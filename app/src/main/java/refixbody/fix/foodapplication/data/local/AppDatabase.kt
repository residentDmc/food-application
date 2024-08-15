package refixbody.fix.foodapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import refixbody.fix.foodapplication.data.local.converter.Converters
import refixbody.fix.foodapplication.data.local.dao.PersonFoodDao
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.Person
import refixbody.fix.foodapplication.data.local.entities.PersonFoodCrossRef

@Database(entities = [Person::class, Food::class, PersonFoodCrossRef::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personFoodDao(): PersonFoodDao
}
