package com.example.pmdm_repaso.list

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class ListaNaves (
    val count: Long,
    val next: String,
    val previous: JsonObject? = null,
    val results: List<Naves>
)

@Serializable
data class Naves (
    val name: String,
    val model: String,
    val manufacturer: String,

    @SerialName("cost_in_credits")
    val costInCredits: String,

    val length: String,

    @SerialName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    val crew: String,
    val passengers: String,

    @SerialName("cargo_capacity")
    val cargoCapacity: String,

    val consumables: String,

    @SerialName("vehicle_class")
    val vehicleClass: String,

    val pilots: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
