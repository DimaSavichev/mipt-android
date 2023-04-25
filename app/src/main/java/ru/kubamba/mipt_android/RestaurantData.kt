package ru.kubamba.mipt_android

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
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

class RestaurantRepository @Inject constructor(private val httpClient: HttpClient) {

    suspend fun fetchRestaurants(): CatalogResponse {
        val response = httpClient.request("http://195.2.84.138:8081/catalog") {
            method = HttpMethod.Get
        }.body<CatalogResponse>()

        return response
    }
}