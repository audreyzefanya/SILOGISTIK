package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.model.GudangBarang;
import apap.ti.silogistik2106650443.model.RestockGudang;

import java.util.List;

public interface GudangBarangService {
    void restockGudang(List<GudangBarang> listGudangBarang);
    Integer countStokBarang(String sku);

}
