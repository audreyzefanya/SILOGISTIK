package apap.ti.silogistik2106650443.controller;

import apap.ti.silogistik2106650443.model.Barang;
import apap.ti.silogistik2106650443.dto.request.BarangStockDTO;
import apap.ti.silogistik2106650443.dto.request.RequestStockGudangDTO;
import apap.ti.silogistik2106650443.dto.response.StockGudangResponseDTO;
import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.model.GudangBarang;
import apap.ti.silogistik2106650443.service.BarangService;
import apap.ti.silogistik2106650443.service.GudangBarangService;
import apap.ti.silogistik2106650443.service.GudangService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apap.ti.silogistik2106650443.service.BarangService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gudang")
public class GudangController {

    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("")
    public String getAllGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping("{id}")
    public String getDetailGudang(Model model, @PathVariable Long id) {
        Gudang gudang = gudangService.getGudangById(id);
        model.addAttribute("gudang", gudang);
        return "detail-gudang";
    }

    @GetMapping("cari-barang")
    public String allBarang(Model model) {
        model.addAttribute("listBarang", barangService.getAllBarang());
        return "cari-barang";
    }

    @GetMapping(value = "cari-barang", params = "sku")
    public String getGudangByBarang(Model model, @RequestParam(value = "sku", required = false) String sku) {
        Barang barang = barangService.getBarangBySku(sku);
        List<GudangBarang> listGudangBarang = new ArrayList<>();

        if (barang != null) {
            listGudangBarang = barang.getListGudangBarang();
            model.addAttribute("listGudangBarang", listGudangBarang);
            model.addAttribute("listBarang", barangService.getAllBarang());
            return "cari-barang";
        } else {
            return "cari-barang";
        }
    }

    @GetMapping("{id}/restock-barang")
    public String restockGudangForm(Model model, @PathVariable Long id) {
        Gudang gudang = gudangService.getGudangById(id);
        List<Barang> listBarang = barangService.getAllBarang();
    
        RequestStockGudangDTO request = new RequestStockGudangDTO();
        request.setId(gudang.getId());
        request.setAlamatGudang(gudang.getAlamatGudang());
        request.setNamaGudang(gudang.getNamaGudang());
        request.setListGudangBarang(gudang.getListGudangBarang()
                .stream()
                .map(gudangBarang -> {
                    BarangStockDTO barangStock = new BarangStockDTO();
                    barangStock.setSku(gudangBarang.getBarang().getSku());
                    barangStock.setStok(gudangBarang.getStok());
                    return barangStock;
                })
                .collect(Collectors.toList()));
    
        if (request.getListGudangBarang().isEmpty()) {
            request.getListGudangBarang().add(new BarangStockDTO());
        }
    
        model.addAttribute("gudang", request);
        model.addAttribute("listBarang", listBarang);
        return "form-restock-barang";
    }

    @PostMapping(value = "{id}/restock-barang", params = {"addRow"})
    public String addRowRestockGudangForm(@ModelAttribute RequestStockGudangDTO gudang, Model model) {
        gudang.getListGudangBarang().add(new BarangStockDTO());
        model.addAttribute("gudang", gudang);
        model.addAttribute("listBarang", barangService.getAllBarang());
        return "form-restock-barang";
    }

    @PostMapping("{id}/restock-barang")
    public String restockBarang(@PathVariable Long id, @ModelAttribute RequestStockGudangDTO gudang, Model model) {
        Gudang gudangFromDB = gudangService.getGudangById(id);

        if (gudangFromDB.getListGudangBarang().isEmpty()) {
            gudangFromDB.setListGudangBarang(new ArrayList<>());
        }

        for (BarangStockDTO stockDTO : gudang.getListGudangBarang()) {
            Barang currentBarang = barangService.getBarangBySku(stockDTO.getSku());
            GudangBarang gudangBarang = new GudangBarang();
            gudangBarang.setGudang(gudangFromDB);
            gudangBarang.setBarang(currentBarang);
            gudangBarang.setStok(stockDTO.getStok());
            gudangFromDB.getListGudangBarang().add(gudangBarang);
        }

        gudangBarangService.restockGudang(gudangFromDB.getListGudangBarang());
        model.addAttribute("response", new StockGudangResponseDTO(gudangFromDB.getId()));
        return "submit-restock-barang";
    }

}