package com.example.designday

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.designday.ui.theme.DesignDayTheme
import com.example.designday.ui.theme.Taupe100
import com.example.designday.ui.theme.Taupe800
import com.example.designday.ui.theme.lato
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Home(navController: NavController){
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        drawerElevation = 4.dp,
    ) {



Box(modifier = Modifier
    .fillMaxSize()

    .background(color = MaterialTheme.colors.background)) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var srtxt by remember { mutableStateOf(TextFieldValue()) }
        TextField(value = srtxt,
            onValueChange = { srtxt = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp)
                .height(56.dp)
                .clip(MaterialTheme.shapes.small),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontFamily = lato,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    modifier = Modifier.size(18.dp),
                    contentDescription = "Search",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "FAVORITE COLLECTIONS",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )
            val collection = Data.values()
                .take(6)
                .withIndex()
                .groupBy { it.index / 2 }

            LazyRow {
                collection
                    .map { pair ->
                        item {
                            if (collection.entries.first() == pair) {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Column {
                                FavoriteItem(
                                    name = pair.value[0].value.description,
                                    url = pair.value[0].value.link
                                )
                                FavoriteItem(
                                    name = pair.value[1].value.description,
                                    url = pair.value[1].value.link
                                )
                            }
                            if (collection.entries.last() == pair) {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
            }

        }


        Column(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ALIGN BODY",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )
            val items = Data.values().toList().subList(fromIndex = 6, toIndex = 12)
            Column {
                LazyRow {
                    items.map {
                        item {
                            if (items.first() == it) Spacer(modifier = Modifier.width(8.dp))
                            CircleImage(url = it.link, name = it.description)
                            if (items.last() == it) Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }





        Column(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ALIGN YOUR MIND",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )
            val items = Data.values().toList().subList(fromIndex = 12, toIndex = 18)
            LazyRow {
                items.map {
                    item {
                        if (items.first() == it) Spacer(modifier = Modifier.width(8.dp))
                        CircleImage(url = it.link, name = it.description)
                        if (items.last() == it) Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }

    }
}
}
        }

@Composable
fun BottomBar(navController: NavController){
    val screens = listOf(Screen.Home, Screen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        val selectedColor = if (isSystemInDarkTheme()) Taupe100 else Taupe800
        screens.map { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.icon!!),
                        contentDescription = screen.route,
                    )
                },
                label = { Text(screen.name) },
                selected = currentRoute == screen.route,
                selectedContentColor = selectedColor,
                unselectedContentColor = selectedColor.copy(alpha = .5f),
                onClick = {
                    
                    navController.popBackStack(navController.graph.startDestination, false)


                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}
@Composable
fun FloatingActionButton() {
    Column(
        modifier = Modifier
            .size(42.dp)
            .shadow(elevation = 4.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.play_arrow),
            contentDescription = "Play Icon",
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}
@Composable
fun FavoriteItem(name: String, url: String) {
    Column {
        Row(
            modifier = Modifier
                .width(192.dp)
                .height(56.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            Column(
                modifier = Modifier
                    .weight(56f / 192)
                    .aspectRatio(1f)
                    .fillMaxHeight(),
            ) {
                CoilImage(
                    data = url,
                    contentDescription = name,
                    modifier = Modifier
                        .size(56.dp),
                    contentScale = ContentScale.Crop
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface),
                    ) {}
                }
            }
            Column(
                modifier = Modifier
                    .weight(150f / 192)
                    .background(MaterialTheme.colors.surface)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
@Composable
fun CircleImage(
    name: String,
    url: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(
            data = url,
            contentDescription = name,
            modifier = Modifier
                .size(88.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        ) {
            Column(
                modifier = Modifier
                    .size(88.dp)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colors.surface),
            ) {}
        }
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            text = name
        )
    }
}
@Preview("Light Theme",widthDp = 360,heightDp = 640 )
@Composable
fun Review(){
    DesignDayTheme(darkTheme = false){
        Home(rememberNavController())
    }
}