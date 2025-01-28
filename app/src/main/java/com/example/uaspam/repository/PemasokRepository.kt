import com.example.uaspam.model.AllPemasokResponse
import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.PemasokDetailResponse
import com.example.uaspam.service_api.PemasokService
import java.io.IOException

interface PemasokRepository {
    suspend fun getpemasok(): AllPemasokResponse
    suspend fun insertpemasok(pemasok: Pemasok)
    suspend fun updatepemasok(id_pemasok: String, pemasok: Pemasok)
    suspend fun deletepemasok(id_pemasok: String)
    suspend fun getpemasokByid_pemasok(id_pemasok: String): PemasokDetailResponse
}

class NetworkpemasokRepository(
    private val pemasokApiService: PemasokService
) : PemasokRepository {

    override suspend fun insertpemasok(pemasok: Pemasok) {
        pemasokApiService.insertPemasok(pemasok)
    }

    override suspend fun updatepemasok(id_pemasok: String, pemasok: Pemasok) {
        pemasokApiService.updatePemasok(id_pemasok, pemasok)
    }

    override suspend fun deletepemasok(id_pemasok: String) {
        try {
            val response = pemasokApiService.deletePemasok(id_pemasok)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete pemasok. HTTP Status Code: " +
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

    override suspend fun getpemasok(): AllPemasokResponse {
        return pemasokApiService.getAllPemasok()
    }

    override suspend fun getpemasokByid_pemasok(id_pemasok: String): PemasokDetailResponse {
        return pemasokApiService.getPemasokById(id_pemasok)
    }
}