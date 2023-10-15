package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Barang;

import java.math.BigInteger;
import java.util.List;

public interface BarangService {
    List<Barang> getAllBarang();
    List<Barang> getAllBarangSortedByMerkAsc();
    Barang getBarangBySku(String sku);
    Barang addBarang(String merk, Integer tipeBarang, BigInteger hargaBarang);
    void updateBarang(String sku, String merk, BigInteger hargaBarang);
    Barang deleteBarang(String sku);
    Barang deleteBarang(Barang barang);
}