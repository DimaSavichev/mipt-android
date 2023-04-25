package ru.kubamba.mipt_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RestaurantsViewState(
    val nearest: List<RemoteRestaurant> = emptyList(),
    val popular: List<RemoteRestaurant> = emptyList()
)

sealed class RestaurantsEvent() {
    object FetchRestaurants: RestaurantsEvent()
}

@HiltViewModel
class RestaurantsViewModel @Inject constructor(private val repository: RestaurantRepository): ViewModel() {
    private val _viewState = MutableStateFlow(RestaurantsViewState())
    val viewState: StateFlow<RestaurantsViewState> = _viewState

    init {
        viewModelScope.launch {
            fetchRestaurants()
        }
    }

    fun obtainEvent(event: RestaurantsEvent) {
//        when (event) {
//            RestaurantsEvent.FetchRestaurants -> fetchRestaurants()
//        }
    }

    private suspend fun fetchRestaurants() {
        val response = repository.fetchRestaurants()
        _viewState.value = _viewState.value.copy(
            nearest = response.nearest,
            popular = response.popular
        )
    }
}