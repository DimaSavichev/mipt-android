package ru.kubamba.mipt_android

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ru.kubamba.mipt_android.data.RemoteRestaurant


@SuppressLint("ResourceType")
@Composable
fun RestaurantsScreen() {
    val restaurantsViewModel: RestaurantsViewModel = viewModel()
    val state by restaurantsViewModel.viewState.observeAsState()
    val restaurantsViewState = state ?: return

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.find_your_food),
            fontWeight = FontWeight.Bold,
            fontSize = 31.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        RestaurantsView(restaurantsViewModel, restaurantsViewState)
    }
}

@Composable
fun RestaurantsView(restaurantsViewModel: RestaurantsViewModel, restaurantsViewState: RestaurantsViewState) {
    Text(
        text = stringResource(R.string.popular),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
    RestaurantsGrid(restaurantsViewState.popular)
    Text(
        text = stringResource(R.string.nearest),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )
    RestaurantsGrid(restaurantsViewState.nearest)
}

@Composable
fun RestaurantsGrid(restaurants: List<RemoteRestaurant>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        ) {

        items(restaurants) {
                restaurant -> Card(
            modifier = Modifier
                .width(147.dp)
                .height(184.dp)
                .padding(8.dp)
                .border(
                    1.dp,
                    Color.LightGray,
                    RoundedCornerShape(22.dp, 22.dp, 22.dp, 22.dp)
                ),
            elevation = 0.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(restaurant.image),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )

                Text(
                    text = restaurant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = restaurant.deliveryTime,
                    fontSize = 13.sp
                )
            }

        }
        }
    }
}