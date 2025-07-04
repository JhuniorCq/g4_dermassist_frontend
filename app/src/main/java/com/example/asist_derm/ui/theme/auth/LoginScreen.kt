package com.example.asist_derm.ui.theme.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R
import com.example.asist_derm.data.remote.RetrofitInstance
import com.example.asist_derm.utils.UserSessionManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        val image_login = painterResource(id = R.drawable.page3)
        Image(
            painter = image_login,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Inicio de sesión",
                textAlign = TextAlign.Center, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Email",
                        style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                    )
                },
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f))
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = "Password",
                        style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                    )
                },
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Gray.copy(alpha = 0.3f))
            )
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            Button(onClick = {
                when {
                    email.isBlank() || !email.contains("@") -> {
                        Toast.makeText(context, "Ingresa un email válido", Toast.LENGTH_LONG).show()
                    }

                    password.length < 6 -> {
                        Toast.makeText(
                            context,
                            "La contraseña debe tener al menos 6 caracteres",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                 else -> {
                     Firebase.auth.signInWithEmailAndPassword(email, password)
                         .addOnCompleteListener { task ->
                             if (task.isSuccessful) {
                                 val uid = Firebase.auth.currentUser?.uid
                                 if (uid != null) {
                                     scope.launch {
                                         try {
                                             val response = RetrofitInstance.api.getUserByUID(uid)
                                             if (response.isSuccessful && response.body() != null) {
                                                 val userResponse = response.body()
                                                 val username = userResponse?.data?.username
                                                 userResponse?.data?.let { userData ->
                                                     UserSessionManager.saveUser(context, userData)
                                                 }



                                                 navController.navigate("actions") {
                                                     popUpTo("login") { inclusive = true }
                                                 }
                                             } else {
                                                 Toast.makeText(
                                                     context,
                                                     "Error al obtener datos del usuario",
                                                     Toast.LENGTH_LONG
                                                 ).show()
                                             }
                                         } catch (e: Exception) {
                                             Toast.makeText(
                                                 context,
                                                 "Error de red: ${e.localizedMessage}",
                                                 Toast.LENGTH_LONG
                                             ).show()
                                         }
                                     }
                                 } else {
                                     Toast.makeText(context, "UID no encontrado", Toast.LENGTH_LONG)
                                         .show()
                                 }
                             } else {
                                 Toast.makeText(
                                     context,
                                     task.exception?.localizedMessage ?: "Inicio de sesión fallido",
                                     Toast.LENGTH_LONG
                                 ).show()
                             }
                         }
                    }
                }
            }
                ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5),
                    contentColor = Color.White )) {
                Text(text = "Log in", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    val navController: NavHostController = rememberNavController()
    LoginScreen(navController)
}
