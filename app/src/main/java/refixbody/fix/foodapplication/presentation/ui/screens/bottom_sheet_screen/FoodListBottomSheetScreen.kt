package refixbody.fix.foodapplication.presentation.ui.screens.bottom_sheet_screen

import BottomSheetFoodItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.presentation.viewmodel.MainViewModel

@Composable
fun FoodListBottomSheetScreen(viewModel: MainViewModel,
                              onItem: (FoodWithPersons) -> Unit = {}) {
    viewModel.getFoodWithPersons()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp) // Ensure content is not obscured by FAB
    ) {
        items(viewModel.foods) { item ->
            BottomSheetFoodItem(
                item = item,
                onItem = {
                    onItem(it)
                },
            )
        }
    }
}

