package com.example.mycity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.model.PlaceInfo
import com.example.mycity.ui.places.PlacesViewModel
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun PlacesScreen(placesViewModel: PlacesViewModel = viewModel()){

    val uiState by placesViewModel.uiState.collectAsState()
    LazyColumn  {
        items(uiState.placesList) {
            place ->
            CardItemView(place)
        }
    }
}

@Composable
fun CardItemView(place: PlaceInfo) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Row (modifier = Modifier.fillMaxSize()
        ){
            Image(painter = painterResource(id = place.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = place.name),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun PlacesScreenPreview() {
    MyCityTheme {
        PlacesScreen()
    }
}