/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;
import model.Pelanggan;
import model.PemesananLayananMUA;
import model.MakeUpPengantin;
import model.MakeUpWisuda;

/**
 *
 * @author HP VICTUS
 */
public class LayananCRUD {

    private final ArrayList<PemesananLayananMUA> daftarPesanan = new ArrayList<>();
    private int counterId = 1;
 
    public LayananCRUD() {
        tampilDataAwal();
    }
 
    private void tampilDataAwal() {
        Pelanggan pelanggan1 = new Pelanggan("Madison Brr", "Jl. Melati No. 10, Samarinda", "081234567890");
        PemesananLayananMUA pesanan1 = new MakeUpWisuda(generateIdPesanan(), "20/09/2026", "Lunas", pelanggan1, 1, true);
        
        daftarPesanan.add(pesanan1);
 
    }
    public String generateIdPesanan() {
        return "P0" + String.format("%01d", counterId++);
    }
    
    public void tambahPemesanan(PemesananLayananMUA pesanan) {
        if (pesanan == null) {
            System.out.println("Data pesanan tidak boleh kosong!");
            return;
        }

        if (cariPemesanan(pesanan.getIdPesanan()) != null) {
            System.out.println("ID Pesanan [" + pesanan.getIdPesanan() + "] sudah digunakan. Gunakan ID lain!");
            return;
        }

        daftarPesanan.add(pesanan);
        System.out.println("Pesanan dengan ID [" + pesanan.getIdPesanan() + "] berhasil ditambahkan!");
    }

    public void tampilkanSemuaPemesanan() {
    if (daftarPesanan.isEmpty()) {
        System.out.println("Belum ada pemesanan yang masuk.");
        return;
    }

    NumberFormat formatRupiah = NumberFormat.getNumberInstance(new Locale("in", "ID"));

    System.out.println("\n--- DAFTAR PESANAN ---");

    for (int i = 0; i < daftarPesanan.size(); i++) {

        PemesananLayananMUA p = daftarPesanan.get(i);
        Pelanggan pelanggan = p.getPelanggan();
        
        System.out.println("   -----------------------------------------------------------------");
        System.out.println((i + 1) + ". ID Pesanan          : " + p.getIdPesanan());
        System.out.println("   Nama Pelanggan      : " + pelanggan.getNamaPelanggan());
        System.out.println("   Alamat Pelanggan    : " + pelanggan.getAlamat());
        System.out.println("   Nomor HP            : " + pelanggan.getNoHP());
        System.out.println("   Jenis Makeup        : " + p.getJenisMakeup());
        System.out.println("   Tanggal Pengerjaan  : " + p.getTanggalPesanan());
        System.out.println("   Total Harga         : Rp" + formatRupiah.format(p.getTotalHarga()));
        System.out.println("   Status Pembayaran   : " + p.getStatus());
        System.out.println("   -----------------------------------------------------------------");
    }
}

    public PemesananLayananMUA cariPemesanan(String getIdPesanan) {
        if (getIdPesanan == null) {
            return null;
        }
        for (PemesananLayananMUA p : daftarPesanan) {
            if (p.getIdPesanan().equals(getIdPesanan)) {
                return p;
            }
        }
        return null;
    }

    public void updatePemesanan(String getIdPesanan, String statusBaru) {
        PemesananLayananMUA p = cariPemesanan(getIdPesanan);

        if (p == null) {
            System.out.println("Pesanan dengan ID [" + getIdPesanan + "] tidak ditemukan.");
            return;
        }
        if (statusBaru == null || statusBaru.trim().isEmpty()) {
        System.out.println("Tidak ada perubahan, status tetap \"" + p.getStatus() + "\".");
        return;
    }
        p.setStatus(statusBaru);
        System.out.println("Data pesanan [" + getIdPesanan + "] berhasil diperbarui!");
    }

    public void hapusPemesanan(String idPesananCari) {
        boolean berhasil = daftarPesanan.removeIf(p -> p.getIdPesanan().equals(idPesananCari));
        if (berhasil) {
            System.out.println("Pesanan [" + idPesananCari + "] berhasil dihapus!");
        } else {
            System.out.println("Pesanan dengan ID [" + idPesananCari + "] tidak ada!!!");
            
        }
        
    }
}
