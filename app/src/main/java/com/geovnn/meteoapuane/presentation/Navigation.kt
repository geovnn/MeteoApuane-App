package com.geovnn.meteoapuane.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import com.geovnn.meteoapuane.presentation.montagna.MontagnaScreen
import com.geovnn.meteoapuane.presentation.montagna.MontagnaViewModel
import com.geovnn.meteoapuane.presentation.nowcasting.NowcastingScreen
import com.geovnn.meteoapuane.presentation.provincia.ProvinciaScreen
import com.geovnn.meteoapuane.presentation.provincia.ProvinciaViewModel
import com.geovnn.meteoapuane.presentation.viabilita.ViabilitaScreen
import com.geovnn.meteoapuane.presentation.viabilita.ViabilitaViewModel
import com.geovnn.meteoapuane.presentation.webcam.WebcamScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation() {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val provinciaViewModel = hiltViewModel<ProvinciaViewModel>()
    val confiniViewModel = hiltViewModel<ConfiniViewModel>()
    val montagnaViewModel = hiltViewModel<MontagnaViewModel>()
    val viabilitaViewModel = hiltViewModel<ViabilitaViewModel>()

    val homeState by homeViewModel.state.collectAsState()
    val provinciaState by provinciaViewModel.state.collectAsState()
    val confiniState by confiniViewModel.state.collectAsState()
    val montagnaState by montagnaViewModel.state.collectAsState()
    val viabilitaState by viabilitaViewModel.state.collectAsState()
    val navController = rememberNavController()
    val drawerItems = listOf(
        DrawerItem(
            title = "Homepage",
            selectedIcon = Icons.Outlined.Home,
            unselectedIcon = Icons.Filled.Home,
            destination = "Home_screen"
        ),
        DrawerItem(
            title = "Previsioni Provincia",
            selectedIcon = Icons.Outlined.Cloud,
            unselectedIcon = Icons.Filled.Cloud,
            destination = "provincia_screen"
        ),
        DrawerItem(
            title = "Previsioni 3 Confini",
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
//        DrawerItem(
//            title = "Meteo-Viabilita",
//            selectedIcon = Icons.Outlined.Traffic,
//            unselectedIcon = Icons.Filled.Traffic,
//            destination = "viabilita_screen"
//        ),
//        DrawerItem(
//            title = "Incendi Boschivi",
//            selectedIcon = Icons.Outlined.LocalFireDepartment,
//            unselectedIcon = Icons.Filled.LocalFireDepartment,
//            destination = "incendi_screen"
//        ),
//        DrawerItem(
//            title = "Webcam",
//            selectedIcon = Icons.Outlined.CameraAlt,
//            unselectedIcon = Icons.Filled.CameraAlt,
//            destination = "webcam_screen"
//        ),
//        DrawerItem(
//            title = "Nowcasting",
//            selectedIcon = Icons.Outlined.Satellite,
//            unselectedIcon = Icons.Filled.Satellite,
//            destination = "nowcasting_screen"
//        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var showAboutDialog by remember { mutableStateOf(false) }
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

        if (showAboutDialog) {
            AboutDialog { showAboutDialog = false }
        }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(32.dp))
                    drawerItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    navController.navigate(item.destination){
                                        popUpTo(0){
                                            inclusive = true
                                            saveState = true
                                        }
                                    }
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1.0f))
                    NavigationDrawerItem(
                        label = {
                            Text(text = "About")
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                showAboutDialog = true
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "About"
                            )
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    Spacer(Modifier.height(32.dp))
                }
            },
            drawerState = drawerState,
        ) {
            NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(
                        uiState = homeState,
                        onMenuClick = { scope.launch { drawerState.open() } },
                        refreshData = { homeViewModel.updateData() }
                    )
                }
                composable(route = Screen.ProvinciaScreen.route) {
                    ProvinciaScreen(
                        uiState = provinciaState,
                        onMenuClick = { scope.launch { drawerState.open() } },
                        refreshData = { provinciaViewModel.updateData() }
                    )
                }
                composable(route = Screen.ConfiniScreen.route) {
                    ConfiniScreen(
                        uiState = confiniState,
                        onMenuClick = { scope.launch { drawerState.open() } },
                        refreshData = { confiniViewModel.updateData() }
                    )
                }
                composable(route = Screen.MontagnaScreen.route) {
                    MontagnaScreen(
                        uiState = montagnaState,
                        onMenuClick = { scope.launch { drawerState.open() } },
                        refreshData = { montagnaViewModel.updateData() }
                    )
                }
                composable(route = Screen.ViabilitaScreen.route) {
                    ViabilitaScreen(
                        uiState = viabilitaState,
                        onMenuClick = { scope.launch { drawerState.open() } },
                        refreshData = { viabilitaViewModel.updateData() }
                    )
                }
                composable(route = Screen.IncendiScreen.route) {
                    IncendiScreen()
                }
                composable(route = Screen.WebcamScreen.route) {
                    WebcamScreen()
                }
                composable(route = Screen.NowcastingScreen.route) {
                    NowcastingScreen()
                }
            }
        }
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
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/geovnn")) } //TODO Use global string

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
                            context.startActivity(intent)
                        }
                )
            }
        }
    }
}