package com.tools.jackofall.ui.theme.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tools.core.BaseViewModel
import com.tools.core.network.Resource
import com.tools.core.network.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(private val loginRepo: LoginRepo):BaseViewModel() {


    val onLoginSuccess = MediatorLiveData<Boolean>()


    fun login(userName:String,password:String)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepo.login(LoginRequest(username = userName,password = password))))
        } catch (exception: Exception) {
            emit(
                Resource.error(data = null, message = exception.message
                ?: "Error Occurred!"))
        }
    }

}