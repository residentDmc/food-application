package refixbody.fix.foodapplication.data.domain.repository

import refixbody.fix.foodapplication.data.local.dao.PersonFoodDao
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonFoodCrossRef
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods

class PersonFoodRepositoryImpl(private val personFoodDao: PersonFoodDao) : PersonFoodRepository {

    override suspend fun addPerson(person: Person) {
        return personFoodDao.insertPerson(person)
    }

    override suspend fun addFood(food: Food) {
        return personFoodDao.insertFood(food)
    }

    override suspend fun assignFoodToPerson(personId: Int, foodId: Int) {
        val crossRef = PersonFoodCrossRef(personId, foodId)
        return personFoodDao.insertPersonFoodCrossRef(crossRef)
    }

    override suspend fun getPersonWithFoods(): List<PersonWithFoods> {
        return personFoodDao.getPersonWithFoods()
    }

    override suspend fun getFoodWithPersons(): List<FoodWithPersons> {
        return personFoodDao.getFoodWithPersons()
    }

}