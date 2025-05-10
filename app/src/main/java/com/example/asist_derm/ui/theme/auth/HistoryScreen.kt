package com.example.asist_derm.ui.theme.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.asist_derm.R

@Composable
fun HistoryScreen(navController: NavHostController) {
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
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(
                rememberScrollState()
            )
                .background(brush = gradientBrush),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title()
            Pictures()

        }

    }
}
@Composable
fun Title() {
    Text(text = "Mi Historial", textAlign = TextAlign.Center, color = Color.White,
        fontStyle = FontStyle.Italic ,fontSize = 50.sp, fontWeight = FontWeight.Bold
        , modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))
}
@Composable
fun Pictures() {
Column(modifier = Modifier.fillMaxSize()) {
    Row(modifier = Modifier.fillMaxWidth().background(color = Color.Transparent).padding(20.dp)) {
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    )
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                      modifier = Modifier.clip(RoundedCornerShape(20.dp)))
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
    }
    Row(modifier = Modifier.fillMaxWidth().background(color = Color.Transparent).padding(20.dp)) {
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                )
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp)))
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
    }
    Row(modifier = Modifier.fillMaxWidth().background(color = Color.Transparent).padding(20.dp)) {
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                )
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(16.dp))
            .padding(12.dp)){
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val image = painterResource(id = R.drawable.historyej)
                Image(painter = image, contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp)))
                Text(text = "Dermatitis", textAlign = TextAlign.Center, color = Color.Black,)
            }
        }
    }
    }
}

@Preview
@Composable
fun HistoryPreview(){
    val navController = rememberNavController()
    HistoryScreen(navController)
}