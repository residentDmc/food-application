package refixbody.fix.foodapplication.presentation.ui.screens

import FoodItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.presentation.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFoodScreen(viewModel: MainViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val addFoodSheetState = rememberModalBottomSheetState()
    viewModel.getFoodWithPersons()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        addFoodSheetState.show() // Show the BottomSheet when FAB is clicked
                    }
                },
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 70.dp)
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        // Main content of the screen, including the LazyColumn
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp) // Ensure content is not obscured by FAB
            ) {
                items(viewModel.foods) { item ->
                    FoodItem(
                        item = item,
                    )
                }
            }
        }
    }

    if (addFoodSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    addFoodSheetState.hide()
                }
            },
            sheetState = addFoodSheetState
        ) {
            FoodScreen(viewModel, addFoodSheetState, scope)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(viewModel: MainViewModel, sheetState: SheetState, scope: CoroutineScope) {
    var userName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "Food Name") }
        )

        Button(
            onClick = {
                if (userName.isNotBlank()) {
                    viewModel.addFood(Food(name = userName))
                    userName = ""
                    scope.launch {
                        sheetState.hide()
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Food")
        }
    }
}