package ru.kubamba.mipt_android.screens.signin

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class SignInViewState(
    val email: String = "",
    val password: String = ""
)

sealed class SignInEvent() {
    data class EmailChanged(val email: String): SignInEvent()
    data class PasswordChanged(val password: String): SignInEvent()
    object ForgotPassword: SignInEvent()
    object Login: SignInEvent()
}

class SignInViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(SignInViewState())
    val viewState: StateFlow<SignInViewState> = _viewState

    fun obtainEvent(event: SignInEvent, navController: NavController) {
        when (event) {
            SignInEvent.ForgotPassword -> forgotPassword()
            SignInEvent.Login -> login(navController)
            is SignInEvent.EmailChanged -> changeEmail(event.email)
            is SignInEvent.PasswordChanged -> changePassword(event.password)
        }
    }

    private fun forgotPassword() {

    }

    private fun login(navController: NavController) {
        navController.navigate("restaurants") {
            popUpTo("signin") {
                inclusive = true
            }
        }
    }

    private fun changeEmail(email: String) {
        _viewState.value = _viewState.value.copy(email = email)
    }

    private fun changePassword(password: String) {
        _viewState.value = _viewState.value.copy(password = password)
    }
}

