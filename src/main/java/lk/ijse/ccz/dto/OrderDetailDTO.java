package lk.ijse.ccz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDTO {
    private String orderId;
    private String ingredientId;
    private double ingredientQty;
    private double unitPrice;
}
