package ru.kubamba.mipt_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kubamba.mipt_android.data.RemoteRestaurant
import ru.kubamba.mipt_android.data.RestaurantRepository
import ru.kubamba.mipt_android.data.mapToRestaurantEntity
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
    private val _viewState: MutableLiveData<RestaurantsViewState> = MutableLiveData(RestaurantsViewState())
    val viewState: LiveData<RestaurantsViewState> = _viewState

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

    private fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRestaurants().collectLatest {
                response -> _viewState.postValue(
                _viewState.value?.copy(
                    nearest = response.nearest,
                    popular = response.popular
                )
                )
            }
        }
    }
}