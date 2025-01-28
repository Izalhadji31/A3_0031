package com.example.uaspam.repository

import com.example.uaspam.model.AllMerkResponse
import com.example.uaspam.model.Merk
import com.example.uaspam.model.MerkDetailResponse
import com.example.uaspam.service_api.MerkService
import java.io.IOException

interface MerkRepository {
    suspend fun getmerk(): AllMerkResponse
    suspend fun insertmerk(merk: Merk)
    suspend fun updatemerk(id_merk: String, merk: Merk)
    suspend fun deletemerk(id_merk: String)
    suspend fun getmerkByid_merk(id_merk: String): MerkDetailResponse
}

class NetworkmerkRepository(
    private val merkApiService: MerkService
) : MerkRepository {

    override suspend fun insertmerk(merk: Merk) {
        merkApiService.insertMerk(merk)
    }

    override suspend fun updatemerk(id_merk: String, merk: Merk) {
        merkApiService.updateMerk(id_merk, merk)
    }

    override suspend fun deletemerk(id_merk: String) {
        try {
            val response = merkApiService.deleteMerk(id_merk)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete merk. HTTP Status Code: " +
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

    override suspend fun getmerk(): AllMerkResponse {
        return merkApiService.getAllMerk()
    }

    override suspend fun getmerkByid_merk(id_merk: String):  MerkDetailResponse {
        return merkApiService.getMerkById(id_merk)
    }
}