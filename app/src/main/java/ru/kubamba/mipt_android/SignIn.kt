import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kubamba.mipt_android.R
import ru.kubamba.mipt_android.SignInViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kubamba.mipt_android.SignInEvent
import ru.kubamba.mipt_android.SignInViewState

@Composable
fun SignInScreen() {
    val signInViewModel: SignInViewModel = viewModel()
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

        SignInView(signInViewModel, signInViewState)
    }
}

@Composable
fun SignInView(signInViewModel: SignInViewModel, signInViewState: SignInViewState) {
    TextField(
        value = signInViewState.email,
        onValueChange = { signInViewModel.obtainEvent(SignInEvent.EmailChanged(it)) },
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
        onValueChange = { signInViewModel.obtainEvent(SignInEvent.PasswordChanged(it)) },
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
        onClick = { signInViewModel.obtainEvent(SignInEvent.ForgotPassword) },
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
        onClick = { signInViewModel.obtainEvent(SignInEvent.Login) },
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