package com.example.myapplication.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.ClimateRepository
import com.example.myapplication.presentation.adapter.ClimateAdapter
import com.example.myapplication.presentation.base.BaseViewModel
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val climateRepository: ClimateRepository
) : BaseViewModel() {


    val climateAdapter by lazy { ClimateAdapter() }

    private val _moviesFlow = MutableStateFlow<UIState>(UIState.Empty)
    val moviesFlow: StateFlow<UIState> = _moviesFlow.asStateFlow()

    fun getClimate(){
        viewModelScope.launch {
            climateRepository.getClimate().catch {
                Log.e(TAG, "Ocurrio un error:", it)
                    _moviesFlow.value = UIState.Error(it.message)

            }.collectLatest {
                    _moviesFlow.value = UIState.Success(it)

            }
        }
    }


}