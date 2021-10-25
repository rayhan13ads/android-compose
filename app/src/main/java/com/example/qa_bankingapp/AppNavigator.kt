package com.example.qa_bankingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.qa_bankingapp.account.AccountAdd
import com.example.qa_bankingapp.account.AccountDetail
import com.example.qa_bankingapp.account.AccountEdit
import com.example.qa_bankingapp.account.AccountList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavigator(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AccountList.route,
        //Set the padding provided by scaffold

    ) {
        composable(route = Screen.AccountList.route) {
            AccountList(navHostController,onAccountSelected = { accountId ->
                navHostController.navigate("${Screen.AccountDetail.route}/${accountId}")
            },{
                navHostController.navigate("${Screen.AccountAdd.route}")
            })
        }

        composable(route = Screen.AccountDetail.route + "/{accountId}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Extract the Nav arguments from the Nav BackStackEntry
            backStackEntry.arguments?.getString("accountId")?.let { accountId ->
                AccountDetail(accountId, onNavigateBack = { navHostController.navigateUp() })
            }
        }

        composable(route = Screen.AccountEdit.route + "/{accountId}",
            arguments = listOf(
                navArgument("accountId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Extract the Nav arguments from the Nav BackStackEntry
            backStackEntry.arguments?.getString("accountId")?.let { accountId ->
                AccountEdit(accountId, onNavigateBack = { navHostController.navigateUp() })
            }
        }

        composable(route = Screen.AccountAdd.route) {
            AccountAdd(onNavigateBack = { navHostController.navigateUp() })
        }


    }
}