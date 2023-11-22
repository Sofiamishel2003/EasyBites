package com.example.easybites.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easybites.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaScreen() {
    var tiempo by remember { mutableStateOf("") }
    var costo by remember { mutableStateOf("") }
    TopBarWithLogo()
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
                fontSize = 30.sp // Tamaño más grande para el título
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tiempo,
            onValueChange = { tiempo = it },
            label = { Text("Tiempo (minutos)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = costo,
            onValueChange = { costo = it },
            label = { Text("Costo (USD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* busqueda */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD84012),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id= R.string.Buscar,"Buscar"),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Texto más grande para el botón
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}
