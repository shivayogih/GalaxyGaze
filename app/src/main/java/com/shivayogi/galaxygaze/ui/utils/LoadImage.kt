package com.shivayogi.galaxygaze.ui.utils


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.shivayogi.galaxygaze.R
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Composable
fun LoadImage(url: String, cacheName: String) {
    val context = LocalContext.current

/*
    val imageLoader = ImageLoader.Builder(context)
        .okHttpClient {
            OkHttpClient.Builder()
                .hostnameVerifier { _, _ -> true }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(url)
       // .size(500) // Resize to a smaller size
        .crossfade(true)
        .diskCacheKey(cacheName)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()


    AsyncImage(
        model = request,
        imageLoader = imageLoader,
        contentDescription = cacheName,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.ic_placeholder)
    )*/

    val painter = rememberAsyncImagePainter(model = url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun LoadImagePreview() {
    LoadImage(
        url = "https://apod.nasa.gov/apod/image/2408/M20OriginalLRGBHaO3S2_1500x1100.jpg",
        cacheName = "abc"
    )
}