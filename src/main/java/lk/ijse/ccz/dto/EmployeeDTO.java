package lk.ijse.ccz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {

    private String employeeID;
    private String name;
    private String position;
    private String address;
    private String contact;

}
