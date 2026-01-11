package org.example.simpanpinjam.dao

import org.example.simpanpinjam.db.Database

class AngsuranDao {

    fun insert(pinjamanId: Int, jumlah: Double) {
        Database.connect().use {
            val ps = it.prepareStatement(
                "INSERT INTO angsuran(pinjaman_id,jumlah) VALUES (?,?)"
            )
            ps.setInt(1, pinjamanId)
            ps.setDouble(2, jumlah)
            ps.executeUpdate()
        }
    }

    fun totalAngsuran(pinjamanId: Int): Double {
        Database.connect().use {
            val ps = it.prepareStatement(
                "SELECT IFNULL(SUM(jumlah),0) FROM angsuran WHERE pinjaman_id=?"
            )
            ps.setInt(1, pinjamanId)
            val rs = ps.executeQuery()
            return rs.getDouble(1)
        }
    }
}
