package org.example.simpanpinjam

import org.example.simpanpinjam.db.Database
import org.example.simpanpinjam.ui.MenuUtama
import javax.swing.SwingUtilities

fun main() {
    // Inisialisasi database & tabel
    Database.init()

    // Jalankan UI
    SwingUtilities.invokeLater {
        MenuUtama().isVisible = true
    }
}