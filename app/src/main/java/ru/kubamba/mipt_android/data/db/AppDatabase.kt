package ru.kubamba.mipt_android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}