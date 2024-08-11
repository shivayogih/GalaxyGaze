package com.shivayogi.galaxygaze.ui.astronomy.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivayogi.galaxygaze.data.local.entities.ApodEntity
import com.shivayogi.galaxygaze.data.repositories.ApodRepository
import com.shivayogi.galaxygaze.domain.usecase.GetApodUseCase
import com.shivayogi.galaxygaze.utils.NetworkUtils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodUseCase: GetApodUseCase,
    private val repository: ApodRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    var apodState by mutableStateOf<ApodEntity?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchApod(date: String) {
        viewModelScope.launch {
            try {
                apodState = getApodUseCase(date)
            } catch (e: Exception) {
                errorMessage = "Error loading data: ${e.message}"
            }
        }
    }


}
