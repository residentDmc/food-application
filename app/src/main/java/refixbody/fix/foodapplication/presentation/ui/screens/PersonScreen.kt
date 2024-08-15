package refixbody.fix.foodapplication.presentation.ui.screens

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
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods
import refixbody.fix.foodapplication.presentation.ui.screens.bottom_sheet_screen.FoodListBottomSheetScreen
import refixbody.fix.foodapplication.presentation.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPersonScreen(viewModel: MainViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val listUserSheetState = rememberModalBottomSheetState()
    var selectedPerson by remember { mutableStateOf<PersonWithFoods?>(null) }
    viewModel.getPersonWithFoods()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        sheetState.show() // Show the BottomSheet when FAB is clicked
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
                items(viewModel.users) { item ->
                    UserItem(
                        item = item,
                        onItem = { itemFood ->
                            scope.launch {
                                selectedPerson = itemFood
                                listUserSheetState.show()
                            }
                        },
                    )
                }
            }
        }
    }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            sheetState = sheetState
        ) {
            PersonScreen(viewModel, sheetState, scope)
        }
    }


    if (listUserSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    listUserSheetState.hide()
                }
            },
            sheetState = listUserSheetState
        ) {
            FoodListBottomSheetScreen(
                viewModel,
                onItem = {itemFood->
                    selectedPerson?.let { itemPerson -> viewModel.assignFoodToPerson(itemPerson.person.personId, itemFood.food.foodId) }
                    scope.launch {
                        listUserSheetState.hide()
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(viewModel: MainViewModel, sheetState: SheetState, scope: CoroutineScope) {
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
            label = { Text(text = "User Name") }
        )

        Button(
            onClick = {
                if (userName.isNotBlank()) {
                    viewModel.addPerson(Person(name = userName))
                    userName = ""
                    scope.launch {
                        sheetState.hide()
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add User")
        }
    }
}

