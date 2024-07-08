package lk.ijse.ccz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class InventoryDTO {

    private String id;
    private String name;
    private double stock;
    private double price;

}
