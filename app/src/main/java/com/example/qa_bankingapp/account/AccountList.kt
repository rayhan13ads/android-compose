package com.example.qa_bankingapp.account


import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qa_bankingapp.Screen
import com.example.qa_bankingapp.model.Account
import com.example.qa_bankingapp.ui.theme.Purple200
import com.example.qa_bankingapp.ui.theme.Purple500
import com.example.qa_bankingapp.viewmodel.AccountViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AccountList(navController: NavHostController,onAccountSelected: (String) -> Unit, onAdd: () -> Unit) {
    val accountViewModel =
        viewModel<AccountViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val accounts = accountViewModel.accounts

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("QA-Banking App") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                                           onAdd()
            },) {
                Icon(Icons.Sharp.Add, contentDescription ="add")
            }
        }

    ) {

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(accounts) { account ->
                ItemCar(
                    account = account,
                    onSelected = {
                        onAccountSelected(account.accountNo)

                    },
                    onDelete = {
                        accountViewModel.delete(account.accountNo)
                    },
                    onEdit = {
                        accountViewModel.putAccount(account,onCallBack ={
                            navController.navigate("${Screen.AccountEdit.route}/${account.accountNo}")
                        })

                    }
                )
            }
        }
    }
}

@Composable
fun ItemCar(account: Account, onSelected: () -> Unit, onDelete: () -> Unit, onEdit: () -> Unit) {

    Card(
        elevation = 10.dp,
        backgroundColor = Color(255, 255, 224),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .clickable { onSelected() }
                    .weight(1f),
            ) {
                Text(
                    text = "${account.name}", style = TextStyle(
                        fontSize = 18.sp,
                        color = Purple200
                    )
                )
                Text(text = "${account.accountNo})")
                Text(text = "${account.acctType}")
                Text(text = "accountNo${account.balance}")
            }
            Column {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .clickable { onDelete() }
                        .size(40.dp)
                )
                Icon(
                    imageVector = Icons.Sharp.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .clickable { onEdit() }
                        .size(40.dp)
                )
            }

        }
    }
}