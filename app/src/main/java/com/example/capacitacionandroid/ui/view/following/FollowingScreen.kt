package com.example.capacitacionandroid.ui.view.following

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.capacitacionandroid.data.datasource.response.User
import com.example.capacitacionandroid.R


@Composable
fun FollowingScreen(viewModel: FollowingViewModel, navController: NavController) {
    val users by viewModel.users.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF15202B)
            )
    ) {
        FollowingTopAppBar(navController)
        HorizontalDivider(
            Modifier
                .height(0.dp)
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            DividerDefaults.Thickness,
            color = Color(0xFF7E8B98)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(user.picture.medium),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${user.name.first} ${user.name.last}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "@${user.login.username}",
                color = Color.Gray,
                fontSize = 14.sp
            )

        }

        BotonSiguiendo()
    }
}


@Composable
fun FollowingTopAppBar(navController: NavController) {
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
                .clickable { navController.popBackStack() }
        )
        Text(
            "Siguiendo", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 58.dp)
        )

        Icon(
            painter = painterResource(R.drawable.ic_profile_add),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun BotonSiguiendo() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Siguiendo",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


