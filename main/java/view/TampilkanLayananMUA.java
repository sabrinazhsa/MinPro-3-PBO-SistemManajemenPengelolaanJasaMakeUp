/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.LayananCRUD;
import controller.LayananValidator;
import model.MakeUpPengantin;
import model.MakeUpWisuda;
import model.Pelanggan;
import model.PemesananLayananMUA;
import java.util.Scanner;

/**
 *
 * @author HP VICTUS
 */

public class TampilkanLayananMUA {

    private Scanner scanner = new Scanner(System.in);
    private LayananCRUD crud = new LayananCRUD();

    public void jalankan() {
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n==============================================================");
            System.out.println("   SELAMAT DATANG DI JASA MAKE UP ARTIS BY GRANDE 100% HALAL.");
            System.out.println("==============================================================");
            System.out.println("1. Lihat Semua Pemesanan");
            System.out.println("2. Tambah Pemesanan");
            System.out.println("3. Update Pemesanan");
            System.out.println("4. Hapus Pemesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    crud.tampilkanSemuaPemesanan();
                    break;

                case "2":
                    menuTambahPemesanan();
                    break;

                case "3":
                    menuUpdatePemesanan();
                    break;

                case "4":
                    menuHapusPemesanan();
                    break;

                case "5":
                    berjalan = false;
                    System.out.println("Terima kasih! Have a nice day.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    private void menuTambahPemesanan() {
        System.out.println("\n--- TAMBAH PESANAN BARU ---");

        String namaKlien = LayananValidator.inputTeks(scanner, "Masukkan Nama Pelanggan:  ");
        String alamatKlien = LayananValidator.inputTeks(scanner, "Masukkan Alamat Pelanggan: ");

        String noHpKlien = LayananValidator.inputNomorHp(scanner, "Masukkan Nomor HP Klien: ");
        String tanggalPelaksanaan = LayananValidator.inputTanggal(scanner, "Masukkan Tanggal Pengerjaan (d/M/yyyy): ");

        Pelanggan pelanggan = new Pelanggan(namaKlien, alamatKlien, noHpKlien);

        System.out.println("\nPilih Kategori Make Up:");
        System.out.println("1. Make Up Wisuda");
        System.out.println("2. Make Up Pengantin");
        int kategori; 
        while (true) {
        kategori = LayananValidator.inputAngka(scanner, "Pilihan Kategori (1/2): ");
        if (kategori == 1 || kategori == 2) {
            break;
        }
        System.out.println("Pilihan kategori tidak tersedia!");
    }
            
        PemesananLayananMUA pesanan = null;    
        String idPesanan = crud.generateIdPesanan();
        

        if (kategori == 1) {
            int jumlahOrang = LayananValidator.inputAngka(scanner, "Masukkan Jumlah Orang yang Dirias: ");
            boolean retouch = LayananValidator.inputYesNo(scanner, "Tambah Retouch Kit? (Ya/Tidak): ");

            pesanan = new MakeUpWisuda(idPesanan, tanggalPelaksanaan, "Pending", pelanggan, jumlahOrang, retouch);

        } else if (kategori == 2) {
            int jumlahSesi = LayananValidator.inputAngka(scanner, "Masukkan Jumlah Sesi Rias (contoh: 1 atau 2): ");
            boolean sanggul = LayananValidator.inputYesNo(scanner, "Include Sanggul/Hairdo? (Ya/Tidak): ");

            pesanan = new MakeUpPengantin(idPesanan, tanggalPelaksanaan, "Pending", pelanggan, jumlahSesi, sanggul);

        } else {
            System.out.println("\nPilihan kategori tidak valid!");
        }

        if (pesanan != null) {
            terapkanDiskonMember(pesanan);
            crud.tambahPemesanan(pesanan);
        }
    }
    
     private void terapkanDiskonMember(PemesananLayananMUA pesanan) {
        System.out.println("Total biaya sebelum diskon: Rp " + (long) pesanan.hitungTotalBiaya());

        boolean isMember = LayananValidator.inputYesNo(scanner, "Apakah pelanggan member (diskon 10%)? (Ya/Tidak): ");
        
        if (isMember) {
            double totalSetelahDiskon = pesanan.hitungTotalBiaya(10);
            pesanan.setTotalHarga(totalSetelahDiskon);
        }
        System.out.println("Total biaya yang harus dibayar: Rp " + (long) pesanan.getTotalHarga());
     }

    private void menuUpdatePemesanan() {
        String idUp = LayananValidator.inputTeks(scanner, "Masukkan ID Pesanan yang diupdate: ");
        PemesananLayananMUA pesananUpdate = crud.cariPemesanan(idUp);

        if (pesananUpdate == null) {
            System.out.println("ID Pesanan tidak ada!");
            return;
        }
        
        String statusUp = LayananValidator.inputStatusPembayaran(scanner, "Masukkan status pembayaran yang baru: ");

        crud.updatePemesanan(idUp, statusUp);
    }

    private void menuHapusPemesanan() {
        String idHapus = LayananValidator.inputTeks(scanner, "Masukkan ID Pesanan yang dihapus: ");
        PemesananLayananMUA pesananHapus = crud.cariPemesanan(idHapus);

        if (pesananHapus == null) {
            System.out.println("ID Pesanan tidak ada!");
            return;
        }

        crud.hapusPemesanan(idHapus);
    }
}