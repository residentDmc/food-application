package refixbody.fix.foodapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonFoodCrossRef
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods

@Dao
interface PersonFoodDao {

    @Insert
    suspend fun insertPerson(person: Person)

    @Insert
    suspend fun insertFood(food: Food)

    @Insert
    suspend fun insertPersonFoodCrossRef(crossRef: PersonFoodCrossRef)

    @Transaction
    @Query("SELECT * FROM persons")
    suspend fun getPersonWithFoods(): List<PersonWithFoods>

    @Transaction
    @Query("SELECT * FROM foods")
    suspend fun getFoodWithPersons(): List<FoodWithPersons>
}
