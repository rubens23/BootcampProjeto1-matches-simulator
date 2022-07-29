package com.example.bootcampprojeto1.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Local(
    val nome: String,
    val imagem: String
): Parcelable