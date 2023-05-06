package ru.kubamba.mipt_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import ru.kubamba.mipt_android.data.RestaurantRepository
import javax.inject.Singleton
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import ru.kubamba.mipt_android.data.db.RestaurantDao

@Module
@InstallIn(SingletonComponent::class)
class RestaurantsModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun provideRestaurantRepository(client: HttpClient, dao: RestaurantDao): RestaurantRepository {
        return RestaurantRepository(client, dao)
    }
}
