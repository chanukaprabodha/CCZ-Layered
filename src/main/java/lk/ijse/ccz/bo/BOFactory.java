package lk.ijse.ccz.bo;

import lk.ijse.ccz.bo.custom.impl.ConfirmOrderBOImpl;
import lk.ijse.ccz.bo.custom.impl.CustomerBOImpl;
import lk.ijse.ccz.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.ccz.bo.custom.impl.InventoryBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,INVENTORY,CONFIRM_ORDER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case CONFIRM_ORDER:
                return new ConfirmOrderBOImpl();
            default:
                return null;
        }
    }
}
