package com.example.uaspam.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.ijalApplication
import com.example.uaspam.ui.ViewModel.kategori.DetailKategoriViewModel
import com.example.uaspam.ui.ViewModel.kategori.HomeKategoriViewModel
import com.example.uaspam.ui.ViewModel.kategori.InsertKategoriViewModel
import com.example.uaspam.ui.ViewModel.kategori.UpdateKategoriViewModel
import com.example.uaspam.ui.ViewModel.merk.DetailMerkViewModel
import com.example.uaspam.ui.ViewModel.merk.HomeMerkViewModel
import com.example.uaspam.ui.ViewModel.merk.InsertMerkViewModel
import com.example.uaspam.ui.ViewModel.merk.UpdateMerkViewModel
import com.example.uaspam.ui.ViewModel.pemasok.DetailPemasokViewModel
import com.example.uaspam.ui.ViewModel.pemasok.HomePemasokViewModel
import com.example.uaspam.ui.ViewModel.pemasok.InsertPemasokViewModel
import com.example.uaspam.ui.ViewModel.pemasok.UpdatePemasokViewModel
import com.example.uaspam.ui.ViewModel.produk.DetailProdukViewModel
import com.example.uaspam.ui.ViewModel.produk.HomeProdukViewModel
import com.example.uaspam.ui.ViewModel.produk.InsertProdukViewModel
import com.example.uaspam.ui.ViewModel.produk.UpdateProdukViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeProdukViewModel(prdkApp().container.produkRepository) }
        initializer { InsertProdukViewModel(prdkApp().container.produkRepository,prdkApp().container.pemasokRepository,prdkApp().container.kategoriRepository,prdkApp().container.merkRepository) }
        initializer { DetailProdukViewModel(createSavedStateHandle(),prdkApp().container.produkRepository) }
        initializer { UpdateProdukViewModel(createSavedStateHandle(),prdkApp().container.produkRepository, prdkApp().container.pemasokRepository,prdkApp().container.kategoriRepository,prdkApp().container.merkRepository) }

        initializer { HomeKategoriViewModel(prdkApp().container.kategoriRepository) }
        initializer { InsertKategoriViewModel(prdkApp().container.kategoriRepository) }
        initializer { DetailKategoriViewModel(createSavedStateHandle(),prdkApp().container.kategoriRepository) }
        initializer { UpdateKategoriViewModel(createSavedStateHandle(),prdkApp().container.kategoriRepository) }

        initializer { HomePemasokViewModel(prdkApp().container.pemasokRepository) }
        initializer { InsertPemasokViewModel(prdkApp().container.pemasokRepository) }
        initializer { DetailPemasokViewModel(createSavedStateHandle(),prdkApp().container.pemasokRepository) }
        initializer { UpdatePemasokViewModel(createSavedStateHandle(),prdkApp().container.pemasokRepository) }

        initializer { HomeMerkViewModel(prdkApp().container.merkRepository) }
        initializer { InsertMerkViewModel(prdkApp().container.merkRepository) }
        initializer { DetailMerkViewModel(createSavedStateHandle(),prdkApp().container.merkRepository) }
        initializer { UpdateMerkViewModel(createSavedStateHandle(),prdkApp().container.merkRepository) }
    }
}

fun CreationExtras.prdkApp(): ijalApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as ijalApplication)