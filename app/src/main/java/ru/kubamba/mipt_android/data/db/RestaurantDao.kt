package ru.kubamba.mipt_android.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<RestaurantEntity>

    @Insert
    suspend fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    suspend fun delete(restaurantEntity: RestaurantEntity)
}