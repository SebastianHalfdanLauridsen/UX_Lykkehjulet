package com.example.lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lykkehjulet.ui.LykkeHjulApp
import com.example.lykkehjulet.ui.theme.LykkehjuletTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LykkehjuletTheme {
                // A surface container using the 'background' color from the theme
                Surface( //TODO maybe remove/move this surface?
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LykkeHjulApp()

                    /*Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        },
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Material 3 test")
                                },
                                colors = TopAppBarDefaults.mediumTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    ) { values ->
                        LazyColumn(contentPadding = values) {
                            items(20) {
                                ImageCard(
                                    title = "This is a title",
                                    description = "This is a description below a title, the description should be concrete and descriptive, duh. But also not too long.",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LykkehjuletTheme {
        Greeting("Android")
    }
}