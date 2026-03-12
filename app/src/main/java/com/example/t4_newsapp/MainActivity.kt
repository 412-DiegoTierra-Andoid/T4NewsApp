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
// 2. COLOR PERSONALIZADO (Definimos el color morado una vez)
val MiColorMorado = Color(0xFF6E56F8)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNoticias() {
    // 3. DATOS DE PRUEBA (Asumiendo que tienes estas imágenes en res/drawable)
    val listaNoticias = listOf(
        Noticia("El presidente de EE.UU. no muestra signos de arrepentimiento...", "febrero 08 - 2024", R.drawable.trump),
        Noticia("Bañarse en la piscina del desierto de Cleopatra", "febrero 09 - 2024", R.drawable.cleopatra),
        Noticia("Gigantes tecnológicos e IA", "febrero 10 - 2024", R.drawable.ia),
        Noticia("El rover de Marte envía datos", "febrero 11 - 2024", R.drawable.marte)
    )



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaNoticias() {
    PantallaNoticias()
}