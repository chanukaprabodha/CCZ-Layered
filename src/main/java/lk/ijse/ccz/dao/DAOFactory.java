package lk.ijse.ccz.dao;

import lk.ijse.ccz.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,INVENTORY,ORDER,EMPLOYEE, CONFIRM_ORDER, ORDER_DETAIL
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
