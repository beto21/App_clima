package com.example.myapplication.presentation.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.ClimateRepository
import com.example.myapplication.presentation.base.BaseViewModel
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ClimateDetailViewModel @Inject constructor(
    private val climateRepository: ClimateRepository
    ) : BaseViewModel() {

    private val _climate by lazy { MutableStateFlow<UIState>(UIState.Empty) }
    val climateFlow: StateFlow<UIState> = _climate


    fun getClimateforId(id:Int) {
        viewModelScope.launch {
            climateRepository.getWeather(id)
                .catch {
                    Log.e(TAG,"Error:", it)
                    _climate.value =  UIState.Error(it.message)
                }.collect {
                    _climate.value = UIState.Success(it)
                }
        }
    }


}