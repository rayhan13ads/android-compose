package com.example.qa_bankingapp.account

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qa_bankingapp.Screen
import com.example.qa_bankingapp.ui.theme.Purple200
import com.example.qa_bankingapp.viewmodel.AccountViewModel

@Composable
fun AccountDetail(accountId: String, onNavigateBack: () -> Unit){
    val accountViewModel =
        viewModel<AccountViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)



    val account = accountViewModel.getItem(accountId)
   Scaffold(
       topBar = {
           TopAppBar(title = { Text("Details Account") },
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

       Column() {
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

                           .weight(1f),
                   ) {
                       Text(
                           text = account!!.name, style = TextStyle(
                               fontSize = 18.sp,
                               color = Purple200
                           )
                       )
                       Text(text = "${account.accountNo})")
                       Text(text = account.acctType)
                       Text(text = "accountNo${account.balance}")
                   }


               }
           }
       }
   }
}