package org.example.simpanpinjam.ui

import org.example.simpanpinjam.dao.LaporanDao
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout

class LaporanFrame : JFrame("Laporan Tunggakan") {

    private val dao = LaporanDao()

    private val model = DefaultTableModel(
        arrayOf("Anggota","Total Pinjaman","Total Angsuran","Tunggakan"), 0
    )
    private val table = JTable(model)

    init {
        setSize(600,400)
        layout = BorderLayout()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(null)

        add(JScrollPane(table), BorderLayout.CENTER)
        loadData()
    }

    private fun loadData() {
        model.rowCount = 0
        dao.getLaporanTunggakan().forEach {
            model.addRow(
                arrayOf(
                    it.nama,
                    it.totalPinjaman,
                    it.totalAngsuran,
                    it.tunggakan
                )
            )
        }
    }
}