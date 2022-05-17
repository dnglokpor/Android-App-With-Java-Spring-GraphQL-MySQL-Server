package com.example.thecommunityboard.ui.util

import android.graphics.Color
import android.util.Log
import kotlin.random.Random

/**
 * @return a random RGB color in hexadecimal
 */
fun randomColor(): Int {
    return Color.argb(180, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}