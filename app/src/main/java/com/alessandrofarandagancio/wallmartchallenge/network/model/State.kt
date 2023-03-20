package com.alessandrofarandagancio.wallmartchallenge.network.model

import com.alessandrofarandagancio.wallmartchallenge.ui.model.State as UIState


/*

  {
    "capital": "Kabul",
    "code": "AF",
    "currency": {
      "code": "AFN",
      "name": "Afghan afghani",
      "symbol": "؋"
    },
    "flag": "https://restcountries.eu/data/afg.svg",
    "language": {
      "code": "ps",
      "name": "Pashto"
    },
    "name": "Afghanistan",
    "region": "AS"
  }

 */

data class State(
    val capital: String,
    val code: String,
    val flag: String,
    val name: String,
    val region: String,
    val language: StateLanguage,
    val currency: StateCurrency
)

data class StateLanguage(val code: String, val name: String)
data class StateCurrency(val code: String, val name: String, val symbol: String)

fun List<State>.asViewModel(): List<UIState> {
    return map {
        UIState(
            code = it.code,
            name = it.name,
            capital = it.capital,
            flag = it.flag,
            region = it.region
        )
    }
}