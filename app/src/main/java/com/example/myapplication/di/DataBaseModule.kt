package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.dao.InternetServiceDao
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.local.dao.NetDao
import com.example.myapplication.data.local.dao.PrintDao
import com.example.myapplication.data.local.dao.stationery.ProductDao
import com.example.myapplication.data.local.dao.stationery.SaleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNetDao(
        database: AppDatabase
    ): NetDao {
        return database.netDao()
    }

    @Provides
    @Singleton
    fun providePrintDao(
        database: AppDatabase
    ): PrintDao{
        return database.printDao()

    }

 @Provides
 @Singleton
 fun provideSaleDao(database: AppDatabase): SaleDao{
     return database.saleDao()
 }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao{
        return database.productDao()

    }

    @Provides
    @Singleton
    fun provideInternetServiceDao(database: AppDatabase): InternetServiceDao {
     return database.internetServiceDao()

    }
}