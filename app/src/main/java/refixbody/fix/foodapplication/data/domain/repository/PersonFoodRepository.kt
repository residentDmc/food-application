package refixbody.fix.foodapplication.data.domain.repository

import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods

interface PersonFoodRepository {

    suspend fun addPerson(person: Person)

    suspend fun addFood(food: Food)

    suspend fun assignFoodToPerson(personId: Int, foodId: Int)

    suspend fun getPersonWithFoods(): List<PersonWithFoods>

    suspend fun getFoodWithPersons(): List<FoodWithPersons>
}
