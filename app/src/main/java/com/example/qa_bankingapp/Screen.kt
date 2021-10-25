package com.example.qa_bankingapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route : String, val title : String){
    object AccountList : Screen(route = "account_list", title = "Accounts")
    object AccountDetail : Screen(route = "account_details", title = "Account Details")
    object AccountAdd : Screen(route = "account_add", title = "Add Account")
    object AccountEdit : Screen(route = "account_edit", title = "Edit Account")

}