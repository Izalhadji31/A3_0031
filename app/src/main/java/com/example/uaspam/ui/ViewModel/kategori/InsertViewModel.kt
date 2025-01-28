package com.example.uaspam.ui.ViewModel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.repository.KategoriRepository
import kotlinx.coroutines.launch

class InsertKategoriViewModel(private val ktgr: KategoriRepository): ViewModel() {
    var uiStateKategori by mutableStateOf(InsertUiStateKategori())
        private set

    var errorMessage by mutableStateOf<String?>(null) // Deklarasi errorMessage

    fun updateInsertktgrState(insertUiEventKategori: InsertUiEventKategori) {
        uiStateKategori = InsertUiStateKategori(insertUiEventKategori)
    }

    fun validate(): Boolean {
        val event = uiStateKategori.insertUiEventKategori
        val errorState = FormKategoriErrorState(
            nama_kategori = if (event.nama_kategori.isNotBlank()) null else "Nama Kategori tidak boleh kosong.",
            deskripsi_kategori = if (event.deskripsi_kategori.isNotBlank()) null else "Deskripsi Kategori tidak boleh kosong."
        )

        uiStateKategori = uiStateKategori.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertktgr() {
        if (validate()) {
            viewModelScope.launch {
                try {
                    ktgr.insertkategori(uiStateKategori.insertUiEventKategori.toktgr())
                    errorMessage = null // Reset error jika sukses
                } catch (e: Exception) {
                    errorMessage = "Gagal menyimpan kategori."
                    e.printStackTrace()
                }
            }
        }
    }
}

// Model validasi error
data class FormKategoriErrorState(
    val nama_kategori: String? = null,
    val deskripsi_kategori: String? = null
) {
    fun isValid(): Boolean {
        return nama_kategori == null && deskripsi_kategori == null
    }
}

// Konversi InsertUiEventKategori ke Kategori
fun InsertUiEventKategori.toktgr(): Kategori = Kategori(
    id_kategori = id_kategori ?: 0,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)

// Konversi Kategori ke UI State
fun Kategori.toUiStateKategori(): InsertUiStateKategori = InsertUiStateKategori(
    insertUiEventKategori = toInsertUiEventKategori()
)

// Konversi Kategori ke InsertUiEventKategori
fun Kategori.toInsertUiEventKategori(): InsertUiEventKategori = InsertUiEventKategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)

// UI State untuk Kategori
data class InsertUiStateKategori(
    val insertUiEventKategori: InsertUiEventKategori = InsertUiEventKategori(),
    val isEntryValid: FormKategoriErrorState = FormKategoriErrorState()
)

// Event untuk Insert Kategori
data class InsertUiEventKategori(
    val id_kategori: Int? = null,
    val nama_kategori: String = "",
    val deskripsi_kategori: String = ""
)
