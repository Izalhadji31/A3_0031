package com.example.uaspam.repository

import com.example.uaspam.model.AllKategoriResponse
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.KategoriDetailResponse
import com.example.uaspam.service_api.KategoriService
import java.io.IOException

interface KategoriRepository {
    suspend fun getkategori(): AllKategoriResponse
    suspend fun insertkategori(kategori: Kategori)
    suspend fun updatekategori(id_kategori: String, kategori: Kategori)
    suspend fun deletekategori(id_kategori: String)
    suspend fun getkategoriByid_kategori(id_kategori: String): KategoriDetailResponse
}

class NetworkkategoriRepository(
    private val kategoriApiService: KategoriService
) : KategoriRepository {

    override suspend fun insertkategori(kategori: Kategori) {
        kategoriApiService.insertKategori(kategori)
    }

    override suspend fun updatekategori(id_kategori: String, kategori: Kategori) {
        kategoriApiService.updateKategori(id_kategori, kategori)
    }

    override suspend fun deletekategori(id_kategori: String) {
        try {
            val response = kategoriApiService.deleteKategori(id_kategori)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete kategori. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getkategori(): AllKategoriResponse {
        return kategoriApiService.getAllKategori()
    }

    override suspend fun getkategoriByid_kategori(id_kategori: String): KategoriDetailResponse {
        return kategoriApiService.getKategoriById(id_kategori)
    }
}