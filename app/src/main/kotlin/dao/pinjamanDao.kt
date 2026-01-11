package org.example.simpanpinjam.dao

import org.example.simpanpinjam.db.Database
import org.example.simpanpinjam.model.Pinjaman

class PinjamanDao {

    fun insert(anggotaId: Int, pokok: Double, bunga: Double, tenor: Int) {
        Database.connect().use {
            val ps = it.prepareStatement(
                "INSERT INTO pinjaman(anggota_id,pokok,bunga,tenor) VALUES (?,?,?,?)"
            )
            ps.setInt(1, anggotaId)
            ps.setDouble(2, pokok)
            ps.setDouble(3, bunga)
            ps.setInt(4, tenor)
            ps.executeUpdate()
        }
    }

    fun getAll(): List<Pinjaman> {
        val list = mutableListOf<Pinjaman>()
        Database.connect().use {
            val rs = it.createStatement().executeQuery("SELECT * FROM pinjaman")
            while (rs.next()) {
                list.add(
                    Pinjaman(
                        rs.getInt("id"),
                        rs.getInt("anggota_id"),
                        rs.getDouble("pokok"),
                        rs.getDouble("bunga"),
                        rs.getInt("tenor")
                    )
                )
            }
        }
        return list
    }
}
