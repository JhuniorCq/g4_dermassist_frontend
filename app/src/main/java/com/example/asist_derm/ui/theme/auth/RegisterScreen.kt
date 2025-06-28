package com.example.asist_derm.ui.theme.auth

import android.util.Log
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
import com.example.asist_derm.data.model.RegisterRequest
import com.example.asist_derm.data.remote.RetrofitInstance.api
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
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
                "Registro",
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
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(
                        text = "UserName",
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

                    name.isBlank() -> {
                        Toast.makeText(context, "Ingresa un nombre de usuario", Toast.LENGTH_LONG)
                            .show()
                    }

                    password.length < 6 -> {
                        Toast.makeText(
                            context,
                            "La contraseña debe tener al menos 6 caracteres",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        Firebase.auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = Firebase.auth.currentUser!!
                                    val uid = user.uid
                                    val emailUser = user.email ?: ""
                                    val username = name

                                    scope.launch {
                                        try{
                                            val req = RegisterRequest(username, emailUser, uid)
                                            val resp = api.registerUser(req)
                                            if (resp.isSuccessful) {
                                                print("Respuesta del backend")
                                                withContext(Dispatchers.Main) {
                                                    Toast.makeText(context, "Registrado en backend", Toast.LENGTH_SHORT).show()
                                                    navController.navigate("login") {
                                                        popUpTo("login") { inclusive = true }
                                                    }
                                                }
                                            } else {

                                                withContext(Dispatchers.Main) {
                                                    Toast.makeText(
                                                        context,
                                                        "Error backend: ${resp.code()}",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }
                                        }catch (e: Exception) {
                                            Log.e("RegisterScreen", "Error alconectar al backend", e)
                                            withContext(Dispatchers.Main) {
                                                Toast.makeText(context, "Falló conexión: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Registro fallido", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                         }
                    }
                }
                ,modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                ,elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ,colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4393C5),
                    contentColor = Color.White )) {
                Text(text = "Sign up", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun RegisterPreview() {
    val navController: NavHostController = rememberNavController()
    RegisterScreen(navController)
}