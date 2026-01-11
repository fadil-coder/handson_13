package org.example.simpanpinjam.ui

import javax.swing.*
import java.awt.GridLayout

class MenuUtama : JFrame("Menu Utama") {
    init {
        setSize(300,300)
        layout = GridLayout(5,1)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)

        val b1 = JButton("Anggota")
        val b2 = JButton("Simpanan")
        val b3 = JButton("Pinjaman")
        val b4 = JButton("Angsuran")
        val b5 = JButton("Laporan")

        add(b1); add(b2); add(b3); add(b4); add(b5)

        b1.addActionListener { AnggotaFrame().isVisible = true }
        b2.addActionListener { SimpananFrame().isVisible = true }
        b3.addActionListener { PinjamanFrame().isVisible = true }
        b4.addActionListener { AngsuranFrame().isVisible = true }
        b5.addActionListener { LaporanFrame().isVisible = true }
    }
}
