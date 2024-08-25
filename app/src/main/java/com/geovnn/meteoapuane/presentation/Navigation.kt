package com.geovnn.meteoapuane.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Satellite
import androidx.compose.material.icons.outlined.Traffic
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geovnn.meteoapuane.R
import com.geovnn.meteoapuane.presentation.confini.ConfiniScreen
import com.geovnn.meteoapuane.presentation.confini.ConfiniViewModel
import com.geovnn.meteoapuane.presentation.home.HomeScreen
import com.geovnn.meteoapuane.presentation.home.HomeViewModel
import com.geovnn.meteoapuane.presentation.incendi.IncendiScreen
import com.geovnn.meteoapuane.presentation.incendi.IncendiViewModel
import com.geovnn.meteoapuane.presentation.montagna.MontagnaScreen
import com.geovnn.meteoapuane.presentation.montagna.MontagnaViewModel
import com.geovnn.meteoapuane.presentation.nowcasting.NowcastingScreen
import com.geovnn.meteoapuane.presentation.nowcasting.NowcastingViewModel
import com.geovnn.meteoapuane.presentation.provincia.ProvinciaScreen
import com.geovnn.meteoapuane.presentation.provincia.ProvinciaViewModel
import com.geovnn.meteoapuane.presentation.viabilita.ViabilitaScreen
import com.geovnn.meteoapuane.presentation.viabilita.ViabilitaViewModel
import com.geovnn.meteoapuane.presentation.webcam.WebcamScreen
import com.geovnn.meteoapuane.presentation.webcam.WebcamViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val provinciaViewModel = hiltViewModel<ProvinciaViewModel>()
    val confiniViewModel = hiltViewModel<ConfiniViewModel>()
    val montagnaViewModel = hiltViewModel<MontagnaViewModel>()
    val viabilitaViewModel = hiltViewModel<ViabilitaViewModel>()
    val incendiViewModel = hiltViewModel<IncendiViewModel>()
    val webcamViewModel = hiltViewModel<WebcamViewModel>()
    val nowcastingViewModel = hiltViewModel<NowcastingViewModel>()


    val homeState by homeViewModel.state.collectAsState()
    val provinciaState by provinciaViewModel.state.collectAsState()
    val confiniState by confiniViewModel.state.collectAsState()
    val montagnaState by montagnaViewModel.state.collectAsState()
    val viabilitaState by viabilitaViewModel.state.collectAsState()
    val incendiState by incendiViewModel.state.collectAsState()
    val webcamState by webcamViewModel.state.collectAsState()
    val nowcastingState by nowcastingViewModel.state.collectAsState()
    val navController = rememberNavController()
    val drawerItems = listOf(
        DrawerItem(
            title = "Home",
            selectedIcon = Icons.Outlined.Home,
            unselectedIcon = Icons.Filled.Home,
            destination = "Home_screen"
        ),
        DrawerItem(
            title = "Provincia",
            selectedIcon = Icons.Outlined.Cloud,
            unselectedIcon = Icons.Filled.Cloud,
            destination = "provincia_screen"
        ),
        DrawerItem(
            title = "Confini",
            selectedIcon = Icons.Outlined.Cloud,
            unselectedIcon = Icons.Filled.Cloud,
            destination = "confini_screen"
        ),
        DrawerItem(
            title = "Montagna",
            selectedIcon = Icons.Outlined.Landscape,
            unselectedIcon = Icons.Filled.Landscape,
            destination = "montagna_screen"
        ),
        DrawerItem(
            title = "Viabilita",
            selectedIcon = Icons.Outlined.Traffic,
            unselectedIcon = Icons.Filled.Traffic,
            destination = "viabilita_screen"
        ),
        DrawerItem(
            title = "Incendi",
            selectedIcon = Icons.Outlined.LocalFireDepartment,
            unselectedIcon = Icons.Filled.LocalFireDepartment,
            destination = "incendi_screen"
        ),
        DrawerItem(
            title = "Webcam",
            selectedIcon = Icons.Outlined.CameraAlt,
            unselectedIcon = Icons.Filled.CameraAlt,
            destination = "webcam_screen"
        ),
        DrawerItem(
            title = "Nowcasting",
            selectedIcon = Icons.Outlined.Satellite,
            unselectedIcon = Icons.Filled.Satellite,
            destination = "nowcasting_screen"
        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val scope = rememberCoroutineScope()
        var showAboutDialog by remember { mutableStateOf(false) }
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        if (showAboutDialog) {
            AboutDialog { showAboutDialog = false }
        }
        Scaffold(
            backgroundColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = { Text(text = "MeteoApuane",
                        color = MaterialTheme.colorScheme.onSurface) },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                showAboutDialog = true
                            }
                        }) {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "About",
                                tint = MaterialTheme.colorScheme.onSurface
                            )                        }
                    },
//                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            bottomBar = {
//
//                NavigationBar(
//                    modifier = Modifier
//                ) {
//                    drawerItems.forEachIndexed { index, item ->
//                        NavigationBarItem(
////                            modifier = Modifier.background(Color.Red),
//                            selected = selectedItemIndex == index,
//                            onClick = {
//                                selectedItemIndex = index
//                                scope.launch {
//                                    navController.navigate(item.destination) {
//                                        popUpTo(0) {
//                                            inclusive = true
//                                            saveState = true
//                                        }
//                                    }
//                                }
//                            },
//                            alwaysShowLabel = true,
//                            label = {
//                                Text(
//                                    text = item.title,
//                                    modifier = Modifier,
//                                )
//                            },
//                            icon = {
//                                Icon(
//                                    imageVector = item.selectedIcon,
//                                    contentDescription = item.title
//                                )
//                            }
//                        )
//                    }
//                }

                val navScrollState = rememberScrollState()

                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    actions = {

                        Box(
                            modifier= Modifier
                        ) {

                            Row(
                                modifier = Modifier.horizontalScroll(navScrollState)
//                                    .align(Alignment.)
                            ){
                                drawerItems.forEachIndexed { index, item ->
                                    Column(
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .clickable {
                                                if (selectedItemIndex != index) {
                                                    selectedItemIndex = index
                                                    scope.launch {
                                                        navController.navigate(item.destination) {
                                                            popUpTo(0) {
                                                                inclusive = true
                                                                saveState = true
                                                            }
                                                        }
                                                    }
                                                }

                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        IconButton(
                                            modifier = Modifier

                                                .align(Alignment.CenterHorizontally)
                                                .background(
                                                    color = if (selectedItemIndex == index)
                                                        MaterialTheme.colorScheme.secondaryContainer else
                                                        Color.Transparent,
                                                    shape = MaterialTheme.shapes.large
                                                ),
                                            onClick = {
                                                if (selectedItemIndex!=index) {
                                                    selectedItemIndex = index
                                                    scope.launch {
                                                        navController.navigate(item.destination) {
                                                            popUpTo(0) {
                                                                inclusive = true
                                                                saveState = true
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally),
                                                imageVector = if (selectedItemIndex == index)
                                                    item.unselectedIcon else
                                                    item.selectedIcon,
                                                contentDescription = "Localized description",
                                                tint = if (selectedItemIndex == index)
                                                    MaterialTheme.colorScheme.onSecondaryContainer else
                                                    MaterialTheme.colorScheme.onSurface,
                                            )
                                        }
                                        Text(
                                            text = item.title,
                                            modifier = Modifier
//                                            .padding(end = 10.dp)
                                                .align(Alignment.CenterHorizontally),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            textAlign = TextAlign.Center,
                                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                                            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                                            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                                            fontStyle = MaterialTheme.typography.labelLarge.fontStyle
                                        )
                                    }

                                }
                            }
                            androidx.compose.animation.AnimatedVisibility(
                                visible = navScrollState.value<navScrollState.maxValue,
                                modifier = Modifier
                                    .fillMaxWidth(.10f)
                                    .fillMaxHeight()
                                    .align(Alignment.CenterEnd)
                            ) {
                                Box(
                                    modifier = Modifier

                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    MaterialTheme.colorScheme.onSecondaryContainer
                                                )
                                            )
                                        )
                                )
                            }
//                            if (navScrollState.value<navScrollState.maxValue) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth(.15f)
//                                        .fillMaxHeight()
//                                        .align(Alignment.CenterEnd)
//                                        .background(
//                                            Brush.horizontalGradient(
//                                                colors = listOf(
//                                                    Color.Transparent,
//                                                    MaterialTheme.colorScheme.secondary
//                                                )
//                                            )
//                                        )
//                                )
//                            }
                            androidx.compose.animation.AnimatedVisibility(
                                visible = navScrollState.value>0,
                                modifier = Modifier
                                    .fillMaxWidth(.10f)
                                    .fillMaxHeight()
                                    .align(Alignment.CenterStart)
                            ) {
                                Box(
                                    modifier = Modifier

                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(
                                                    MaterialTheme.colorScheme.onSecondaryContainer,
                                                    Color.Transparent
                                                )
                                            )
                                        )
                                )
                            }
//                            if (navScrollState.value>0) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth(.15f)
//                                        .fillMaxHeight()
//                                        .align(Alignment.CenterStart)
//                                        .background(
//                                            Brush.horizontalGradient(
//                                                colors = listOf(
//                                                    MaterialTheme.colorScheme.secondary,
//                                                    Color.Transparent
//                                                )
//                                            )
//                                        )
//                                )
//                            }
                        }

                    },
                )
            },

        ) { paddingValue ->
            NavHost(
                modifier = Modifier.padding(paddingValue),
                navController = navController, startDestination = Screen.HomeScreen.route) {
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(
                        uiState = homeState,
                        refreshData = { homeViewModel.updateData() },
                    )
                }
                composable(route = Screen.ProvinciaScreen.route) {
                    ProvinciaScreen(
                        uiState = provinciaState,
                        refreshData = { provinciaViewModel.updateData() }
                    )
                }
                composable(route = Screen.ConfiniScreen.route) {
                    ConfiniScreen(
                        uiState = confiniState,
                        refreshData = { confiniViewModel.updateData() }
                    )
                }
                composable(route = Screen.MontagnaScreen.route) {
                    MontagnaScreen(
                        uiState = montagnaState,
                        refreshData = { montagnaViewModel.updateData() }
                    )
                }
                composable(route = Screen.ViabilitaScreen.route) {
                    ViabilitaScreen(
                        uiState = viabilitaState,
                        refreshData = { viabilitaViewModel.updateData() }
                    )
                }
                composable(route = Screen.IncendiScreen.route) {
                    IncendiScreen(
                        uiState = incendiState,
                        refreshData = { incendiViewModel.updateData() }
                    )
                }
                composable(route = Screen.WebcamScreen.route) {
                    WebcamScreen(
                        uiState = webcamState,
                        refreshData = { webcamViewModel.updateData() }
                    )
                }
                composable(route = Screen.NowcastingScreen.route) {
                    NowcastingScreen(
                        uiState = nowcastingState,
                        refreshData = { nowcastingViewModel.updateData() }
                    )
                }
            }
        }
//        ModalNavigationDrawer(
//            drawerContent = {
//                ModalDrawerSheet {
//                    Spacer(Modifier.height(32.dp))
//                    drawerItems.forEachIndexed { index, item ->
//                        NavigationDrawerItem(
//                            label = {
//                                Text(text = item.title)
//                            },
//                            selected = index == selectedItemIndex,
//                            onClick = {
//                                selectedItemIndex = index
//                                scope.launch {
//                                    navController.navigate(item.destination){
//                                        popUpTo(0){
//                                            inclusive = true
//                                            saveState = true
//                                        }
//                                    }
//                                    drawerState.close()
//                                }
//                            },
//                            icon = {
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex) {
//                                        item.selectedIcon
//                                    } else item.unselectedIcon,
//                                    contentDescription = item.title
//                                )
//                            },
//                            modifier = Modifier
//                                .padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
//                    }
//                    Spacer(modifier = Modifier.weight(1.0f))
//                    NavigationDrawerItem(
//                        label = {
//                            Text(text = "About")
//                        },
//                        selected = false,
//                        onClick = {
//                            scope.launch {
//                                showAboutDialog = true
//                                drawerState.close()
//                            }
//                        },
//                        icon = {
//                            Icon(
//                                Icons.Filled.Info,
//                                contentDescription = "About"
//                            )
//                        },
//                        modifier = Modifier
//                            .padding(NavigationDrawerItemDefaults.ItemPadding)
//                    )
//                    Spacer(Modifier.height(32.dp))
//                }
//            },
//            drawerState = drawerState,
//        ) {
//
//            NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
//                composable(route = Screen.HomeScreen.route) {
//                    HomeScreen(
//                        uiState = homeState,
//                        onMenuClick = { scope.launch { drawerState.open() } },
//                        refreshData = { homeViewModel.updateData() }
//                    )
//                }
//                composable(route = Screen.ProvinciaScreen.route) {
//                    ProvinciaScreen(
//                        uiState = provinciaState,
//                        onMenuClick = { scope.launch { drawerState.open() } },
//                        refreshData = { provinciaViewModel.updateData() }
//                    )
//                }
//                composable(route = Screen.ConfiniScreen.route) {
//                    ConfiniScreen(
//                        uiState = confiniState,
//                        onMenuClick = { scope.launch { drawerState.open() } },
//                        refreshData = { confiniViewModel.updateData() }
//                    )
//                }
//                composable(route = Screen.MontagnaScreen.route) {
//                    MontagnaScreen(
//                        uiState = montagnaState,
//                        onMenuClick = { scope.launch { drawerState.open() } },
//                        refreshData = { montagnaViewModel.updateData() }
//                    )
//                }
//                composable(route = Screen.ViabilitaScreen.route) {
//                    ViabilitaScreen(
//                        uiState = viabilitaState,
//                        onMenuClick = { scope.launch { drawerState.open() } },
//                        refreshData = { viabilitaViewModel.updateData() }
//                    )
//                }
//                composable(route = Screen.IncendiScreen.route) {
//                    IncendiScreen()
//                }
//                composable(route = Screen.WebcamScreen.route) {
//                    WebcamScreen()
//                }
//                composable(route = Screen.NowcastingScreen.route) {
//                    NowcastingScreen()
//                }
//            }
//
//        }
    }
}
data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val destination: String
)

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ProvinciaScreen : Screen("provincia_screen")
    object ConfiniScreen : Screen("confini_screen")
    object MontagnaScreen : Screen("montagna_screen")
    object ViabilitaScreen : Screen("viabilita_screen")
    object IncendiScreen : Screen("incendi_screen")
    object WebcamScreen : Screen("webcam_screen")
    object NowcastingScreen : Screen("nowcasting_screen")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutDialog(
    closeDialog: () -> Unit
) {
    val context = LocalContext.current
    val intentGithub = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/geovnn")) }
    val intentWebsite = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.meteoapuane.it/"))

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = LocalContentColor.current)) {
            append("I contenuti presenti appartengono all'associazione MeteoApuane e sono disponibili pubblicamente sul sito ")
        }
        pushStringAnnotation(tag = "website", annotation = "https://www.meteoapuane.it/")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("meteoapuane.it")
        }
        pop()
    }

    AlertDialog(
        onDismissRequest = { closeDialog() },
    ) {
        val githubBadge = painterResource(id = R.drawable.github_badge)
        Card(
            Modifier
                .wrapContentSize()
        ) {
            Column (
                Modifier
                    .wrapContentSize()
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MeteoApuane\nby Giovanni",
                    Modifier
                        .wrapContentSize(),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painter = githubBadge,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(50.dp)
                        .size(120.dp)
                        .wrapContentSize()
                        .clickable {
                            context.startActivity(intentGithub)
                        }
                )
                ClickableText(text = annotatedString, style = MaterialTheme.typography.bodySmall, onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "website", start = offset, end = offset).firstOrNull()?.let {
                        context.startActivity(intentWebsite)
                    }
                })
            }
        }
    }
}