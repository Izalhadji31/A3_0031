package com.example.uaspam.ui.ViewModel.merk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Merk
import com.example.uaspam.repository.MerkRepository
import kotlinx.coroutines.launch

class InsertMerkViewModel(private val mrk: MerkRepository): ViewModel() {
    var uiStateMerk by mutableStateOf(InsertUiStateMerk())
        private set

    // Fungsi untuk memperbarui status UI
    fun updateInsertmrkState(insertUiEventMerk: InsertUiEventMerk) {
        uiStateMerk = InsertUiStateMerk(insertUiEventMerk)
    }

    // Fungsi untuk memvalidasi data Merk
    fun validateMerkData(): Boolean {
        val data = uiStateMerk.insertUiEventMerk
        return when {
            data.nama_merk.isBlank() -> {
                uiStateMerk = uiStateMerk.copy(errorMessage = "Nama merk tidak boleh kosong")
                false
            }
            data.deskripsi_merk.isBlank() -> {
                uiStateMerk = uiStateMerk.copy(errorMessage = "Deskripsi merk tidak boleh kosong")
                false
            }
            else -> {
                uiStateMerk = uiStateMerk.copy(errorMessage = "") // Clear error message
                true
            }
        }
    }

    // Fungsi untuk menyimpan data Merk
    suspend fun insertmrk() {
        viewModelScope.launch {
            try {
                mrk.insertmerk(uiStateMerk.insertUiEventMerk.tomrk())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// Fungsi untuk konversi data InsertUiEventMerk ke Merk
fun InsertUiEventMerk.tomrk(): Merk = Merk(
    id_merk = id_merk ?: 0,
    nama_merk = nama_merk,
    deskripsi_merk = deskripsi_merk,
)

// Fungsi untuk konversi Merk ke InsertUiStateMerk
fun Merk.toUiStateMerk(): InsertUiStateMerk = InsertUiStateMerk(
    insertUiEventMerk = toInsertUiEventMerk()
)

// Fungsi untuk konversi Merk ke InsertUiEventMerk
fun Merk.toInsertUiEventMerk(): InsertUiEventMerk = InsertUiEventMerk(
    id_merk = id_merk,
    nama_merk = nama_merk,
    deskripsi_merk = deskripsi_merk,
)

// Data class untuk status UI
data class InsertUiStateMerk(
    val insertUiEventMerk: InsertUiEventMerk = InsertUiEventMerk(),
    val errorMessage: String = "" // Menyimpan pesan error
)

// Data class untuk event UI
data class InsertUiEventMerk(
    val id_merk: Int? = null,
    val nama_merk: String = "",
    val deskripsi_merk: String = "",
)
