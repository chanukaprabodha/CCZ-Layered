package lk.ijse.ccz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Orders {
    private String orderId;
    private Date orderDate;
    private String customerID;
    private double totalAmount;
}
