/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.HistorialScreen
import com.example.lunchtray.ui.InicioScreen
import com.example.lunchtray.ui.MenuPrincipalScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.RegistroScreen

enum class EcoSpinScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout),
    MenuPrincipal(title = R.string.menu_principal),
    Registro(title=R.string.registro),
    Inicio(title = R.string.inicio),
    Historial(title = R.string.historial_pago)

}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun EcoSpinAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun EcoSpinApp() {
    //Create NavController
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = EcoSpinScreen.valueOf(
        backStackEntry?.destination?.route ?: EcoSpinScreen.Start.name
    )
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            EcoSpinAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = EcoSpinScreen.MenuPrincipal.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = EcoSpinScreen.MenuPrincipal.name) {
                MenuPrincipalScreen(
                    historialBotonClick = {
                                          navController.navigate(EcoSpinScreen.Historial.name)
                    },
                    modifier=Modifier
                )
            }

            composable(route = EcoSpinScreen.Registro.name) {
                RegistroScreen()
            }

            composable(route = EcoSpinScreen.Historial.name) {
                HistorialScreen()
            }

            composable(route = EcoSpinScreen.Inicio.name) {
                InicioScreen()
            }

        }
    }
}
