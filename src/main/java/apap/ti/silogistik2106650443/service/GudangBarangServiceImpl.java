package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Barang;
import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.model.GudangBarang;
import apap.ti.silogistik2106650443.model.RestockGudang;
import apap.ti.silogistik2106650443.repository.BarangDb;
import apap.ti.silogistik2106650443.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GudangBarangServiceImpl implements GudangBarangService{

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    BarangDb barangDb;

    @Override
    public void restockGudang(List<GudangBarang> listGudangBarang) {
        gudangBarangDb.saveAll(listGudangBarang);
    }

    @Override
    public Integer countStokBarang(String sku) {
        List<GudangBarang> listGudangBarang = gudangBarangDb.findGudangBarangByBarang_SkuOrderById(sku);
        Integer totalStok = 0;
        for (GudangBarang gudangBarang : listGudangBarang) {
            totalStok += gudangBarang.getStok();
        }
        return totalStok;
    }
}