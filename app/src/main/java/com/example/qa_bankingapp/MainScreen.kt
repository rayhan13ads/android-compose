package com.example.qa_bankingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    AppNavigator(navHostController)
}



