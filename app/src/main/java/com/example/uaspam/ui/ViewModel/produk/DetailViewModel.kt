package com.example.uaspam.ui.ViewModel.produk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.ProdukDetailResponse
import com.example.uaspam.repository.ProdukRepository
import com.example.uaspam.ui.Navigation.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val produk: ProdukDetailResponse) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailProdukViewModel(
    savedStateHandle: SavedStateHandle,
    private val prdk: ProdukRepository
) : ViewModel() {

    var produkDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _id_produk: String = checkNotNull(savedStateHandle[DestinasiDetail.ID_produk])

    init {
        getprodukbyid_produk()
    }

    fun getprodukbyid_produk() {
        viewModelScope.launch {
            produkDetailState = DetailUiState.Loading
            produkDetailState = try {
                val produk = prdk.getprodukByid_produk(_id_produk)
                DetailUiState.Success(produk )
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}

