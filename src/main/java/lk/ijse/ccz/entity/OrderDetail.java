package lk.ijse.ccz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetail {
    private String orderId;
    private String ingredientId;
    private double ingredientQty;
    private double unitPrice;
}
