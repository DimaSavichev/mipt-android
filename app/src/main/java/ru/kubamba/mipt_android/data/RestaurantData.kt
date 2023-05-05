package ru.kubamba.mipt_android.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class CatalogResponse(
    val nearest: List<RemoteRestaurant>,
    val popular: List<RemoteRestaurant>,
    val commercial: RemoteCommercial
)

@Serializable
data class RemoteRestaurant(
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class RemoteCommercial(
    val picture: String,
    val url: String
)

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao) {

    suspend fun fetchRestaurants(): Flow<CatalogResponse> {
        return flow {
            val cache = restaurantDao.getAll()
            if (cache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = emptyList(),
                        popular = cache.map { it.mapToRemoteRestaurant() },
                        commercial = RemoteCommercial("", "")
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                restaurantDao.insertAll(*response.popular.map { it.mapToRestaurantEntity() }.toTypedArray())
                emit(response)
            } catch (e: Exception) {

            }
        }

    }
}