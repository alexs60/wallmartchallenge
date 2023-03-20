package com.alessandrofarandagancio.wallmartchallenge.network

import com.alessandrofarandagancio.wallmartchallenge.network.NetworkManager.Companion.apiService
import com.alessandrofarandagancio.wallmartchallenge.network.model.State

class NetworkManagerImpl : NetworkManager {

    override suspend fun getAllStates(): List<State> {
        return apiService.getAllState()
    }

}