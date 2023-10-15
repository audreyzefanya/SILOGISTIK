package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Barang;
import apap.ti.silogistik2106650443.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    private BarangDb barangRepository;

    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @Override
    public List<Barang> getAllBarangSortedByMerkAsc() {
        return barangRepository.findAllByOrderByMerkAsc();
    }

    @Override
    public Barang addBarang(String merk, Integer tipeBarang, BigInteger hargaBarang) {
        Barang newBarang = new Barang();
        newBarang.setMerk(merk);
        newBarang.setTipeBarang(tipeBarang);
        newBarang.setHargaBarang(hargaBarang);
        newBarang.setSku(createSku(tipeBarang));
        return barangRepository.save(newBarang);
    }

    private String createSku(Integer tipeBarang) {
        String[] prefixes = {"ELEC", "CLOT", "FOOD", "COSM", "TOOL"};
        String current = prefixes[tipeBarang - 1];
        long jumlahBarang = barangRepository.count();
        return current.concat(String.format("%03d", jumlahBarang + 1));
    }

    @Override
    public Barang getBarangBySku(String sku) {
        Optional<Barang> barang = barangRepository.findBySku(sku);
        return barang.orElse(null);
    }
    
    @Override
    public void updateBarang(String sku, String merk, BigInteger hargaBarang) {
        Barang retrievedBarang = barangRepository.findBySku(sku).orElse(null);
        if (retrievedBarang != null) {
            retrievedBarang.setMerk(merk);
            retrievedBarang.setHargaBarang(hargaBarang);

            barangRepository.save(retrievedBarang);
        }
    }

    @Override
    public Barang deleteBarang(String sku) {
        return null;
    }

    @Override
    public Barang deleteBarang(Barang barang) {
        return null;
    }
}