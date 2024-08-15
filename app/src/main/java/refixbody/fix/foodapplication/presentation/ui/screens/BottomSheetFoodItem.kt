import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons

@Composable
fun BottomSheetFoodItem(
    item: FoodWithPersons,
    onItem: (FoodWithPersons) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItem(item) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display the food name
            Text(
                text = item.food.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Divider at the end
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
    }
}
