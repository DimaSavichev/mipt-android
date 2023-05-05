package ru.kubamba.mipt_android.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "restaurants")
class RestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

fun RemoteRestaurant.mapToRestaurantEntity(): RestaurantEntity =
    RestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun RestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<RestaurantEntity>

    @Insert
    suspend fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    suspend fun delete(restaurantEntity: RestaurantEntity)
}