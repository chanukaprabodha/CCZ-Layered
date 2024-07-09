package lk.ijse.ccz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Inventory {
    private String id;
    private String name;
    private double stock;
    private double price;

    public Inventory(String ingredientId, double ingredientQty) {

        this.id = ingredientId;
        this.stock = ingredientQty;
    }
}
