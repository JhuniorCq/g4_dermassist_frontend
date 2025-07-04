package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R
import com.example.asist_derm.data.model.UserData
import com.example.asist_derm.utils.UserSessionManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController) {
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
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(brush = gradientBrush),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Viewimage()
            Control(navController)

        }
    }
}

@Composable
fun Viewimage(){
    Box(modifier = Modifier.padding(top = 100.dp)){
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)

        )
    }
}
@Composable
fun Control(navController: NavHostController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userData = remember { mutableStateOf<UserData?>(null) }
    LaunchedEffect(Unit) {
        userData.value = UserSessionManager.getUser(context)
    }
    var uid by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        val user = UserSessionManager.getUser(context)
        uid = user?.uid
    }

    Column(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.Center) {
      Text(
          text =  userData?.value?.username ?: "Cargando...",
          color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(top = 20.dp), textAlign = TextAlign.Center
      )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {navController.navigate("actions")  }
            ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
            ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F5D84),
                contentColor = Color.White )) {
            Text(text = "Nuevo diágnostico")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { uid?.let { navController.navigate("history/$it") }}
            ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
            ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5),
                contentColor = Color.White )) {
            Text(text = "Historial")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {scope.launch {
            UserSessionManager.clearUser(context)
            Firebase.auth.signOut()
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }}
            ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
            ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ,colors = ButtonDefaults.buttonColors(containerColor = Color.White,
            contentColor = Color.Black )) {
            Text(text = "Cerrar sesión")
        }
    }
}
@Composable
@Preview
fun ProfilePreview(){
    val navController: NavHostController = rememberNavController()
    ProfileScreen(navController)
}