package com.example.t4_newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Modelo de datos simple
data class Noticia(val titulo: String, val fecha: String, val color: Color)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                PantallaNoticias()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNoticias() {
    // Datos de ejemplo
    val listaNoticias = listOf(
        Noticia("El presidente de EE.UU. no muestra signos...", "febrero 08 - 2024", Color(0xFF6E56F8)),
        Noticia("Bañarse en la piscina de Cleopatra", "febrero 09 - 2024", Color(0xFF6E56F8))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // --- BUSCADOR (TopBar) ---
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp)),
            placeholder = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            // ASÍ SE PONE AHORA EN MATERIAL 3:
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFF6E56F8)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- TABS (Row) ---
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Noticias", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("Eventos", color = Color.LightGray) // Deshabilitado
            Text("Clima", color = Color.LightGray)  // Deshabilitado
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- CONTENIDO CON SCROLL ---
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {

            // SECCIÓN: Ultimas noticias
            item {
                Text("Ultimas noticias", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(listaNoticias) { noticia ->
                        CardGrande(noticia)
                    }
                }
            }

            // SECCIÓN: Alrededor del mundo
            item {
                Text("Alrededor del mundo", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Grid de 2 columnas (Simulado con Rows para evitar conflictos de scroll)
            items(listaNoticias.chunked(2)) { par ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    par.forEach { noticia ->
                        CardPequeña(noticia, Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CardGrande(noticia: Noticia) {
    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 150.dp) // Ajustado para dar más espacio al texto
            .clip(RoundedCornerShape(32.dp))      // Bordes más redondeados como en la imagen
            .background(Color(0xFF6E56F8))         // El morado exacto de la imagen
            .padding(24.dp)                       // Más padding para que el texto no toque los bordes
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre el título y la fecha
        ) {
            Text(
                text = noticia.titulo,
                color = Color.White,
                fontSize = 22.sp,                // Fuente más grande
                fontWeight = FontWeight.ExtraBold, // Letra más gruesa
                lineHeight = 28.sp,              // Mejor interlineado para lectura
                maxLines = 4,                    // Permitir más líneas si es necesario
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = noticia.fecha,
                color = Color.White.copy(alpha = 0.9f), // Un poco más brillante
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CardPequeña(noticia: Noticia, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE0E0E0)) // Fondo gris simulando imagen
    ) {
        // Texto sobre fondo gris claro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.LightGray.copy(alpha = 0.6f))
                .padding(8.dp)
        ) {
            Text(noticia.titulo, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 2)
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaNoticias() {
    // Aquí envolvemos la pantalla en el tema de tu app
    // para que se vea con los colores correctos
    Surface(color = Color.White) {
        PantallaNoticias()
    }
}