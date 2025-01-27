package com.myjar.jarassignment.di

import android.content.Context
import androidx.room.Room
import com.myjar.jarassignment.data.remote.api.ApiService
import com.myjar.jarassignment.data.local.db.AppDatabase
import com.myjar.jarassignment.data.local.dao.ComputerItemDao
import com.myjar.jarassignment.data.repository.JarRepository
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    fun provideDao(db: AppDatabase): ComputerItemDao {
        return db.computerItemDao()
    }

    @Provides
    fun provideRepository(apiService: ApiService, dao: ComputerItemDao, @ApplicationContext context: Context): JarRepository {
        return JarRepositoryImpl(apiService, dao,context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.restful-api.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
