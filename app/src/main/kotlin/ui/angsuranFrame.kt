package org.example.simpanpinjam.ui

import org.example.simpanpinjam.dao.AngsuranDao
import org.example.simpanpinjam.dao.PinjamanDao
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.awt.BorderLayout

class AngsuranFrame : JFrame("Data Angsuran") {

    private val angsuranDao = AngsuranDao()
    private val pinjamanDao = PinjamanDao()

    private val model = DefaultTableModel(
        arrayOf("ID Pinjaman", "ID Anggota", "Total Pinjaman", "Total Dibayar"), 0
    )
    private val table = JTable(model)

    private val txtPinjamanId = JTextField(5)
    private val txtJumlah = JTextField(10)

    init {
        setSize(600, 400)
        layout = BorderLayout()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setLocationRelativeTo(null)

        val panel = JPanel()
        val btnTambah = JButton("Bayar Angsuran")
        val btnReset = JButton("Reset")

        panel.add(JLabel("ID Pinjaman"))
        panel.add(txtPinjamanId)
        panel.add(JLabel("Jumlah"))
        panel.add(txtJumlah)
        panel.add(btnTambah)
        panel.add(btnReset)

        add(panel, BorderLayout.NORTH)
        add(JScrollPane(table), BorderLayout.CENTER)

        loadData()

        btnTambah.addActionListener { tambahAngsuran() }
        btnReset.addActionListener {
            txtPinjamanId.text = ""
            txtJumlah.text = ""
        }
    }

    private fun tambahAngsuran() {
        if (txtPinjamanId.text.isBlank() || txtJumlah.text.isBlank()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong")
            return
        }

        try {
            angsuranDao.insert(
                txtPinjamanId.text.toInt(),
                txtJumlah.text.toDouble()
            )

            txtPinjamanId.text = ""
            txtJumlah.text = ""

            loadData()

        } catch (e: Exception) {
            JOptionPane.showMessageDialog(this, "Input tidak valid")
        }
    }

    private fun loadData() {
        model.rowCount = 0

        pinjamanDao.getAll().forEach { pinjaman ->
            val totalPinjaman =
                pinjaman.pokok + (pinjaman.pokok * pinjaman.bunga * pinjaman.tenor / 12)

            val totalBayar = angsuranDao.totalAngsuran(pinjaman.id)

            model.addRow(
                arrayOf(
                    pinjaman.id,
                    pinjaman.anggotaId,
                    totalPinjaman,
                    totalBayar
                )
            )
        }
    }
}
