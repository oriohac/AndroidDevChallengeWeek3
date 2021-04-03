package com.example.designday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.designday.ui.theme.Typography
import com.example.designday.ui.theme.lato

@Composable
fun Login(navController: NavController){
    val backg = if (isSystemInDarkTheme()){
        MaterialTheme.colors.background
    }else{
        MaterialTheme.colors.background
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backg) ) {

        val image = if (isSystemInDarkTheme()){
            painterResource(id = R.drawable.ic_dark_login)
        }else{
            painterResource(id = R.drawable.ic_light_login)
        }
        Image(modifier= Modifier.fillMaxSize(),
            painter = image,
            contentDescription = "Log In background",
            contentScale = ContentScale.FillBounds)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


                Column(modifier = Modifier.padding(bottom = 32.dp)) {
                    Text(text = "LOG IN", color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.h1)
                }
                var text by remember { mutableStateOf (TextFieldValue( ))}
                TextField( value = text,
                    onValueChange = {text = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(MaterialTheme.shapes.small),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface,fontFamily = lato, fontSize = 14.sp, fontWeight = FontWeight.Normal ),
                    placeholder = {Text(text = "Email address")}
                    )

                Spacer(modifier = Modifier.height(8.dp))

                var text2 by remember { mutableStateOf(TextFieldValue()) }

                TextField(value = text2,
                    onValueChange = {text2 = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(MaterialTheme.shapes.small),
                    colors = TextFieldDefaults.textFieldColors( backgroundColor = MaterialTheme.colors.onPrimary),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface,fontFamily = lato, fontSize = 14.sp, fontWeight = FontWeight.Normal ),
                    placeholder = {Text(text = "Password")},
                    visualTransformation = PasswordVisualTransformation()
                    )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clip(MaterialTheme.shapes.medium),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                    onClick = {  navController.navigate(Screen.Home.route) }) {
                    Text(text = "LOG IN", style = Typography.button,
                        color = MaterialTheme.colors.onPrimary)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row{
                Text(text = "Don't have an account? ",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
                Text(text = "Sign up",
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}


