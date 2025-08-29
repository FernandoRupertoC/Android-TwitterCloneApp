package com.example.capacitacionandroid.ui.view.home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.capacitacionandroid.R
import com.example.capacitacionandroid.data.datasource.response.NewsResult
import com.example.capacitacionandroid.data.datasource.response.User
import com.example.capacitacionandroid.ui.view.core.navigation.Following
import com.example.capacitacionandroid.ui.view.core.navigation.Timeline
import com.example.capacitacionandroid.ui.view.following.FollowingViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun HomeScreen(
    //viewModel: FollowingViewModel, navController: NavHostController
    followingViewModel: FollowingViewModel,
    forYouViewModel: ForYouViewModel,
    navController: NavHostController
) {
//    val users by viewModel.users.collectAsState()
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
    val users by followingViewModel.users.collectAsState()
    val news by forYouViewModel.news.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableStateOf(0) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
                drawerContentColor = Color.White,
                drawerContainerColor = Color(0xFF15202B),
                drawerShape = RoundedCornerShape(0),
                drawerTonalElevation = 10.dp
            ) {
                ContentDrawerSheet(
                    navController = navController,
                    onCloseDrawer = { scope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                Column {
                    CustomTopBar (
                        onNavSelected = { scope.launch { drawerState.open() } },
                        onSettingsClicked = { navController.navigate(Timeline) }
                    )
                    //CustomTapBar(viewModel, navController)
                    CustomTapBar(
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { selectedTabIndex = it },
                        users = users,
                        news = news
                    )
                }
            },
            bottomBar = {
                BottomNavigationBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFF1DA1F2),
                    shape = RoundedCornerShape(58.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Nuevo tweet", tint = Color.White)
                }
            },
            containerColor = Color(0xFF15202B)
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                when (selectedTabIndex) {
                    0 -> items(news) { article ->
                        NewsTwitt(article)
                        TuitDivider()
                    }

                    1 -> items(users) { user ->
                        CustomTwitt(user)
                        TuitDivider()
                    }
                }
            }


//            LazyColumn(
//                contentPadding = innerPadding,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(users) { user ->
//                    CustomTwitt(user)
//                    TuitDivider()
//                }
//            }
        }
    }
}

@Composable
fun CustomTopBar(onNavSelected: () -> Unit,
                 onSettingsClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                Color(0xFF15202B)
            )
    ) {
        AsyncImage(
            model = "https://avatars.githubusercontent.com/u/124626515?s=400&u=6fe70b34f517b136f7df89bf31ae6e7e130f43bd&v=4",
            contentDescription = null,
            modifier = Modifier
                .clickable { onNavSelected() }
                .padding(start = 16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .align(Alignment.CenterStart),
            onError = {
                Log.i("image", "Ha ocurrido un error ${it.result.throwable?.message}")
            }
        )


        Icon(
            painter = painterResource(R.drawable.ic_twitter),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )

        Icon(
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .clickable { onSettingsClicked()  }
                .size(40.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )

    }
}

@Composable
fun CustomTapBar(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    users: List<User>,
    news: List<NewsResult>
) {
    //var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Para ti", "Siguiendo")

    Column {
//        TabRow(
//            selectedTabIndex = selectedTabIndex,
//            indicator = { tabPositions ->
//                TabRowDefaults.SecondaryIndicator(
//                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
//                    color = Color(0xFF1DA1F2)
//                )
//            },
//            containerColor = Color(0xFF15202B),
//            contentColor = Color.White
//        ) {
//            tabs.forEachIndexed { index, title ->
//                Tab(
//                    selected = selectedTabIndex == index,
//                    onClick = { selectedTabIndex = index },
//                ) {
//                    Text(
//                        text = title,
//                        modifier = Modifier.padding(vertical = 16.dp),
//                        color = if (selectedTabIndex == index) Color.White else Color.Gray
//                    )
//                }
//            }
//        }
        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFF15202B),
                indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFF1DA1F2)
                )
            }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { onTabSelected(index) }) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray
                        )
                    }
                }
            }
        }
    }


    when (selectedTabIndex) {
        0 -> {
            val forYouVM: ForYouViewModel = viewModel()
            ForYouTabContent(forYouVM)
        }

        1 -> {
            val followingVM: FollowingViewModel = viewModel()
            FollowingTabContent(followingVM)
        }
    }
}

@Composable
fun ForYouTabContent(viewModel: ForYouViewModel = ForYouViewModel()) {
    val news by viewModel.news.collectAsState()

    LazyColumn {
        items(news) { article ->
            NewsTwitt(article)
            TuitDivider()
        }
    }
}


@Composable
fun FollowingTabContent(viewModel: FollowingViewModel) {
    val users by viewModel.users.collectAsState()

    LazyColumn {
        items(users) { user ->
            CustomTwitt(user)
            TuitDivider()
        }
    }
}



@Composable
fun CustomTwitt(user: User) {
    val randomHour = Random.nextInt(1, 25)
    var chat by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf(false) }
    var like by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF15202B))
            .padding(16.dp)
    ) {
        AsyncImage(
            model = user.picture.medium,
            contentDescription = "Imagen de ${user.name}",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextTitle("${user.name.first} ${user.name.last}")

                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "@${user.login.username}",
                    color = Color(0xFF8899A6),
                    maxLines = 1,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1.3f)
                )
                Text(
                    text = "· ${randomHour}h",
                    color = Color(0xFF8899A6),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painterResource(id = R.drawable.ic_tres),
                    "",
                    Modifier.size(14.dp),
                    tint = Color.Gray
                )
            }

            TextBody(Modifier.padding(0.dp), "#NuevaFotoDePerfil")
            Spacer(Modifier.height(4.dp))

            AsyncImage(
                model = user.picture.large,
                contentDescription = "Imagen de ${user.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))


            InteractionButtons(
                chat = chat,
                rt = rt,
                like = like,
                onChatClick = { chat = !chat },
                onRtClick = { rt = !rt },
                onLikeClick = { like = !like }
            )

        }

    }
}

@Composable
fun ContentDrawerSheet(
    navController: NavController,
    onCloseDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://avatars.githubusercontent.com/u/124626515?s=400&u=6fe70b34f517b136f7df89bf31ae6e7e130f43bd&v=4",
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        Text("Fer Ruperto", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("@FerRupert", color = Color.Gray, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        Row {
            Text("16", fontWeight = FontWeight.Bold, color = Color.White)
            Text(" Siguiendo  ", modifier = Modifier.clickable {
                navController.navigate(Following)
                onCloseDrawer()
            }, color = Color.Gray)
            Text("496", fontWeight = FontWeight.Bold, color = Color.White)
            Text(" Seguidores", color = Color.Gray)
        }

        Spacer(Modifier.height(32.dp))

        DrawerItem("Perfil", painterResource(R.drawable.ic_profile)) { }
        DrawerItem("Chat", painterResource(R.drawable.ic_chat)) { }
        DrawerItem("Elementos guardados", painterResource(R.drawable.ic_saved)) { }
        DrawerItem(
            "Empleos",
            painterResource(R.drawable.facebook_jobs_facebook_social_media_application_brand_job_icon_197314)
        ) { }
        DrawerItem("Listas", painterResource(R.drawable.ic_lists)) { }
        DrawerItem("Espacios", painterResource(R.drawable.ic_spaces)) { }
        DrawerItem("Monetización", painterResource(R.drawable.ic_money)) { }
    }
}


@Composable
private fun DrawerItem(
    title: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 0.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(20.dp))
        Text(title, color = Color.White, fontSize = 16.sp)
    }

}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        modifier = Modifier.height(48.dp),
        containerColor = Color(0xFF15202B),
        windowInsets = WindowInsets(0)
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(Icons.Default.MailOutline, contentDescription = "Mensajes", tint = Color.White)
        }
    }
}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1

    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSelected) {
            selectedIcon()
        } else {
            unselectedIcon()
        }

        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }

}

@Composable
fun TextTitle(title: String) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
}

@Composable
fun TextBody(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, color = Color(0xFF1DA1F2))
}

@Composable
fun DefaultText(modifier: Modifier = Modifier, title: String) {
    Text(modifier = modifier, text = title, color = Color.Gray, fontSize = 12.sp)
}

@Composable
fun TuitDivider() {
    HorizontalDivider(
        Modifier
            .padding(top = 4.dp)
            .height(1.dp)
            .fillMaxWidth(),
        DividerDefaults.Thickness, color = Color(0xFF7E8B98)
    )
}

@Composable
fun NewsTwitt(article: NewsResult) {
    var chat by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf(false) }
    var like by remember { mutableStateOf(false) }
    val randomHour = (1..24).random()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF15202B))
            .padding(16.dp)
    ) {
        // Imagen de perfil
        AsyncImage(
            model = article.source_icon
                ?: "https://static.vecteezy.com/system/resources/previews/026/619/142/original/default-avatar-profile-icon-of-social-media-user-photo-image-vector.jpg", //poner una imagen de la red en caso de ser null
            contentDescription = "Fuente",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            // Nombre y usuario
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.creator?.firstOrNull() ?: "Sin nombre",
                    color = Color.White,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "@${article.creator?.firstOrNull() ?: "user"}",
                    color = Color(0xFF8899A6),
                    maxLines = 1,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                //Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "·${randomHour}h",
                    color = Color(0xFF8899A6),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painterResource(id = R.drawable.ic_tres),
                    "",
                    Modifier.size(14.dp),
                    tint = Color.Gray
                )
            }

            //Spacer(modifier = Modifier.height(4.dp))

            // Título (como el contenido del tweet)
            Text(
                text = article.title ?: "Sin texto",
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Imagen del artículo (si existe)
            //article.imageURL?.let { imageUrl ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    //.data(article.imageURL ?: "https://via.placeholder.com/250")
                    .data(
                        article.imageURL
                            ?: "https://static.vecteezy.com/system/resources/previews/006/059/989/non_2x/crossed-camera-icon-avoid-taking-photos-image-is-not-available-illustration-free-vector.jpg"
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = article.title ?: "Imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))
            // }

            // Botones de interacción
            InteractionButtons(
                chat = chat,
                rt = rt,
                like = like,
                onChatClick = { chat = !chat },
                onRtClick = { rt = !rt },
                onLikeClick = { like = !like }
            )
        }
    }
}

@Composable
fun InteractionButtons(
    chat: Boolean,
    rt: Boolean,
    like: Boolean,
    onChatClick: () -> Unit,
    onRtClick: () -> Unit,
    onLikeClick: () -> Unit
) {

    Row {
        SocialIcon(
            modifier = Modifier.weight(1f),
            isSelected = chat,
            unselectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_chat),
                    contentDescription = "Comentar",
                    tint = Color(0xFF7E8B98)
                )
            },
            selectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_chat_filled),
                    contentDescription = "Comentar",
                    tint = Color(0xFF7E8B98)
                )
            }
        ) { onChatClick() }

        SocialIcon(
            modifier = Modifier.weight(1f),
            isSelected = rt,
            unselectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_rt),
                    contentDescription = "Retweet",
                    tint = Color(0xFF7E8B98)
                )
            },
            selectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_rt),
                    contentDescription = "Retweet",
                    tint = Color(0xFF00FF27)
                )
            }
        ) { onRtClick() }

        SocialIcon(
            modifier = Modifier.weight(1f),
            isSelected = like,
            unselectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_like),
                    contentDescription = "Me gusta",
                    tint = Color(0xFF7E8B98)
                )
            },
            selectedIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "Me gusta",
                    tint = Color(0xFFFF0000)
                )
            }
        ) { onLikeClick() }
    }
}
