package refixbody.fix.foodapplication.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import refixbody.fix.foodapplication.presentation.ui.screens.MainScreen
import refixbody.fix.foodapplication.presentation.ui.theme.FoodApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            FoodApplicationTheme {
                MainScreen()
            }

        }
    }

}

