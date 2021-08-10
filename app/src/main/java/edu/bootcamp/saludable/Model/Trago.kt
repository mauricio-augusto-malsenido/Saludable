package edu.bootcamp.saludable.Model

import java.io.Serializable

data class Trago (val drinks: List<Drink>)
{
    data class Drink (val idDrink: String,
                      val strDrink: String,
                      val strCategory: String,
                      val strDrinkThumb: String) : Serializable
}