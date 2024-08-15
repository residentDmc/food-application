package refixbody.fix.foodapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import refixbody.fix.foodapplication.data.domain.usecases.PersonFoodUseCase
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.FoodWithPersons
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonWithFoods
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personFoodUseCase: PersonFoodUseCase,
) : ViewModel() {
    val foods: List<FoodWithPersons> get() = _foods
    val users: List<PersonWithFoods> get() = _users

    private var _foods = mutableStateListOf<FoodWithPersons>()
    private var _users = mutableStateListOf<PersonWithFoods>()

    fun addPerson(person: Person) {
        viewModelScope.launch {
            personFoodUseCase.addPerson(person)
        }
    }

    fun addFood(food: Food) {
        viewModelScope.launch {
            personFoodUseCase.addFood(food)
        }
    }

    fun assignFoodToPerson(personId: Int, foodId: Int) {
        viewModelScope.launch {
            personFoodUseCase.assignFoodToPerson(personId, foodId)
        }
    }

    fun getPersonWithFoods() {
        viewModelScope.launch {
            val personWithFoods = personFoodUseCase.getPersonWithFoods()
            _users.clear()
            _users.addAll(personWithFoods)
        }
    }

    fun getFoodWithPersons() {
        viewModelScope.launch {
            val foodWithPersons = personFoodUseCase.getFoodWithPersons()
            _foods.clear()
            _foods.addAll(foodWithPersons)
        }
    }
}
