package com.example.capacitacionandroid.ui.view.timelineSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capacitacionandroid.R

@Composable
fun TimelineScreen(
    navigateUp: () -> Unit
) {
    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    Color(0xFF15202B)
                )
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable { navigateUp() }
            )
            Text(
                "Configuración de Cronología", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 24.dp)
            )
        }
        HorizontalDivider(
            Modifier
                .padding(top = 4.dp)
                .height(1.dp)
                .fillMaxWidth(),
            DividerDefaults.Thickness, color = Color(0xFF7E8B98)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0xFF15202B)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nada que mostrar aún",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    "Intente fijar una ",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    lineHeight = 2.sp
                )
                Text(
                    "Lista ",
                    color = Color(0xFF1DA1F2),
                    fontSize = 12.sp,
                    lineHeight = 2.sp,
                    modifier = Modifier.clickable {}
                )
                Text(
                    "o una ",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    lineHeight = 2.sp
                )
                Text(
                    "Comunidad ",
                    color = Color(0xFF1DA1F2),
                    fontSize = 12.sp,
                    lineHeight = 2.sp,
                    modifier = Modifier.clickable {}
                )
                Text(
                    "para tener acceso más fácil a su contenido favorito",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    lineHeight = 2.sp
                )
            }
            }
        }
    }
}