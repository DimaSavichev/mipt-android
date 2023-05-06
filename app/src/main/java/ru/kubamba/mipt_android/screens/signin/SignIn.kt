package ru.kubamba.mipt_android.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.kubamba.mipt_android.R

@Composable
fun SignInScreen(signInViewModel: SignInViewModel, navController: NavController) {
    val signInViewState by signInViewModel.viewState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo()

        Text(
            text = stringResource(R.string.login_to_your_account),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        SignInView(signInViewModel, signInViewState, navController)
    }
}

@Composable
fun SignInView(signInViewModel: SignInViewModel, signInViewState: SignInViewState, navController: NavController) {
    TextField(
        value = signInViewState.email,
        onValueChange = { signInViewModel.obtainEvent(SignInEvent.EmailChanged(it), navController) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(stringResource(R.string.email_field)) },
        singleLine = true,
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .height(57.dp)
            .width(325.dp)
    )

    TextField(
        value = signInViewState.password,
        onValueChange = { signInViewModel.obtainEvent(SignInEvent.PasswordChanged(it), navController) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(stringResource(R.string.password_field)) },
        singleLine = true,
        modifier = Modifier
            .padding(top = 5.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .height(57.dp)
            .width(325.dp)
    )



    TextButton(
        onClick = { signInViewModel.obtainEvent(SignInEvent.ForgotPassword, navController) },
        modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)
    )
    {
        Text(
            text = stringResource(id = R.string.forgot_password),
            color = Color(0xFF53E88B),
            fontSize = 12.sp
        )
    }

    Button(
        onClick = { signInViewModel.obtainEvent(SignInEvent.Login, navController = navController) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF53E88B)),
        modifier = Modifier
            .height(57.dp)
            .width(141.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
    )
    {
        Text(
            text = stringResource(id = R.string.login_button),
            color = Color.White,
            fontSize = 16.sp)
    }
}
@Composable
fun Logo() {
    val image = painterResource(id = R.drawable.image_44)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .height(139.dp)
            .width(175.dp))

    Text(
        text = stringResource(id = R.string.app_title),
        fontSize = 40.sp,
        color = Color(0xFF53E88B)
    )

    Text(
        text = stringResource(id = R.string.app_subtitle),
        fontSize = 13.sp,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}