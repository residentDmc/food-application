package refixbody.fix.foodapplication.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "foodId"])
data class PersonFoodCrossRef(
    val personId: Int, // References the 'id' in 'Person' entity
    val foodId: Int    // References the 'id' in 'Food' entity
)

