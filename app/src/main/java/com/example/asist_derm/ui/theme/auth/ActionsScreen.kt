package com.example.asist_derm.ui.theme.auth


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R
import com.example.asist_derm.data.model.UserData
import com.example.asist_derm.utils.UserSessionManager

@Composable
fun ActionsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Log.d("ActionsScreen", "Imagen seleccionada: $it")
            val encodedUri = Uri.encode(it.toString())
            navController.navigate("predict/$encodedUri")
        }
    }
    val userData = remember { mutableStateOf<UserData?>(null) }

    LaunchedEffect(Unit) {
        userData.value = UserSessionManager.getUser(context)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavPanel(navController)
        }
    ) { paddingValues ->
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF4393C5),
                Color.White
            )
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(brush = gradientBrush).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting(username = userData.value?.username ?: "Usuario")
            Presentation(navController = navController, onUploadClick = {
                galleryLauncher.launch("image/*")
            })
        }
    }
}

@Composable
fun Greeting(username: String) {
     Box(modifier = Modifier.fillMaxWidth()
         .padding(top = 20.dp, start = 18.dp, end = 18.dp)
         .clip(RoundedCornerShape(16.dp))
         .background(Color.White)
         .border(2.dp, Color(0xFF4393C5), RoundedCornerShape(16.dp))
         .padding(16.dp), contentAlignment = Alignment.TopEnd
     ){
         Text(text = buildAnnotatedString {
             append("Hola, ")
             withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                 append(username)
             }
             append(" \uD83D\uDC4B")
         },
         fontSize = 18.sp, textAlign = TextAlign.End, color = Color.Black,
             modifier = Modifier


         )
     }
}
@Composable
fun Presentation(navController: NavHostController, onUploadClick: () -> Unit ) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
             horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a DermAssist", textAlign = TextAlign.Center,
                fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            )
            Text(
                text = "¿Tienes una preocupación en la piel? ", textAlign = TextAlign.Start,
                fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
            Text(
                text = stringResource(R.string.description),
                fontSize = 20.sp, color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
            )
            Text(
                text = "\uD83D\uDCF8 Haz una foto o sube una imagen y deja que la tecnología te ayude ⬇\uFE0F."
                , fontSize = 19.sp, color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
            Button(onClick = {navController.navigate("camera")  }, modifier = Modifier
                .padding(bottom = 20.dp,top = 30.dp).fillMaxWidth()
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5)))
            {
                Text(text = "Hacer una foto", color = Color.White)
            }
            Text(
                text = "o", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Button(onClick = onUploadClick
                ,modifier = Modifier.padding(bottom = 30.dp,top = 30.dp).fillMaxWidth()
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color.White))
            {
                Text(text = "Subir una foto", color = Color.Black)
            }
        }
    }

}

@Composable
fun BottomNavPanel(navController: NavHostController,modifier: Modifier= Modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
      BottomNavigation(
            modifier = Modifier.fillMaxWidth(), backgroundColor = Color.White,
        ) {
            BottomNavigationItem(
                selected = currentRoute == "actions",
                onClick = { navController.navigate("actions")},
                icon = {
                    Icon(painter = painterResource(R.drawable.boton1), contentDescription = "Home"
                        ,tint = Color.Unspecified)

                }
            )
            BottomNavigationItem(
                selected = currentRoute == "history",
                onClick = { navController.navigate("history") },
                icon = {
                    Icon(painter = painterResource(R.drawable.boton2), contentDescription = "Historial"
                        ,tint = Color.Unspecified)
                }
            )
            BottomNavigationItem(
                selected = currentRoute == "camera",
                onClick = { navController.navigate("camera") },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFF4393C5), shape = CircleShape)
                            .border(2.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.boton3), contentDescription = "Camara",
                            tint = Color.Unspecified
                        )
                    }
                }
            )
            BottomNavigationItem(
                selected  = currentRoute == "clinics",
                onClick = { navController.navigate("clinics") },
                icon = {
                    Icon(painter = painterResource(R.drawable.boton4), contentDescription = "Clínicas",
                        tint = Color.Unspecified)
                }
            )
            BottomNavigationItem(
                selected = currentRoute == "profile",
                onClick = {navController.navigate("profile")  },
                icon = {
                    Icon(painter = painterResource(R.drawable.boton5), contentDescription = "Perfil",
                        tint = Color.Unspecified)
                }
            )
        }
}


@Composable
@Preview
    fun ActionsPreview(){
        val navController: NavHostController = rememberNavController()
    ActionsScreen(navController)
}
