package org.example.simpanpinjam.ui

import org.example.simpanpinjam.dao.SimpananDao
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout

class SimpananFrame : JFrame("Data Simpanan") {

    private val dao = SimpananDao()
    private val model = DefaultTableModel(arrayOf("ID","Anggota","Jumlah"),0)
    private val table = JTable(model)

    private val txtAnggota = JTextField(5)
    private val txtJumlah = JTextField(10)

    init {
        setSize(500,400)
        layout = BorderLayout()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(null)

        val panel = JPanel()
        val btnTambah = JButton("Tambah")
        val btnHapus = JButton("Hapus")

        panel.add(JLabel("ID Anggota"))
        panel.add(txtAnggota)
        panel.add(JLabel("Jumlah"))
        panel.add(txtJumlah)
        panel.add(btnTambah)
        panel.add(btnHapus)

        add(panel, BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)

        loadData()

        btnTambah.addActionListener {
            dao.insert(txtAnggota.text.toInt(), txtJumlah.text.toDouble())
            loadData()
        }

        btnHapus.addActionListener {
            val row = table.selectedRow
            if (row >= 0)
                dao.delete(model.getValueAt(row,0) as Int)
            loadData()
        }
    }

    private fun loadData() {
        model.rowCount = 0
        dao.getAll().forEach {
            model.addRow(arrayOf(it.id, it.anggotaId, it.jumlah))
        }
    }
}
