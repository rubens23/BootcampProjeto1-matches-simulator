package com.example.bootcampprojeto1.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Partida(
    val descricao: String,
    val local: Local,
    val mandante: Time,
    val visitante: Time
): Parcelable
