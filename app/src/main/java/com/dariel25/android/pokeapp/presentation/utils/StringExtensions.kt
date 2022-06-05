/*
 * Created by David on 18/2/22 21:46
 */

package com.dariel25.android.pokeapp.presentation.utils

fun String.capitalizeFirst(): String =
    replaceFirstChar { c -> c.uppercase() }

fun String.capitalizeWords(): String =
    split(" ").map { it.capitalizeFirst() }.joinToString(" ")

fun String.normalizeProperty(): String =
    replace("-", " ").capitalizeWords()

fun String?.safe(): String = this ?: ""
