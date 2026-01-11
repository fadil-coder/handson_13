package org.example.simpanpinjam.model

data class Pinjaman(
    val id: Int,
    val anggotaId: Int,
    val pokok: Double,
    val bunga: Double,
    val tenor: Int
)
