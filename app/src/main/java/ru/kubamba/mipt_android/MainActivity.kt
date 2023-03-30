package ru.kubamba.mipt_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kubamba.mipt_android.ui.theme.MiptandroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiptandroidTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Logo()

                        Text(
                            text = "Login To Your Account",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )


                        var username by remember {
                            mutableStateOf("")
                        }

                        TextField(
                            value = username,
                            onValueChange = { newText: String ->
                                username = newText
                            },
                            placeholder = { Text("Email") },
                            singleLine = true,
                            modifier = Modifier.height(57.dp).width(325.dp).clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)).padding(bottom = 5.dp)
                        )

                        var password by remember {
                            mutableStateOf("")
                        }

                        TextField(
                            value = password,
                            onValueChange = { newText: String ->
                                password = newText
                            },
                            placeholder = { Text("Password") },
                            singleLine = true,
                            modifier = Modifier.height(57.dp).width(325.dp).clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)).padding(top = 5.dp)
                        )



                        TextButton(
                            onClick = {},
                            modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)
                        )
                        {
                            Text(
                                text = "Forgot Your Password?",
                                color = Color(0xFF53E88B),
                                fontSize = 12.sp
                            )
                        }

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF53E88B)),
                            modifier = Modifier.height(57.dp).width(141.dp).clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        )
                        {
                            Text(
                                text = "Login",
                                color = Color.White,
                                fontSize = 16.sp)
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun Logo() {
    val image = painterResource(id = R.drawable.image_44)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.height(139.dp).width(175.dp))

    Text(
        text = "FoodNinja",
        fontSize = 40.sp,
        color = Color(0xFF53E88B)
    )

    Text(
        text = "Deliver Favourite Food",
        fontSize = 13.sp,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}