package apap.ti.silogistik2106650443.controller;

import apap.ti.silogistik2106650443.model.*;
import apap.ti.silogistik2106650443.dto.request.CreateBarangPengirimanDTO;
import apap.ti.silogistik2106650443.dto.request.RequestPermintaanPengirimanDTO;
import apap.ti.silogistik2106650443.dto.response.PermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106650443.service.BarangService;
import apap.ti.silogistik2106650443.service.KaryawanService;
import apap.ti.silogistik2106650443.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106650443.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/permintaan-pengiriman")
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @GetMapping("")
    public String getAllPermintaanPengiriman(Model model) {
        model.addAttribute("listPermintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman());
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("{id}")
    public String getDetailPermintaanPengiriman(Model model, @PathVariable Long id) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = permintaanPengirimanBarangService.getPermintaanPengirimanBarangByPermintaanPengiriman(id);
        List<BigInteger> totalHarga = listPermintaanPengirimanBarang.stream()
            .map(barang -> barang.getBarang().getHargaBarang().multiply(BigInteger.valueOf(barang.getKuantitasPesanan())))
            .collect(Collectors.toList());

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        model.addAttribute("listPermintaanPengirimanBarang", listPermintaanPengirimanBarang);
        model.addAttribute("totalHarga", totalHarga);
        return "detail-permintaan-pengiriman";
    }

    @GetMapping("tambah")
    public String addPermintaanPengiriman(Model model) {
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();
        RequestPermintaanPengirimanDTO permintaanPengiriman = new RequestPermintaanPengirimanDTO();
        List<CreateBarangPengirimanDTO> listPermintaanPengirimanBarang = new ArrayList<CreateBarangPengirimanDTO>();
        listPermintaanPengirimanBarang.add(new CreateBarangPengirimanDTO());
        permintaanPengiriman.setListPermintaanPengirimanBarang(listPermintaanPengirimanBarang);

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "form-permintaan-pengiriman";
    }

    @PostMapping(value = "tambah", params = {"addRow"})
    public String addRowPermintaanPengirimanBarang (Model model, @ModelAttribute RequestPermintaanPengirimanDTO permintaanPengiriman) {
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();
        permintaanPengiriman.getListPermintaanPengirimanBarang().add(new CreateBarangPengirimanDTO());

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "form-permintaan-pengiriman";
    }

    @PostMapping(value = "tambah", params = {"submit"})
    public String submitPermintaanPengirimanBarang (Model model, @ModelAttribute RequestPermintaanPengirimanDTO permintaanPengiriman) {
        List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();
        PermintaanPengiriman newPermintaanPengiriman = new PermintaanPengiriman();
        Karyawan karyawan = karyawanService.getKaryawanById(permintaanPengiriman.getIdKaryawan());
        newPermintaanPengiriman.setKaryawan(karyawan);
        newPermintaanPengiriman.setBiayaPengiriman(permintaanPengiriman.getBiayaPengiriman());
        newPermintaanPengiriman.setTanggalPengiriman(permintaanPengiriman.getTanggalPengiriman());
        newPermintaanPengiriman.setJenisLayanan(permintaanPengiriman.getJenisLayanan());
        newPermintaanPengiriman.setNamaPenerima(permintaanPengiriman.getNamaPenerima());
        newPermintaanPengiriman.setAlamatPenerima(permintaanPengiriman.getAlamatPenerima());


        for (int i = 0; i < permintaanPengiriman.getListPermintaanPengirimanBarang().size(); i++) {
            PermintaanPengirimanBarang curr = new PermintaanPengirimanBarang();
            Barang barang = barangService.getBarangBySku(permintaanPengiriman.getListPermintaanPengirimanBarang().get(i).getSku());
            curr.setBarang(barang);
            curr.setKuantitasPesanan(permintaanPengiriman.getListPermintaanPengirimanBarang().get(i).getKuantitasPesanan());
            listPermintaanPengirimanBarang.add(curr);
        }
        permintaanPengirimanService.addPermintaanPengiriman(newPermintaanPengiriman, newPermintaanPengiriman.getKaryawan().getId(), newPermintaanPengiriman.getTanggalPengiriman(),
                newPermintaanPengiriman.getNamaPenerima(), newPermintaanPengiriman.getJenisLayanan(), newPermintaanPengiriman.getAlamatPenerima(),
                newPermintaanPengiriman.getBiayaPengiriman(), permintaanPengiriman.getListPermintaanPengirimanBarang());

        PermintaanPengirimanResponseDTO response = new PermintaanPengirimanResponseDTO();
        response.setNomorPengiriman(newPermintaanPengiriman.getNomorPengiriman());
        response.setId(newPermintaanPengiriman.getId());
        model.addAttribute("response", response);
        return "submit-permintaan-barang";
    };

    @GetMapping("{id}/cancel")
    public String cancelPermintaanPengiriman(Model model, @PathVariable Long id) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        boolean isCancelled = permintaanPengirimanService.isPengirimanLessThanOneDay(permintaanPengiriman);
        if (isCancelled) {
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanService.updatePermintaanPengiriman(permintaanPengiriman);
        }

        model.addAttribute("isCancelled", isCancelled);
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        model.addAttribute("id", permintaanPengiriman.getId());
        return "cancel-permintaan-pengiriman";
    }

}
