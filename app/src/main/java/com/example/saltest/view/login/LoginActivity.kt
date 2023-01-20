@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.saltest.view.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saltest.data.viewmodel.LoginViewModel
import com.example.saltest.ui.theme.SALTESTTheme
import com.example.saltest.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SALTESTTheme {
                Login()
            }
        }
    }
}


@Composable
fun Login(viewModel: LoginViewModel = hiltViewModel()) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()

        Text(
            text = "Welcome to SALTEST",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            textStyle = TextStyle(fontWeight = FontWeight.Bold)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.loginUser(emailState.value, passwordState.value)
                    context.startActivity(Intent(context, MainActivity::class.java))

                }
            },
            modifier = Modifier.padding(bottom = 16.dp),
            enabled = emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()
        ) {
            Text("Login")
        }
    }
}

