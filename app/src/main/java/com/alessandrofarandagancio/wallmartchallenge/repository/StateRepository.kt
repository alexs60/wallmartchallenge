package com.alessandrofarandagancio.wallmartchallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alessandrofarandagancio.wallmartchallenge.network.NetworkManagerImpl
import com.alessandrofarandagancio.wallmartchallenge.network.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class StateRepository {

    private val api: NetworkManagerImpl = NetworkManagerImpl()
    private var _statesList = MutableLiveData<List<State>>()
    val statesList: LiveData<List<State>> get() = _statesList

    fun getAllStates() = statesList

    suspend fun refreshStates() {
        withContext(Dispatchers.IO) {
            var coins = api.getAllStates()
            withContext(Dispatchers.Main) {
                _statesList.value = coins
            }
        }
    }

}