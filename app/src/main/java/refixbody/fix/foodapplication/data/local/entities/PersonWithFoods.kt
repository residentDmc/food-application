package refixbody.fix.foodapplication.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PersonWithFoods(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "personId",
        entityColumn = "foodId",
        associateBy = Junction(PersonFoodCrossRef::class)
    )
    val foods: List<Food>
)
