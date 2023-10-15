package apap.ti.silogistik2106650443.controller;

import apap.ti.silogistik2106650443.model.Barang;
import apap.ti.silogistik2106650443.dto.request.CreateBarangDTO;
import apap.ti.silogistik2106650443.dto.request.UpdateBarangDTO;
import apap.ti.silogistik2106650443.dto.response.AddBarangResponseDTO;
import apap.ti.silogistik2106650443.dto.response.UbahBarangResponseDTO;
import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.model.GudangBarang;
import apap.ti.silogistik2106650443.service.BarangService;
import apap.ti.silogistik2106650443.service.GudangBarangService;
import apap.ti.silogistik2106650443.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/barang")
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("")
    public String getAllBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

    @GetMapping("/tambah")
    public String addBarang(Model model) {
        model.addAttribute("barang", new CreateBarangDTO());
        return "tambah-barang";
    }

    @PostMapping("/tambah")
    public String addBarang(Model model, @ModelAttribute CreateBarangDTO barang) {
        AddBarangResponseDTO response = new AddBarangResponseDTO();
        Barang newBarang = barangService.addBarang(barang.getMerk(), barang.getTipeBarang(), barang.getHargaBarang());

        response.setMerk(newBarang != null ? newBarang.getMerk() : null);
        model.addAttribute("response", response);
        return "submit-barang";
    }

    @GetMapping("{id}")
    public String getDetailBarang(Model model, @PathVariable String id) {
        Barang barang = barangService.getBarangBySku(id);
        List<Gudang> listGudang = gudangService.getAllGudangByBarang(id);
        List<Integer> listStok = listGudang.stream()
                .flatMap(gudang -> gudang.getListGudangBarang().stream())
                .filter(gudangBarang -> gudangBarang.getBarang().getSku().equals(id))
                .map(GudangBarang::getStok)
                .collect(Collectors.toList());
        Integer totalStok = listStok.stream().reduce(0, Integer::sum);

        model.addAttribute("barang", barang);
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listStok", listStok);
        model.addAttribute("totalStok", totalStok);
        return "detail-barang";
    }

    @GetMapping("{id}/ubah")
    public String updateDetailBarang(Model model, @PathVariable String id) {
        Barang barang = barangService.getBarangBySku(id);
        UpdateBarangDTO updateBarangDTO = new UpdateBarangDTO(barang.getSku(), barang.getMerk(), barang.getTipeBarang(), barang.getHargaBarang());
        model.addAttribute("barang", updateBarangDTO);
        return "update-barang";
    }

    @PostMapping("{id}/ubah")
    public String submitUpdateDetailBarang(Model model, @PathVariable String id, @ModelAttribute UpdateBarangDTO barang) {
        barangService.updateBarang(id, barang.getMerk(), barang.getHargaBarang());
        UbahBarangResponseDTO response = new UbahBarangResponseDTO();
        response.setMerk(barang.getMerk());
        response.setSku(id);
        model.addAttribute("barang", response);
        return "submit-update-barang";
    }

}