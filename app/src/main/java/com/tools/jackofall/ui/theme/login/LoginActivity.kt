package com.tools.jackofall.ui.theme.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposable(loginViewModel)
        }
    }
}


@Composable
fun LoginComposable(loginViewModel: LoginViewModel) {

    var userNameState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = userNameState, onValueChange = {
                userNameState = it
            }, label = {
                Text("Enter Username")
            }, modifier = Modifier.fillMaxWidth(), singleLine = true

            )


            Spacer(modifier = Modifier.height(30.dp))

            TextField(value = passwordState,
                onValueChange = {
                    passwordState = it
                },
                label = {
                    Text("Enter Password")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                loginViewModel.login(userNameState,passwordState)

            },modifier = Modifier
                            .fillMaxWidth(0.5f)
                .height(40.dp)
                ) {

                Text(text = "Login")

            }

        }
    }


}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}