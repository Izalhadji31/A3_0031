package com.example.uaspam

import android.app.Application
import com.example.uaspam.model.AppContainer
import com.example.uaspam.model.ProdukContainer

class ijalApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ProdukContainer()
    }
}