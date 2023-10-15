package apap.ti.silogistik2106650443.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik2106650443.service.BarangService;
import apap.ti.silogistik2106650443.service.GudangService;
import apap.ti.silogistik2106650443.service.KaryawanService;
import apap.ti.silogistik2106650443.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {

    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gudang", gudangService.getAllGudang().size());
        model.addAttribute("barang", barangService.getAllBarang().size());
        model.addAttribute("permintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman().size());
        model.addAttribute("karyawan", karyawanService.getAllKaryawan().size());
        return "beranda";
    }
}