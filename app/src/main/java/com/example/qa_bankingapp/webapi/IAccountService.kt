package com.example.qa_bankingapp.webapi

import com.example.qa_bankingapp.model.Account


interface IAccountService {
    suspend fun getAccount(cid: String): Account
    suspend fun addAccount(account: Account): Account?
    suspend fun getAccounts(): List<Account>
    suspend fun updateAccount(account: Account): Account?
    suspend fun deleteAccount(accountNo: String): String
}