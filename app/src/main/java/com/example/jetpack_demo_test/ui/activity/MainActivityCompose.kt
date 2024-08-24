package com.example.jetpack_demo_test.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_demo_test.ui.compose.FabButton
import com.example.jetpack_demo_test.ui.compose.PetBreedScreen
import com.example.jetpack_demo_test.ui.theme.Jetpack_Demo_TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_Demo_TestTheme {
                val showBottomSheet =
                    rememberSaveable { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FabButton { showBottomSheet.value = true }
                    },
                ) { innerPadding ->
                    Column(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        PetBreedScreen(showBottomSheet)
                    }
                }
            }
        }
    }
}
