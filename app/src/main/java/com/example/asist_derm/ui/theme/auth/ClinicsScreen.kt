package com.example.asist_derm.ui.theme.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun ClinicsScreen(navController: NavHostController) {
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
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(brush = gradientBrush)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleClinic()
            ClinicList()

        }

    }
}
@Composable
fun TitleClinic(){
    Column (modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment= Alignment.CenterHorizontally) {
    Text(text = "Encuenta a los Mejores", textAlign = TextAlign.Center
        , color = Color.White, fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold
        , modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
    Text(text = "Las mejores clínicas dermatológicas en Perú trabajan con nosotros"
        , textAlign = TextAlign.Center, color = Color.White, fontSize = 24.sp, fontStyle = FontStyle.Italic
        )
    }
}


@Composable
fun ClinicCard(number: String, name: String, imageRes: Int,onClick: () -> Unit ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFFF0F0F0))
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFFF0F0F0)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = number,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color.Black, textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun ClinicList() {
    Box(modifier = Modifier.padding(20.dp).clip(RoundedCornerShape(20.dp)).background(color = Color.White)
        .padding(20.dp)) {
        val context = LocalContext.current
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
            ClinicCard("01", "Clínica Lima Derma", R.drawable.clinic1) {
                openLink(context, "https://limaderma.com/")
            }
            ClinicCard("02", "Clínica Internacional", R.drawable.clinic2){
                openLink(context, "https://www.clinicainternacional.com.pe/")
            }
            ClinicCard("03", "Clínica San Pablo", R.drawable.clinic3){
                openLink(context, "https://www.sanpablo.com.pe/")
            }
            ClinicCard("04", "Clínica Ricardo Palma", R.drawable.clinic4){
                openLink(context, "https://www.crp.com.pe/")
            }
            ClinicCard("05", "Clínica San Gabriel", R.drawable.clinic5){
                openLink(context, "https://www.clinicasangabriel.com.pe/")
            }
        }
    }
}
fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
@Preview
fun ClinicsPreview(){
    val navController = rememberNavController()
    ClinicsScreen(navController)
}