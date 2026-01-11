package org.example.simpanpinjam.dao

import org.example.simpanpinjam.db.Database
import org.example.simpanpinjam.model.Simpanan

class SimpananDao {

    fun insert(anggotaId: Int, jumlah: Double) {
        Database.connect().use {
            val ps = it.prepareStatement(
                "INSERT INTO simpanan(anggota_id, jumlah) VALUES (?,?)"
            )
            ps.setInt(1, anggotaId)
            ps.setDouble(2, jumlah)
            ps.executeUpdate()
        }
    }

    fun delete(id: Int) {
        Database.connect().use {
            val ps = it.prepareStatement("DELETE FROM simpanan WHERE id=?")
            ps.setInt(1, id)
            ps.executeUpdate()
        }
    }

    fun getAll(): List<Simpanan> {
        val list = mutableListOf<Simpanan>()
        Database.connect().use {
            val rs = it.createStatement().executeQuery("SELECT * FROM simpanan")
            while (rs.next()) {
                list.add(
                    Simpanan(
                        rs.getInt("id"),
                        rs.getInt("anggota_id"),
                        rs.getDouble("jumlah")
                    )
                )
            }
        }
        return list
    }
}
