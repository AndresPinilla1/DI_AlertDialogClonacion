package com.example.di_alertdialogclonacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.di_alertdialogclonacion.ui.theme.DI_AlertDialogClonacionTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.MoreVert


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_AlertDialogClonacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun principal() {
    // Estados para cambiar entre las "aplicaciones"
    var actividad1 by remember { mutableStateOf(false) }
    var actividad2 by remember { mutableStateOf(false) }
    var actividad3 by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selecciona una Aplicación", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            // Botón para la primera actividad
            Button(onClick = {
                actividad1 = true
                actividad2 = false
                actividad3 = false
            }) {
                Text("Diálogos de Alerta")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Botón para la segunda actividad
            Button(onClick = {
                actividad1 = false
                actividad2 = true
                actividad3 = false
            }) {
                Text("Clonar App")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Botón para la tercera actividad
            Button(onClick = {
                actividad1 = false
                actividad2 = false
                actividad3 = true
            }) {
                Text("Lista de Tareas")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            // Mostrar la sección activa según el estado
            if (actividad1) {
                SeccionDialogos { actividad1 = false }
            }
            if (actividad2) {
                SpotifyApp { actividad2 = false }
            }
            if (actividad3) {
                ListaTareas { actividad3 = false }
            }
        }
    }
}

// Actividad 1
@Composable
fun SeccionDialogos(onCerrar: () -> Unit) {
    var textoPrincipal by remember { mutableStateOf("Pantalla Principal") }
    // Variables de control para cada diálogo
    var mostrarDialogo1 by remember { mutableStateOf(false) }
    var mostrarDialogo2 by remember { mutableStateOf(false) }
    var mostrarDialogo3 by remember { mutableStateOf(false) }
    var mostrarDialogo4 by remember { mutableStateOf(false) }
    var mostrarDialogo5 by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = textoPrincipal, fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(bottom = 20.dp))
        // Botón 1: Confirmación de Acción
        Button(onClick = { mostrarDialogo1 = true }) { Text("Confirmación de Acción") }
        Spacer(modifier = Modifier.height(8.dp))
        // Botón 2: Eliminación Permanente
        Button(onClick = { mostrarDialogo2 = true }) { Text("Eliminar Elemento") }
        Spacer(modifier = Modifier.height(8.dp))
        // Botón 3: Información Importante
        Button(onClick = { mostrarDialogo3 = true }) { Text("Aviso Importante") }
        Spacer(modifier = Modifier.height(8.dp))
        // Botón 4: Autenticación Requerida
        Button(onClick = { mostrarDialogo4 = true }) { Text("Requiere Autenticación") }
        Spacer(modifier = Modifier.height(8.dp))
        // Botón 5: Error Crítico
        Button(onClick = { mostrarDialogo5 = true }) { Text("Error Crítico") }
    }
    // Diálogos
    if (mostrarDialogo1) DialogoConfirmacion(onCerrar = { mostrarDialogo1 = false }, onConfirmar = {
        textoPrincipal = "Acción Confirmada"
        mostrarDialogo1 = false
    })
    if (mostrarDialogo2) DialogoEliminacion(onCerrar = { mostrarDialogo2 = false }, onEliminar = {
        textoPrincipal = "Eliminación Exitosa"
        mostrarDialogo2 = false
    })
    if (mostrarDialogo3) DialogoInformacion(onCerrar = { mostrarDialogo3 = false })
    if (mostrarDialogo4) DialogoAutenticacion(onCerrar = { mostrarDialogo4 = false }, onAutenticar = {
        textoPrincipal = "Usuario Autenticado"
        mostrarDialogo4 = false
    })
    if (mostrarDialogo5) DialogoErrorCritico(onCerrar = { mostrarDialogo5 = false }, onReintentar = {
        textoPrincipal = "Intento de Reintento"
        mostrarDialogo5 = false
    })
}

@Composable
fun DialogoConfirmacion(onCerrar: () -> Unit, onConfirmar: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text("Confirmación de Acción") },
        text = { Text("¿Estás seguro de que deseas continuar con esta acción?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Sí")
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar) {
                Text("No")
            }
        }
    )
}

@Composable
fun DialogoEliminacion(onCerrar: () -> Unit, onEliminar: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text("Eliminar Elemento") },
        text = { Text("Esta acción es irreversible. ¿Deseas eliminar este elemento?") },
        confirmButton = {
            TextButton(onClick = onEliminar) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun DialogoInformacion(onCerrar: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text("Aviso Importante") },
        text = { Text("Recuerda que los cambios realizados no se pueden deshacer.") },
        confirmButton = {
            TextButton(onClick = onCerrar) {
                Text("Entendido")
            }
        }
    )
}

@Composable
fun DialogoAutenticacion(onCerrar: () -> Unit, onAutenticar: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text("Requiere Autenticación") },
        text = { Text("Para continuar, necesitas autenticarte de nuevo.") },
        confirmButton = {
            TextButton(onClick = onAutenticar) {
                Text("Autenticar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun DialogoErrorCritico(onCerrar: () -> Unit, onReintentar: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text("Error Crítico") },
        text = { Text("Se ha producido un error crítico. ¿Deseas intentar nuevamente?") },
        confirmButton = {
            TextButton(onClick = onReintentar) {
                Text("Reintentar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar) {
                Text("Cancelar")
            }
        }
    )
}

// Actividad
@Composable
fun SpotifyApp(function: () -> Unit) {
    var pantallaSeleccionada by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(pantallaSeleccionada) { indicePantalla ->
                pantallaSeleccionada = indicePantalla
            }
        }
    ) { valoresPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresPadding)
                .background(Color.Black)
        ) {
            when (pantallaSeleccionada) {
                0 -> PantallaInicio()
                1 -> PantallaBusqueda()
                2 -> PantallaPerfil()
            }
        }
    }
}

@Composable
fun BarraNavegacionInferior(pantallaSeleccionada: Int, onPantallaSeleccionada: (Int) -> Unit) {
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = pantallaSeleccionada == 0,
            onClick = { onPantallaSeleccionada(0) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") },
            selected = pantallaSeleccionada == 1,
            onClick = { onPantallaSeleccionada(1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = pantallaSeleccionada == 2,
            onClick = { onPantallaSeleccionada(2) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
    }
}

@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Spotify", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniAlbum("Tu Playlist")
            MiniAlbum("Éxitos 2024")
            MiniAlbum("GYM")
        }
    }
}

@Composable
fun MiniAlbum(nombreAlbum: String) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.Gray, CircleShape)
        ) {
            // Placeholder para imagen de álbum
        }
        Text(
            nombreAlbum,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun PantallaBusqueda() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Buscar", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("¿Qué quieres escuchar?", color = Color.Gray) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tus mejores géneros", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ChipGenero("Phonk")
            ChipGenero("Rock")
            ChipGenero("Pop")
        }
    }
}

@Composable
fun ChipGenero(nombre: String) {
    Box(
        modifier = Modifier
            .background(Color.Gray, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(nombre, color = Color.White)
    }
}

@Composable
fun PantallaPerfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Tu Biblioteca", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Listas recientes", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniAlbum("Playlist 1")
            MiniAlbum("Playlist 2")
            MiniAlbum("Favoritos")
        }
    }
}

@Composable
fun ListaTareas(onClose: () -> Unit) {

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    principal()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DI_AlertDialogClonacionTheme {
        Greeting("Android")
    }
}