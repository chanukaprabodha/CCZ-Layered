package lk.ijse.ccz.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerTm {
    private String customerID;
    private String name;
    private String email;
    private String address;
    private String contact;
}
