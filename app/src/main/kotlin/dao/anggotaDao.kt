package org.example.simpanpinjam.dao

import org.example.simpanpinjam.db.Database
import org.example.simpanpinjam.model.Anggota

class AnggotaDao {

    fun insert(nama: String) {
        Database.connect().use {
            val ps = it.prepareStatement("INSERT INTO anggota(nama) VALUES(?)")
            ps.setString(1, nama)
            ps.executeUpdate()
        }
    }

    fun update(id: Int, nama: String) {
        Database.connect().use {
            val ps = it.prepareStatement("UPDATE anggota SET nama=? WHERE id=?")
            ps.setString(1, nama)
            ps.setInt(2, id)
            ps.executeUpdate()
        }
    }

    fun delete(id: Int) {
        Database.connect().use {
            val ps = it.prepareStatement("DELETE FROM anggota WHERE id=?")
            ps.setInt(1, id)
            ps.executeUpdate()
        }
    }

    fun getAll(): List<Anggota> {
        val list = mutableListOf<Anggota>()
        Database.connect().use {
            val rs = it.createStatement().executeQuery("SELECT * FROM anggota")
            while (rs.next()) {
                list.add(Anggota(rs.getInt("id"), rs.getString("nama")))
            }
        }
        return list
    }
}
