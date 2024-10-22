package com.agnes.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.agnes.composetutorial.ui.theme.ComposeTutorialTheme

class NavActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { Screen1(navController) }
                        composable(
                            route = "screen2/{name}",
                            arguments = listOf(navArgument("name") { type = NavType.StringType })
                        ) { backStackEntry ->
                            Screen2(
                                navController,
                                name = backStackEntry.arguments?.getString("name") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun Screen1(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Text(text = "Screen1",
            style = MaterialTheme.typography.titleSmall
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {navController.navigate("screen2/JohnDoe")}){
            Text(text = "Navigate to screen 2 with name")
        }
    }

}

@Composable
fun Screen2(navController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
    }
    Text(
        text = "Screen 2",
        style = MaterialTheme.typography.titleSmall
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Name : $name ",
        style = MaterialTheme.typography.bodyMedium
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { navController.navigate("Screen1") }) {
        Text(text = "Navigate to Screen 1 ")
    }
}
}


