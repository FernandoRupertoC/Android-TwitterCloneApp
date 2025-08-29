package com.example.capacitacionandroid.ui.view.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capacitacionandroid.R
import com.example.capacitacionandroid.ui.view.core.components.ButtonPrimary
import com.example.capacitacionandroid.ui.view.core.components.ButtonSecondary
import com.example.capacitacionandroid.ui.view.core.components.CustomText
import com.example.capacitacionandroid.ui.view.core.components.CustomTextField
import com.example.capacitacionandroid.ui.view.core.components.DividerTwoLines
import com.example.capacitacionandroid.ui.view.core.components.SocialLoginButton

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    navigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit

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
            contentDescription = stringResource(R.string.login_screen_header_desc_icon),
            tint = Color.White
        )
        Spacer(Modifier.height(26.dp))

        CustomText(
            text = stringResource(R.string.login_screen_title_CustomText)
        )

        Spacer(Modifier.height(26.dp))

        SocialLoginButton(
            text = stringResource(R.string.login_screen_SocialLoginButton_google),
            icon = painterResource(R.drawable.ic_google),
            contentDescription = "Google Icon",
            onClick = { }
        )

        Spacer(Modifier.height(18.dp))

        SocialLoginButton(
            text = stringResource(R.string.login_screen_SocialLoginButton_apple),
            icon = painterResource(R.drawable.ic_apple),
            contentDescription = "Apple Icon",
            onClick = { }
        )

        Spacer(Modifier.height(8.dp))

        DividerTwoLines()

        Spacer(Modifier.height(8.dp))

        CustomTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChanged(it) }
        )

        Spacer(Modifier.height(18.dp))

        ButtonPrimary(text = stringResource(R.string.login_screen_ButtonPrimary)) {
            onLoginSuccess()
        }

        Spacer(Modifier.height(24.dp))

        ButtonSecondary(
            onClick = {

            }
        )

        Spacer(Modifier.weight(.8f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                stringResource(R.string.login_screen_text_account),
                color = Color.Gray
            )
            Spacer(Modifier.padding(2.dp))
            Text(
                stringResource(R.string.login_screen_text_button_register),
                color = Color(0xFF1DA1F2),
                modifier = Modifier.clickable(onClick = navigateToRegister)
            )
        }
        Spacer(Modifier.weight(1.2f))
    }
}