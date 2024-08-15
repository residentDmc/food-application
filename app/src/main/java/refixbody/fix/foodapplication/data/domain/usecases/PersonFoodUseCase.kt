package refixbody.fix.foodapplication.data.domain.usecases

import refixbody.fix.foodapplication.data.domain.repository.PersonFoodRepository
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods
import javax.inject.Inject

class PersonFoodUseCase @Inject constructor(
    private val foodRepository: PersonFoodRepository
) {

    suspend fun addPerson(person: Person) {
        foodRepository.addPerson(person)
    }

    suspend fun addFood(food: Food) {
        foodRepository.addFood(food)
    }

    suspend fun assignFoodToPerson(personId: Int, foodId: Int) {
        foodRepository.assignFoodToPerson(personId, foodId)
    }

    suspend fun getPersonWithFoods(): List<PersonWithFoods> {
        return foodRepository.getPersonWithFoods()
    }

    suspend fun getFoodWithPersons(): List<FoodWithPersons> {
        return foodRepository.getFoodWithPersons()
    }

}