package com.example.uaspam.ui.ViewModel.produk

import PemasokRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.Merk
import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.Produk
import com.example.uaspam.repository.KategoriRepository
import com.example.uaspam.repository.MerkRepository
import com.example.uaspam.repository.ProdukRepository
import kotlinx.coroutines.launch

class InsertProdukViewModel(
    private val prdk: ProdukRepository,
    private val pmsk: PemasokRepository,
    private val ktgr: KategoriRepository,
    private val mrk: MerkRepository,
) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    var pmskList by mutableStateOf<List<Pemasok>>(emptyList())
    var ktgrList by mutableStateOf<List<Kategori>>(emptyList())
    var mrkList by mutableStateOf<List<Merk>>(emptyList())

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                pmskList = pmsk.getpemasok().data
                ktgrList = ktgr.getkategori().data
                mrkList = mrk.getmerk().data
            } catch (e: Exception) {
                errorMessage = "Gagal memuat data."
            }
        }
    }

    fun updateInsertprdkState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent)
    }

    fun validate(): Boolean {
        val event = uiState.insertUiEvent
        return when {
            event.nama_produk.isBlank() -> {
                errorMessage = "Nama produk tidak boleh kosong."
                false
            }
            event.deskripsi_produk.isBlank() -> {
                errorMessage = "Deskripsi produk tidak boleh kosong."
                false
            }
            event.harga == null || event.harga <= 0 -> {
                errorMessage = "Harga harus lebih dari 0."
                false
            }
            event.stok == null || event.stok < 0 -> {
                errorMessage = "Stok tidak boleh kurang dari 0."
                false
            }
            event.id_kategori == null || event.id_kategori <= 0 -> {
                errorMessage = "Pilih kategori yang valid."
                false
            }
            event.id_pemasok == null || event.id_pemasok <= 0 -> {
                errorMessage = "Pilih pemasok yang valid."
                false
            }
            event.id_merk == null || event.id_merk <= 0 -> {
                errorMessage = "Pilih merek yang valid."
                false
            }
            else -> {
                errorMessage = null
                true
            }
        }
    }

    fun insertprdk() {
        if (validate()) {
            viewModelScope.launch {
                try {
                    prdk.insertproduk(uiState.insertUiEvent.toprdk())
                    errorMessage = null
                } catch (e: Exception) {
                    errorMessage = "Gagal menyimpan produk."
                    e.printStackTrace()
                }
            }
        }
    }
}


fun InsertUiEvent.toprdk(): Produk = Produk(
    id_produk = id_produk ?: 0,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    harga = harga ?: 0,
    stok = stok ?: 0,
    id_kategori = id_kategori ?: 0,
    id_pemasok = id_pemasok ?: 0,
    id_merk = id_merk ?: 0,
)

fun Produk.toUiState(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Produk.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_produk = id_produk,
    nama_produk = nama_produk,
    deskripsi_produk = deskripsi_produk,
    harga = harga,
    stok = stok,
    id_kategori = id_kategori,
    id_pemasok = id_pemasok,
    id_merk = id_merk,

    )

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_produk: Int? = null,
    val nama_produk: String = "",
    val deskripsi_produk: String = "",
    val harga:Int? = null,
    val stok: Int? = null,
    val id_kategori: Int? = null,
    val id_pemasok: Int? = null,
    val id_merk: Int? = null,

    )