package refixbody.fix.foodapplication.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    val name: String
)


