package com.example.capacitacionandroid.ui.view.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capacitacionandroid.R
import com.example.capacitacionandroid.ui.view.core.components.ButtonPrimary
import com.example.capacitacionandroid.ui.view.core.components.CustomText
import com.example.capacitacionandroid.ui.view.core.components.DividerTwoLines
import com.example.capacitacionandroid.ui.view.core.components.SocialLoginButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RegisterScreen(modifier: Modifier = Modifier,
                   viewModel: RegisterViewModel = viewModel (),
                   navigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier
            .background(Color.Black)
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(8.dp))
        Icon(
            modifier = Modifier
                .size(30.dp),
            painter = painterResource(R.drawable.ic_twitter),
            contentDescription = "X logo",
            tint = Color.White
        )
        Spacer(Modifier.height(26.dp))

        CustomText(
            text = "Únete a X hoy"
        )

        Spacer(Modifier.height(26.dp))

        SocialLoginButton(
            text = "Registrarse con Google",
            icon = painterResource(R.drawable.ic_google),
            contentDescription = "Google Icon",
            onClick = { }
        )

        Spacer(Modifier.height(18.dp))

        SocialLoginButton(
            text = "Registrarse con Apple",
            icon = painterResource(R.drawable.ic_apple),
            contentDescription = "Apple Icon",
            onClick = { }
        )

        Spacer(Modifier.height(8.dp))

        DividerTwoLines()

        Spacer(Modifier.height(8.dp))

        ButtonPrimary(text = "Crear cuenta") {

        }
        Spacer(Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                "Al registrarte, aceptas los ",
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 2.sp
            )
            Text(
                "Términos de servicio ",
                color = Color(0xFF1DA1F2),
                fontSize = 12.sp,
                lineHeight = 2.sp,
                modifier = Modifier.clickable {}
            )
            Text(
                "y la ",
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 2.sp
            )
            Text(
                "Política de privacidad",
                color = Color(0xFF1DA1F2),
                fontSize = 12.sp,
                lineHeight = 2.sp,
                modifier = Modifier.clickable {}
            )
            Text(
                ", incluida la política de ",
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 2.sp
            )
            Text(
                "Uso de Cookies.",
                color = Color(0xFF1DA1F2),
                fontSize = 12.sp,
                lineHeight = 2.sp,
                modifier = Modifier.clickable {}
            )
        }
        Spacer(Modifier.weight(.4f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                "¿Ya tienes una cuenta?",
                color = Color.Gray
            )
            Spacer(Modifier.padding(2.dp))
            Text(
                "Iniciar sesión",
                color = Color(0xFF1DA1F2),
                modifier = Modifier.clickable
                   ( onClick = navigateToLogin)

            )
        }
        Spacer(Modifier.weight(1.6f))

    }

}
