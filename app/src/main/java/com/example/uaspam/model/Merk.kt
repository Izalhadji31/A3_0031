package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Merk (
    val id_merk: Int,
    val nama_merk: String,
    val deskripsi_merk: String,
)

@Serializable
data class AllMerkResponse(
    val status: Boolean,
    val message: String,
    val data: List<Merk>
)

@Serializable
data class MerkDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Merk
)