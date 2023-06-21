package com.cicd.employee.di

import android.app.Application
import androidx.room.Room
import com.cicd.employee.data.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
//@InstallIn(ActivityComponent::class)
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): EmployeeDatabase =
        Room.databaseBuilder(app, EmployeeDatabase::class.java, "employee_database")
            .fallbackToDestructiveMigration()
            .build()
}