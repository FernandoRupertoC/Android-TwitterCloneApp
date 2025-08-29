package com.example.capacitacionandroid.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Saludo(modifier: Modifier = Modifier) {
    Text("Hola mundo en jecpack compouse")
}

//@Preview(showBackground = true)
//@Composable
//fun SaludoPrev(){
//    Column
//    { Saludo("Hola") }
//}