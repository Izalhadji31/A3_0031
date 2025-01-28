package com.example.uaspam.ui.ViewModel.pemasok

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Pemasok
import kotlinx.coroutines.launch

class InsertPemasokViewModel(private val pmsk: PemasokRepository): ViewModel() {
    var uiStatePemasok by mutableStateOf(InsertUiStatePemasok())
        private set

    // Fungsi untuk memperbarui status UI
    fun updateInsertpmskState(insertUiEventPemasok: InsertUiEventPemasok) {
        uiStatePemasok = InsertUiStatePemasok(insertUiEventPemasok)
    }

    // Validasi Data Pemasok
    fun validatePemasokData(): Boolean {
        val data = uiStatePemasok.insertUiEventPemasok
        return when {
            data.nama_produk.isBlank() -> {
                uiStatePemasok = uiStatePemasok.copy(errorMessage = "Nama produk tidak boleh kosong")
                false
            }
            data.nama_pemasok.isBlank() -> {
                uiStatePemasok = uiStatePemasok.copy(errorMessage = "Nama pemasok tidak boleh kosong")
                false
            }
            data.alamat_pemasok.isBlank() -> {
                uiStatePemasok = uiStatePemasok.copy(errorMessage = "Alamat pemasok tidak boleh kosong")
                false
            }
            !isValidPhoneNumber(data.telepon_pemasok) -> {
                uiStatePemasok = uiStatePemasok.copy(errorMessage = "Nomor telepon tidak valid")
                false
            }
            else -> {
                uiStatePemasok = uiStatePemasok.copy(errorMessage = "") // Clear error message
                true
            }
        }
    }

    // Fungsi untuk mengecek format nomor telepon (misalnya, hanya angka dan panjangnya tepat)
    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\+?[0-9]{10,13}\$")) // Menyesuaikan dengan format nomor telepon
    }

    // Fungsi untuk menyimpan data pemasok
    suspend fun insertpmsk() {
        viewModelScope.launch {
            try {
                pmsk.insertpemasok(uiStatePemasok.insertUiEventPemasok.topmsk())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// Fungsi untuk konversi data Pemasok ke InsertUiEventPemasok
fun InsertUiEventPemasok.topmsk(): Pemasok = Pemasok(
    id_pemasok = id_pemasok ?: 0,
    nama_produk = nama_produk,
    nama_pemasok = nama_pemasok,
    alamat_pemasok = alamat_pemasok,
    telepon_pemasok = telepon_pemasok,
)

// Fungsi untuk konversi Pemasok ke InsertUiStatePemasok
fun Pemasok.toUiStatePemasok(): InsertUiStatePemasok = InsertUiStatePemasok(
    insertUiEventPemasok = toInsertUiEventPemasok()
)

// Fungsi untuk konversi Pemasok ke InsertUiEventPemasok
fun Pemasok.toInsertUiEventPemasok(): InsertUiEventPemasok = InsertUiEventPemasok(
    id_pemasok = id_pemasok,
    nama_produk = nama_produk,
    nama_pemasok = nama_pemasok,
    alamat_pemasok = alamat_pemasok,
    telepon_pemasok = telepon_pemasok,
)

// State UI untuk menyimpan data dan pesan kesalahan
data class InsertUiStatePemasok(
    val insertUiEventPemasok: InsertUiEventPemasok = InsertUiEventPemasok(),
    val errorMessage: String = ""
)

// Event UI untuk mengatur data pemasok
data class InsertUiEventPemasok(
    val id_pemasok: Int? = null,
    val nama_produk: String = "",
    val nama_pemasok: String = "",
    val alamat_pemasok: String = "",
    val telepon_pemasok: String = "",
)
