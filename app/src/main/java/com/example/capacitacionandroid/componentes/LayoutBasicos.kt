package com.example.capacitacionandroid.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EjemploLayout(modifier: Modifier = Modifier){
    Column {
        Text("Elemento 1")
        Text("Elemento 2")

        Row {
            Text("Fila 1")
            Text("Fila 2")
        }
        Text("Elemento 4")
        Box(){
            
        }
    }
}