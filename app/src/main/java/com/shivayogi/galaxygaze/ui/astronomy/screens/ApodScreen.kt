package com.shivayogi.galaxygaze.ui.astronomy.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.shivayogi.galaxygaze.ui.astronomy.viewmodel.ApodViewModel
import com.shivayogi.galaxygaze.ui.utils.LoadImage
import com.shivayogi.galaxygaze.ui.utils.VideoPlayerOrThumbnail
import java.time.LocalDate

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ApodScreen() {
    val viewModel: ApodViewModel = hiltViewModel()

    val isRefreshing = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(Unit) {
        // Fetch today's APOD
        viewModel.fetchApod(LocalDate.now().toString())
    }

    val apod = viewModel.apodState
    val error = viewModel.errorMessage

    SwipeRefresh(
        state = isRefreshing,
        onRefresh = {
            // Refresh the APOD data
            viewModel.fetchApod(LocalDate.now().toString())
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Page title at the top
            Text(
                text = "Astronomy Picture of the Day",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Start
            )

            // Scrollable content below the title
            if (apod != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        // Image taking 50% of the screen height
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(LocalConfiguration.current.screenHeightDp.dp / 2)
                        ) {
                            when (apod.media_type) {
                                "image" -> {
                                    apod.url?.let {
                                        LoadImage(
                                            url = it,
                                            cacheName = "image"
                                        )
                                    }
                                }

                                "video" -> {
                                    apod.url?.let {
                                        VideoPlayerOrThumbnail(url = apod.url)
                                    }
                                }

                                else -> {
                                    Text(text = "Unsupported media type")
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        // Text details below the image
                        apod.title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        apod.date?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        apod.explanation?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        apod.copyright?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            } else if (error != null) {
                // Display error message
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            text = "Error Loading Data or Check you Network Connection.",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Swipe down to refresh the Page",
                            style = MaterialTheme.typography.displayMedium,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                }

            } else {
                // Show a loading indicator
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}