package com.tools.jackofall.ui.theme.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.tools.core.network.Status
import com.tools.jackofall.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    val loginViewModel: LoginViewModel by viewModels()


    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposable()
        }
    }


    @ExperimentalComposeUiApi
    @Composable
    fun LoginComposable() {
        val keyboardController = LocalSoftwareKeyboardController.current
        var userNameState by remember {
            mutableStateOf("")
        }

        var passwordState by remember {
            mutableStateOf("")
        }

        var isProgressGoingOn by remember {
            mutableStateOf(false)
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

                TextField(
                    value = passwordState,
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



                    Button(
                        onClick = {
                            login(userNameState, passwordState){
                                isProgressGoingOn=it
                            }
                            isProgressGoingOn=true
                            keyboardController?.hide()

                        }, modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(40.dp)

                      , enabled = !isProgressGoingOn

                    ) {

                        Text(text = "Login")

                    }

                    if(isProgressGoingOn){
                        CircularProgressIndicator(
                            modifier = Modifier.padding(top=15.dp)
                                .height(10.dp),
                            color=Color.Green
                        )
                    }




            }
        }


    }


    @ExperimentalComposeUiApi
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        LoginComposable()
    }

   private fun login(userName: String, password: String, updateProgressStatus:(Boolean)->Unit):Unit {
        loginViewModel.login(userName, password).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { it -> it.access_token
                        Toast.makeText(this,it.access_token,Toast.LENGTH_LONG).show();
                            updateProgressStatus(false)
                            loginViewModel.saveAuthData(it)
                            moveToMainScreen()
                        }


                    }
                    Status.ERROR -> {
                        //showErrorAlertMessage()
                        updateProgressStatus(false)
                    }
                    Status.LOADING -> {
                        // not required to handle

                    }
                }
        }
    });

}

    private fun moveToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}