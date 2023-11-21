import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.easybites.R
import com.example.easybites.navigation.AppScreens

@Composable
fun UsuarioScreen() {
    // Contenido de la pantalla Usuario
    @Composable
    fun TopBarWithLogo() {
        val backgroundColor = Color(0xFFD84012)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logolargo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 1000.dp, height = 80.dp)
                    .padding(16.dp)
            )

            IconButton(
                onClick = {  { "   viewModel.signOut {\n" +
                        "                    navController.navigate(AppScreens.LoginScreen.ruta) // Navegar a la pantalla de inicio de sesión después de cerrar sesión\n" +
                        "                }"

                }},
                modifier = Modifier.padding(end = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
            }
        }
    }
}
