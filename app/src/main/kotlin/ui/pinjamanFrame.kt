package org.example.simpanpinjam.ui

import org.example.simpanpinjam.dao.PinjamanDao
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout

class PinjamanFrame : JFrame("Data Pinjaman") {

    private val dao = PinjamanDao()
    private val model = DefaultTableModel(
        arrayOf("ID","Anggota","Pokok","Bunga","Tenor","Total"),0
    )
    private val table = JTable(model)

    private val txtAnggota = JTextField(5)
    private val txtPokok = JTextField(10)
    private val txtBunga = JTextField(5)
    private val txtTenor = JTextField(5)

    init {
        setSize(600,400)
        layout = BorderLayout()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(null)

        val panel = JPanel()
        val btnTambah = JButton("Tambah")

        panel.add(JLabel("Anggota"))
        panel.add(txtAnggota)
        panel.add(JLabel("Pokok"))
        panel.add(txtPokok)
        panel.add(JLabel("Bunga"))
        panel.add(txtBunga)
        panel.add(JLabel("Tenor"))
        panel.add(txtTenor)
        panel.add(btnTambah)

        add(panel, BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)

        loadData()

        btnTambah.addActionListener {
            dao.insert(
                txtAnggota.text.toInt(),
                txtPokok.text.toDouble(),
                txtBunga.text.toDouble(),
                txtTenor.text.toInt()
            )
            loadData()
        }
    }

    private fun loadData() {
        model.rowCount = 0
        dao.getAll().forEach {
            val total = it.pokok + (it.pokok * it.bunga * it.tenor / 12)
            model.addRow(arrayOf(it.id, it.anggotaId, it.pokok, it.bunga, it.tenor, total))
        }
    }
}
