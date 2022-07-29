package com.example.bootcampprojeto1.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(
    val nome: String,
    val forca: Int,
    val imagem: String,
    var placar: Int?

): Parcelable
