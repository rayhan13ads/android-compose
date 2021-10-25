package com.example.qa_bankingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val accountNo: String,
    val acctType: String,
    val balance: Int,
    val name: String
)