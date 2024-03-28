package com.dariel94.android.pokeapp.domain.model

/**
 * Created by dariel94 on 31/10/2021.
 */
data class EvolutionDetail(
    val level: Int?,
    val happiness: Int?,
    val affection: Int?,
    val gender: Int?,
    val relativePhysicalStats: Int?,
    val needsOverWorldRain: Boolean,
    val item: String?,
    val heldItem: String?,
    val knownMoveType: String?,
    val knownMove: String?,
    val timeOfDay: String?,
    val beauty: String?,
    val location: String?,
    val trigger: String?,
)
