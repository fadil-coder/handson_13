package org.example.simpanpinjam.db

import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val URL = "jdbc:sqlite:simpanpinjam.db"

    fun connect(): Connection = DriverManager.getConnection(URL)

    fun init() {
        connect().use { c ->
            c.createStatement().executeUpdate(
                """
                CREATE TABLE IF NOT EXISTS anggota(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nama TEXT
                );

                CREATE TABLE IF NOT EXISTS simpanan(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    anggota_id INTEGER,
                    jumlah REAL
                );

                CREATE TABLE IF NOT EXISTS pinjaman(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    anggota_id INTEGER,
                    pokok REAL,
                    bunga REAL,
                    tenor INTEGER
                );

                CREATE TABLE IF NOT EXISTS angsuran(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pinjaman_id INTEGER,
                    jumlah REAL
                );
                """
            )
        }
    }
}
