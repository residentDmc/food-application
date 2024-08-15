package refixbody.fix.foodapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import refixbody.fix.foodapplication.data.domain.repository.PersonFoodRepository
import refixbody.fix.foodapplication.data.domain.repository.PersonFoodRepositoryImpl
import refixbody.fix.foodapplication.data.domain.usecases.PersonFoodUseCase
import refixbody.fix.foodapplication.data.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "food_application.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonRepository(db: AppDatabase): PersonFoodRepository {
        return PersonFoodRepositoryImpl(db.personFoodDao())
    }

    // Add this line to provide AddPersonUseCase
    @Provides
    @Singleton
    fun provideAddPersonUseCase(personRepository: PersonFoodRepository): PersonFoodUseCase {
        return PersonFoodUseCase(personRepository)
    }
}
