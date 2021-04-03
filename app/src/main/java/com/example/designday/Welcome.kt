package com.example.designday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.designday.ui.theme.Typography

@Composable
fun Welcome(navController: NavController){
    val backg = if (isSystemInDarkTheme()){
        MaterialTheme.colors.background
    }else{
        MaterialTheme.colors.background
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backg) ) {

        val image = if (isSystemInDarkTheme()){
            painterResource(id = R.drawable.ic_dark_welcome)
        }else{
            painterResource(id = R.drawable.ic_light_welcome)
        }
        Image(modifier= Modifier.fillMaxSize(),
            painter = image,
            contentDescription = "background",
            contentScale = ContentScale.FillBounds)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


                Column(modifier = Modifier.padding(bottom = 32.dp)) {
                    val logo = if (isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.ic_dark_logo)
                    } else {
                        painterResource(id = R.drawable.ic_light_logo)
                    }
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = logo,
                        contentDescription = "Logo",
                    )
                }

                Button(
                    // color: Color = MaterialTheme.colors.onPrimary,
                    //shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clip(MaterialTheme.shapes.medium),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                    onClick = { /*TODO*/ }) {
                    Text(text = "SIGN UP", style = Typography.button,
                        color = MaterialTheme.colors.onPrimary)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clip(MaterialTheme.shapes.medium),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
                    onClick = { navController.navigate(Screen.Login.route)  }) {
                    Text(text = "LOG IN", style = Typography.button,
                        color = MaterialTheme.colors.onSecondary)
                }
            }
        }
    }
}