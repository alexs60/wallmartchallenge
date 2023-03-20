package com.alessandrofarandagancio.wallmartchallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alessandrofarandagancio.wallmartchallenge.network.model.asViewModel
import com.alessandrofarandagancio.wallmartchallenge.repository.StateRepository
import com.alessandrofarandagancio.wallmartchallenge.ui.model.State
import kotlinx.coroutines.launch
import java.io.IOException

class StateViewModel : ViewModel() {

    private val stateRepository: StateRepository = StateRepository()

    val stateListResponse: LiveData<List<State>>
        get() = Transformations.map(stateRepository.getAllStates()) {
            it.asViewModel()
        }

    fun refreshCoins() {
        viewModelScope.launch {
            try {
                stateRepository.refreshStates()
            } catch (networkError: IOException) {
                Log.d("ERROR", networkError.message.orEmpty())
            }
        }
    }

}
