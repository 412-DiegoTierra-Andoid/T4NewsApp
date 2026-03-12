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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp) // Espacio entre cada sección
    ) {
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp)),
                placeholder = { Text("Buscar", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MiColorMorado,
                    focusedBorderColor = MiColorMorado
                )
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Noticias", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = Color.Black)
                    Box(Modifier.width(40.dp).height(4.dp).background(MiColorMorado))
                }
                Text("Eventos", color = Color.LightGray, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Clima", color = Color.LightGray, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }


        // --- SECCIÓN: ÚLTIMAS NOTICIAS (Horizontal) ---
        item {
            Text("Últimas noticias", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))

            // Fila deslizable
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(listaNoticias) { noticia ->
                    CardGrande(noticia)
                }
            }
        }

        // --- SECCIÓN: ALREDEDOR DEL MUNDO (Título) ---
        item {
            Text("Alrededor del mundo", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
        }

        // --- SECCIÓN: GRILLA DE NOTICIAS (2x2) ---
        // Aquí simplificamos el 'chunked'. Simplemente creamos las filas manualmente.
        // Fila 1
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Usamos Modifier.weight(1f) para que cada tarjeta ocupe la mitad
                CardPequeña(listaNoticias[0], Modifier.weight(1f))
                CardPequeña(listaNoticias[1], Modifier.weight(1f))
            }
        }
        // Fila 2
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CardPequeña(listaNoticias[2], Modifier.weight(1f))
                CardPequeña(listaNoticias[3], Modifier.weight(1f))
            }
        }

        // Espacio final para que no quede pegado abajo
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

// 6. COMPONENTE: TARJETA GRANDE CON TEXTO ENCIMA Y FILTRO MORADO
@Composable
fun CardGrande(noticia: Noticia) {
    Box(
        modifier = Modifier
            .size(width = 280.dp, height = 150.dp)
            // Bordes muy redondeados
            .clip(RoundedCornerShape(32.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                text = noticia.titulo,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = noticia.fecha,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CardPequeña(noticia: Noticia, modifier: Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(id = noticia.imagen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = Color(0xFFD9D9D9).copy(alpha = 0.9f),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Text(
                text = noticia.titulo,
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 3,
                lineHeight = 16.sp // Espacio entre líneas de texto
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaNoticias() {
    PantallaNoticias()
}