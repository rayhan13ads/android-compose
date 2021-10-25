package com.example.qa_bankingapp.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.qa_bankingapp.model.Account
import com.example.qa_bankingapp.webapi.AccountService
import kotlinx.coroutines.launch

class AccountViewModel(appContext: Application): AndroidViewModel(appContext) {

    private val TAG = "TransferViewModel"

    private val accountService = AccountService()

    val accounts = mutableStateListOf<Account>()
    val accountType:Array<String> = arrayOf("saving","current")


    init {
        getAccounts()
    }

    private fun getAccounts() {
        accounts.clear()
        viewModelScope.launch {
            accounts.addAll(accountService.getAccounts())
        }
    }

    fun addAccount(account: Account) {
        Log.d(TAG, "$account")
        viewModelScope.launch {
            val newTransfer = accountService.addAccount(account = account)
            newTransfer?.let { accounts.add(it) }
            Log.d(TAG, "$newTransfer")
        }
    }

    fun putAccount(account: Account, onCallBack: () -> Unit) {
        Log.d(TAG, "$account")
        viewModelScope.launch {
            val newAccounts = accountService.updateAccount(account = account)
            val existAccountPositon = accounts.indexOfFirst {
                it.accountNo == account.accountNo
            }
            newAccounts.let {
                accounts[existAccountPositon] = it!!
                onCallBack()
            }


            Log.d(TAG, "$newAccounts")
        }
    }

    fun getItem(accountNo: String) = accounts.find { it.accountNo == accountNo }

    fun getAccount(accountNo: String):Account {
        lateinit var accountDetails: Account
        viewModelScope.launch {
           accountDetails = accountService.getAccount(accountNo)
        }

        return  accountDetails

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun delete(accountNo: String) {
        accounts.removeIf { it.accountNo == accountNo }
        viewModelScope.launch {
            accountService.deleteAccount(
                accountNo = accountNo,

            )
        }
    }
}