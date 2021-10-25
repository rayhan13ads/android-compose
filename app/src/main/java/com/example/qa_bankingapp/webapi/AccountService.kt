package com.example.qa_bankingapp.webapi

import com.example.qa_bankingapp.model.Account
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

class AccountService :IAccountService {
    private val BASE_URL = "https://employee-bank-app.herokuapp.com/api"
    private val client = HttpClient {
        //This will auto-parse from/to json when sending and receiving data from Web API
        install(JsonFeature)  {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getAccount(cid: String): Account {
        val url = "$BASE_URL/accounts/$cid"
        return client.get(url)
    }

    override suspend fun addAccount(account: Account): Account? {
        return client.post {
            url("$BASE_URL/accounts/")
            contentType(ContentType.Application.Json)
            body = account
        }
    }

    override suspend fun getAccounts(): List<Account> {
        val url = "$BASE_URL/accounts"
        return client.get(url)
    }

    override suspend fun updateAccount(account: Account): Account? {
        val url = "$BASE_URL/accounts/${account.accountNo}"
        return client.put {
            url(url)
            contentType(ContentType.Application.Json)
            body = account
        }
    }

    override suspend fun deleteAccount(accountNo: String): String {
        val url = "$BASE_URL/accounts/$accountNo"
        return client.delete(url)
    }
}