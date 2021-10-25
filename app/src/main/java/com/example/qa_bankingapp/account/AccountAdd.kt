package com.example.qa_bankingapp.account

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qa_bankingapp.common.displayMessage
import com.example.qa_bankingapp.model.Account
import com.example.qa_bankingapp.viewmodel.AccountViewModel

@Composable
fun AccountAdd( onNavigateBack: () -> Unit) {
    val accountViewModel =
        viewModel<AccountViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    var name by remember { mutableStateOf("") }
    var accountNo by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var expandable by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    //initialize the accounts

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Account") },

                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
                )
        }
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxSize(), elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = accountNo,
                    onValueChange = { accountNo = it },
                    label = { Text(text = "Account Number ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text(text = "Amount ") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Dropdown
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { expandable = !expandable }) {
                    OutlinedTextField(
                        value = type,
                        onValueChange = { },
                        enabled = false,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("From Account")
                        }
                    )
                    //we populate the data

                    DropdownMenu(expanded = expandable, onDismissRequest = { expandable = false }) {
                        accountViewModel.accountType.forEach { it ->
                            DropdownMenuItem(onClick = {
                                expandable = false
                                type = it
                            }) {
                                Text(text = "$it")
                            }
                        }
                    }


                }



                Button(
                    onClick = {
                        if (name.isEmpty()) {
                            displayMessage(
                                context,
                                "Please enter name"
                            )
                        } else if (accountNo.isEmpty()) {
                            displayMessage(
                                context,
                                "Please enter Account No"
                            )
                        } else if (type.isEmpty()) {
                            displayMessage(
                                context,
                                "Please select account type "
                            )
                        } else if (amount.isEmpty()) {
                            displayMessage(
                                context,
                                "Please enter amount "
                            )
                        } else {
                            accountViewModel.addAccount(
                                Account(
                                    name = name,
                                    accountNo = accountNo,
                                    acctType = type,
                                    balance = amount.toInt()
                                )
                            )
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Text(text = "Submit")
                }
            }


        }
    }
}

