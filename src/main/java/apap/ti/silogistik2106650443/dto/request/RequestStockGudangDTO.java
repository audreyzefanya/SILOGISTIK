package apap.ti.silogistik2106650443.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStockGudangDTO {
    private Long id;
    private String namaGudang;
    private String alamatGudang;
    private List<BarangStockDTO> listGudangBarang;
}

