package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangRepository;

    @Override
    public List<Gudang> getAllGudang() {
        return gudangRepository.findAll();
    }

    @Override
    public List<Gudang> getAllGudangByBarang(String sku) {
        return gudangRepository.findAllGudangByListGudangBarang_Barang_skuOrderById(sku);
    }

    @Override
    public Gudang getGudangById(Long id) {
        Optional<Gudang> gudang = gudangRepository.findById(id);
        return gudang.orElse(null);
    }

    @Override
    public void addGudang(Gudang gudang) {
        gudangRepository.save(gudang);
    }
}