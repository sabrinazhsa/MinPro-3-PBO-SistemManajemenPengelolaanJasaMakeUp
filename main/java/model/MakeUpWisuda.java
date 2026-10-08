/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */

public class MakeUpWisuda extends PemesananLayananMUA {
 
    private static final double hargaPerOrang = 250000;
    private static final double hargaRetouchKit = 50000;
 
    private int jumlahOrangDirias;
    private boolean adaRetouchKit;
 
    public MakeUpWisuda(String idPesanan, String tanggalPesanan, String status, Pelanggan pelanggan,
                         int jumlahOrangDirias, boolean adaRetouchKit) {
        super(idPesanan, tanggalPesanan, "Make Up Wisuda", 0, status, pelanggan);
        this.jumlahOrangDirias = jumlahOrangDirias;
        this.adaRetouchKit = adaRetouchKit;
        this.setTotalHarga(hitungTotalBiaya());
    }
 
    @Override
    public double hitungTotalBiaya() {
        double total = hargaPerOrang * jumlahOrangDirias;
        if (adaRetouchKit) {
            total += hargaRetouchKit;
        }
        return total;
    }
 
    @Override
    public void tampilkanDetailPesanan() {
        super.tampilkanDetailPesanan();
        System.out.println("Jumlah Orang     : " + jumlahOrangDirias + " Orang");
        System.out.println("Retouch Kit      : " + (adaRetouchKit ? "Ya (+Rp50.000)" : "Tidak"));
        System.out.println("Total Biaya      : Rp " + (long) getTotalHarga());
    }
    
    public int getJumlahOrangDirias() { return jumlahOrangDirias; }
    public void setJumlahOrangDirias(int jumlahOrangDirias) {
        this.jumlahOrangDirias = jumlahOrangDirias;
        this.setTotalHarga(hitungTotalBiaya());
    }
    public boolean isAdaRetouchKit() { return adaRetouchKit; }
    public void setAdaRetouchKit(boolean adaRetouchKit) {
        this.adaRetouchKit = adaRetouchKit;
        this.setTotalHarga(hitungTotalBiaya());
    }
}