package com.example.uaspam.ui.Navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

//produk
object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail-produk"
    override val titleRes = "DETAIL PRODUK"
    const val ID_produk = "idproduk"
    val routesWithArg = "$route/{$ID_produk}"
}

object DestinasiHome : DestinasiNavigasi {
    override val route = "homeproduk"
    override val titleRes = "HOME PRODUK"
}

object DestinasiEntry : DestinasiNavigasi {
    override val route = "itementry"
    override val titleRes = "TAMBAH PRODUK"
}

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "UPDATE PRODUK"
    const val ID_produk = "idproduk"
    val routesWithArg = "$route/{$ID_produk}"
}


// kategori
object DestinasiDetailKategori: DestinasiNavigasi {
    override val route = "detail_kategori"
    override val titleRes = "DETAIL KATEGORI"
    const val ID_kategori = "idkategori"
    val routesWithArg = "$route/{$ID_kategori}"
}

object DestinasiHomeKategori: DestinasiNavigasi {
    override val route = "homeKategori"
    override val titleRes= "HOME KATEGORI"
}

object DestinasiEntryKategori: DestinasiNavigasi{
    override val route = "item_entryktgr"
    override val titleRes= "TAMBAH KATEGORI"
}

object DestinasiUpdateKategori: DestinasiNavigasi{
    override val route = "updatektgr"
    override val titleRes = "UPDATE KATEGORI"
    const val ID_kategori = "idkategori"
    val routesWithArg = "$route/{$ID_kategori}"
}


//pemasok
object DestinasiDetailPemasok: DestinasiNavigasi {
    override val route = "detail_pemasok"
    override val titleRes = "DETAIL PEMASOK"
    const val ID_pemasok = "idpemasok"
    val routesWithArg = "$route/{$ID_pemasok}"
}

object DestinasiHomePemasok: DestinasiNavigasi {
    override val route = "homePemasok"
    override val titleRes= "HOME PEMASOK"
}

object DestinasiEntryPemasok: DestinasiNavigasi{
    override val route = "item_entrypmsk"
    override val titleRes= "TAMBAH PEMASOK"
}

object DestinasiUpdatePemasok: DestinasiNavigasi{
    override val route = "updatepmsk"
    override val titleRes = "UPDATE PEMASOK"
    const val ID_pemasok = "idpemasok"
    val routesWithArg = "$route/{$ID_pemasok}"
}

//merk
object DestinasiDetailMerk: DestinasiNavigasi {
    override val route = "detail_merk"
    override val titleRes = "DETAIL MEREK"
    const val ID_merk = "idmerk"
    val routesWithArg = "$route/{$ID_merk}"
}

object DestinasiHomeMerk: DestinasiNavigasi {
    override val route = "homeMerk"
    override val titleRes= "HOME MEREK"
}

object DestinasiEntryMerk: DestinasiNavigasi{
    override val route = "item_entrymerk"
    override val titleRes= "TAMBAH MEREK"
}

object DestinasiUpdateMerk: DestinasiNavigasi{
    override val route = "updatemerk"
    override val titleRes = "UPDATE MEREK"
    const val ID_merk = "idmerk"
    val routesWithArg = "$route/{$ID_merk}"

}