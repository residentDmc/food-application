package refixbody.fix.foodapplication.presentation.ui.screens

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
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods

@Composable
fun UserItem(
    item: PersonWithFoods,
    onItem: (PersonWithFoods) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItem(item) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display the food name
            Text(
                text = item.person.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Display the list of persons associated with the food
            item.foods.forEach { person ->
                Text(
                    text = person.name,
                    style = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
            }

            // Divider at the end
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
    }
}
