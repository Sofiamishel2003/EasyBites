package com.example.busqueda


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easybites.R


data class Receta(
    val nombre: String,
    val imageUrl: String,
    val ingredientes: List<String>,
    val tiempo: Int,
    val presupuesto: String,
    val procedimiento: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaScreen() {

    val recetas = listOf(
        Receta("Huevos rancheros con crema", "https://cloudfront-us-east-1.images.arcpublishing.com/elespectador/TIZD7JJOKJEVTKSXD2FDIVJCZI.jpg", listOf("(70g.) ½ taza de cebolla cortada finamente,(8g.) 2 dientes de ajo,(40g.) ¼ taza de ají rojo,(30ml.) 2 cucharadas de aceite de oliva,(76g.) 1 sobre de CREMA DE TOMATE MAGGI®,(1000ml.) 1 litro de agua,(150g.) 3 huevos medianos,(85g.) ½ taza de queso fresco,Sal y pimienta al gusto,Culantro al gusto"), 11 , "$18", "1.  En una cacerola a fuego medio, calentar el aceite y sofreír la cebolla, el ajo y el ají por 3 minutos.  2.  En un tazón con el agua, diluir el contenido de la CREMA DE TOMATE MAGGI®. Agregar el contenido a la cacerola anterior y cocinar hasta que hierva.  3.  Añadir los huevos, agregar sal y pimienta al gusto, tapar y cocinar por aproximadamente 5 minutos o hasta que los huevos estén cocidos.  4.  Decorar con queso fresco y culantro.  5.  Servir y disfrutar."),
        Receta("Espaguetis a la carbonara","https://imag.bonviveur.com/espaguetis-carbonara-primer-plano.webp",listOf("30 g de guanciale (o panceta fresca),2 yemas de huevo,25 g de queso pecorino rallado (o parmesano),100 g de espaguetis,Sal,Pimienta negra molida,Unas hojas de albahaca (para decorar)"),25,"45","1.Salteamos el guanciale 2.Mezclamos las yemas de huevo con el queso 3.Cocemos los espaguetis 4.Agregamos los espaguetis cocidos a la mezcla de huevo y queso 5.Incorporamos el guanciale 6.Espolvoreamos con pimienta negra y servimos") ,
        Receta("Salmón a la plancha","https://imag.bonviveur.com/salmon-a-la-plancha-facil-foto-principal.webp",listOf("30 g de guanciale (o panceta fresca),2 yemas de huevo,25 g de queso pecorino rallado (o parmesano),100 g de espaguetis,Sal,Pimienta negra molida,Unas hojas de albahaca (para decorar)"),10,"60","1.Salamos las rodajas de salmón por los dos lados y añadimos eneldo seco opcionalmente 2.Añadimos una cucharada de aceite a la plancha caliente, ponemos las rodajas de salmón y las cocinamos por un lado durante 2 minutos 3.Les damos la vuelta y las cocinamos otros 2 minutos 4.Servimos el salmón acompañado de limón."),
        Receta("Camarones al Ajillo","https://www.goya.com/media/6630/spanish-garlic-shrimp-new.jpg?quality=80",listOf("1 ½ libra de camarones limpios,1 ½ libra de camarones limpios,2 cucharadas de mantequilla, 2/3 taza de ajo cortado finamente, ½ taza de perejil cortado finamente"),21,"60","1.  En un tazón, combinar los camarones con el Sazonador de Camarón MAGGI® y dejar sazonar por 5 minutos.  2.  En un sartén a fuego medio, derretir la mantequilla y sofreír el ajo por 1 minuto.  3.  Agregar los camarones y cocinar por 10 minutos. Añadir el perejil y cocinar 2 minutos más.  4.  Servir y disfrutar."),
        Receta("Tortitas de Carne","https://cdn7.kiwilimon.com/recetaimagen/39177/50714.jpg",listOf("1 1/2 libra de carne molida,1 huevo ligeramente batido,1/2 taza de pan molido,1 paquete de JUGOSO AL SARTÉN® MAGGI® Finas Hierbas"),5,"28","Mezclar  1.  Mezcla la carne de res con el huevo y el pan molido hasta integrar por completo, forma 8 hamburguesas de aproximadamente 75 g c/u. Abrir  2.  Abre una Hoja con Sazonador MAGGI® JUGOSO AL SARTÉN® Finas Hierbas; coloca 2 hamburguesas, cierra la hoja y presiona ligeramente para que se impregnen las especias. Cocinar  3.  Precalienta una sartén sin aceite durante 1 minuto; cocina cada hoja con las hamburguesas a fuego medio durante 3 o 4 minutos por cada lado. Retira la hoja, sirve en la lonchera y ¡a disfrutar!"),
        Receta("Carne Asada","https://mojo.generalmills.com/api/public/content/KIVwKMkmX0mcxyYnGcQSyA_gmi_hi_res_jpeg.jpeg?v=25af9e9b&t=16e3ce250f244648bef28c5949fb99ff",listOf("(454g.) 1 libra de carne de res,(8g.) 2 CUBITOS DE RES MAGGI®,Sal y pimienta al gusto,(15g.) 1 cucharada de aceite de oliva,Aguacate,Frijol negro,Encurtido de repollo,Arroz, Plátano frito,Tortilla,Queso blanco"),13,"25"," 1.  En un recipiente triturar el CUBITO DE RES MAGGI® junto con la sal y la pimienta e integrar. Sazonar la carne con esta mezcla ayudado con el aceite de oliva y reservar.  2.  Azar la carne en una parrilla a fuego alto por 3 minutos a cada lado.  3.  En una superficie plana cortar la carne en tiras.  4.  Servir y disfrutar junto con los acompañamientos ya seleccionados"),
    )
    var tiempo by remember { mutableStateOf("") }




    var recetasFiltradas by remember { mutableStateOf<List<Receta>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Buscar Recetas",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tiempo,
            onValueChange = { tiempo = it },
            label = { Text("Tiempo (minutos)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                recetasFiltradas = if (tiempo.isNotBlank()) {
                    val tiempoIngresado = tiempo.toIntOrNull() ?: 0
                    recetas.filter { it.tiempo <= tiempoIngresado }
                } else {
                    emptyList()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD84012),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(if (recetasFiltradas.isNotEmpty()) recetasFiltradas else recetas) { receta ->
                RecetaItem(receta = receta)
                Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray)
            }
        }
    }
}

@Composable
fun RecetaItem(receta: Receta) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = receta.nombre)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tiempo: ${receta.tiempo} minutos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusquedaScreen()
}