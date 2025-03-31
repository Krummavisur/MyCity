package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.model.PlaceInfo
import com.example.mycity.ui.details.DetailsViewModel
import com.example.mycity.ui.places.PlacesViewModel
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel, index: Int) {
    val place = detailsViewModel.uiState.collectAsState().value

    place?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFFE44D26), Color.Black)
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(id = it.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp)
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(id = it.name),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stringResource(id = it.details),
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    MyCityTheme {

    }
}