package org.example.simpanpinjam.dao

import org.example.simpanpinjam.db.Database

data class Laporan(
    val nama: String,
    val totalPinjaman: Double,
    val totalAngsuran: Double,
    val tunggakan: Double
)

class LaporanDao {

    fun getLaporanTunggakan(): List<Laporan> {
        val list = mutableListOf<Laporan>()

        Database.connect().use { c ->
            val rs = c.createStatement().executeQuery(
                """
                SELECT a.nama,
                       (p.pokok + (p.pokok * p.bunga * p.tenor / 12)) AS total,
                       IFNULL(SUM(an.jumlah),0) AS bayar,
                       (p.pokok + (p.pokok * p.bunga * p.tenor / 12)) 
                       - IFNULL(SUM(an.jumlah),0) AS tunggakan
                FROM pinjaman p
                JOIN anggota a ON p.anggota_id = a.id
                LEFT JOIN angsuran an ON p.id = an.pinjaman_id
                GROUP BY p.id
                """
            )

            while (rs.next()) {
                list.add(
                    Laporan(
                        rs.getString("nama"),
                        rs.getDouble("total"),
                        rs.getDouble("bayar"),
                        rs.getDouble("tunggakan")
                    )
                )
            }
        }

        return list
    }
}