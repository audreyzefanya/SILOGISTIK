package apap.ti.silogistik2106650443;

import apap.ti.silogistik2106650443.model.Gudang;
import apap.ti.silogistik2106650443.model.Karyawan;
import apap.ti.silogistik2106650443.service.GudangService;
import apap.ti.silogistik2106650443.service.KaryawanService;
import jakarta.transaction.Transactional;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.ZoneId;
import java.util.Locale;

@SpringBootApplication
public class Silogistik2106650443Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106650443Application.class, args);
	}

@Bean
@Transactional
CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService) {
    return args -> {
        if (gudangService.getAllGudang().isEmpty()) {
            Faker faker = new Faker(new Locale("in-ID"));
            for (int i = 0; i < 5; i++) {
                Gudang gudang = new Gudang();
                gudang.setNamaGudang("Gudang " + faker.country().name());
                gudang.setAlamatGudang(faker.address().streetAddress());
                gudangService.addGudang(gudang);
            }
        }

        if (karyawanService.getAllKaryawan().isEmpty()) {
            Faker faker = new Faker(new Locale("in-ID"));
            for (int i = 0; i < 5; i++) {
                Karyawan karyawan = new Karyawan();
                karyawan.setNama(faker.name().name());
                karyawan.setJenisKelamin(faker.options().option(0, 1));
                karyawan.setTanggalLahir(faker.date().birthday().toInstant().atZone(ZoneId.of(faker.options().option("Asia/Jakarta", "Asia/Bangkok"))).toLocalDate());
                karyawanService.addKaryawan(karyawan);
            }
        }
    };
}
}