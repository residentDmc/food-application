package refixbody.fix.foodapplication.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FoodWithPersons(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "foodId",  // Primary key of the Food entity
        entityColumn = "personId",  // Primary key of the Person entity
        associateBy = Junction(PersonFoodCrossRef::class)
    )
    val persons: List<Person>
)
