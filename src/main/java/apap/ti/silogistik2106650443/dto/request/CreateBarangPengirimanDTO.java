package apap.ti.silogistik2106650443.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarangPengirimanDTO {

    private String sku;
    private Integer kuantitasPesanan;
}
