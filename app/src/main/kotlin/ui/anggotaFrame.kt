package org.example.simpanpinjam.ui

import org.example.simpanpinjam.dao.AnggotaDao
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout

class AnggotaFrame : JFrame("Data Anggota") {

    private val dao = AnggotaDao()
    private val model = DefaultTableModel(arrayOf("ID","Nama"),0)
    private val table = JTable(model)
    private val txtNama = JTextField(15)

    init {
        setSize(500,400)
        layout = BorderLayout()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(null)

        val panel = JPanel()
        val btnTambah = JButton("Tambah")
        val btnUbah = JButton("Ubah")
        val btnHapus = JButton("Hapus")
        val btnReset = JButton("Reset")

        panel.add(JLabel("Nama"))
        panel.add(txtNama)
        panel.add(btnTambah)
        panel.add(btnUbah)
        panel.add(btnHapus)
        panel.add(btnReset)

        add(panel, BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)

        loadData()

        btnTambah.addActionListener {
            dao.insert(txtNama.text)
            reset()
        }

        btnUbah.addActionListener {
            val row = table.selectedRow
            if (row >= 0)
                dao.update(model.getValueAt(row,0) as Int, txtNama.text)
            reset()
        }

        btnHapus.addActionListener {
            val row = table.selectedRow
            if (row >= 0)
                dao.delete(model.getValueAt(row,0) as Int)
            reset()
        }

        btnReset.addActionListener { reset() }

        table.selectionModel.addListSelectionListener {
            val row = table.selectedRow
            if (row >= 0)
                txtNama.text = model.getValueAt(row,1).toString()
        }
    }

    private fun loadData() {
        model.rowCount = 0
        dao.getAll().forEach {
            model.addRow(arrayOf(it.id, it.nama))
        }
    }

    private fun reset() {
        txtNama.text = ""
        loadData()
    }
}
