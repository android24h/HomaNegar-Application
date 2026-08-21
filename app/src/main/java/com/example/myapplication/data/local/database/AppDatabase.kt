package com.example.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.InternetServiceDao
import com.example.myapplication.data.local.dao.NetDao
import com.example.myapplication.data.local.dao.PrintDao
import com.example.myapplication.data.local.dao.stationery.ProductDao
import com.example.myapplication.data.local.dao.stationery.SaleDao
import com.example.myapplication.data.local.entity.NetDataModel
import com.example.myapplication.data.local.entity.InternetServiceDataModel
import com.example.myapplication.data.local.entity.PrintDataModel
import com.example.myapplication.data.local.entity.Stationery.ProductDataModel
import com.example.myapplication.data.local.entity.Stationery.SalesDataModel

@Database(
    entities = [
        NetDataModel::class,
        PrintDataModel::class,
        SalesDataModel::class,
        ProductDataModel::class,
        InternetServiceDataModel::class
   ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun netDao(): NetDao
    abstract fun printDao(): PrintDao
    abstract fun productDao(): ProductDao
    abstract fun saleDao(): SaleDao

    abstract fun internetServiceDao(): InternetServiceDao

    // الگو برای جلوگیری از ساخت نمونه‌های متعدد از دیتابیس
    companion object {
        const val DATABASE_NAME="office_database"
    }
}