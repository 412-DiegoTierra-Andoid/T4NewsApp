package com.example.t4_newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. MODELO DE DATOS (Mismo que el original)
data class Noticia(val titulo: String, val fecha: String, val imagen: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                // Llamamos a la pantalla principal
                PantallaNoticias()
            }
        }
    }
}

val MiColorMorado = Color(0xFF6E56F8)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNoticias() {

    val listaNoticias = listOf(
        Noticia("El presidente de EE.UU. no muestra signos de arrepentimiento...", "febrero 08 - 2024", R.drawable.trump),
        Noticia("Bañarse en la piscina del desierto de Cleopatra", "febrero 09 - 2024", R.drawable.cleopatra),
        Noticia("Gigantes tecnológicos e IA", "febrero 10 - 2024", R.drawable.ia),
        Noticia("El rover de Marte envía datos", "febrero 11 - 2024", R.drawable.marte)
    )
    // 4. ESTRUCTURA PRINCIPAL (Columna con scroll)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp) // Espacio entre cada sección
    ) {

        // --- SECCIÓN: BUSCADOR ---
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    // Redondeamos los bordes completamente
                    .clip(RoundedCornerShape(50.dp)),
                placeholder = { Text("Buscar", color = Color.Gray) },
                // 5. ESTILO DEL BUSCADOR: Color morado para el borde
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MiColorMorado,
                    focusedBorderColor = MiColorMorado
                )
            )
        }

        // --- SECCIÓN: PESTAÑAS (Noticias, Eventos, Clima) ---
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                // Pestaña "Noticias" (Seleccionada)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Noticias", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = Color.Black)
                    // La línea morada debajo
                    Box(Modifier.width(40.dp).height(4.dp).background(MiColorMorado))
                }
                // Pestañas no seleccionadas
                Text("Eventos", color = Color.LightGray, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Clima", color = Color.LightGray, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaNoticias() {
    PantallaNoticias()
}