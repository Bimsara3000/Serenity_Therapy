package lk.ijse.gdse71.serenity_therapy.dao;

import lk.ijse.gdse71.serenity_therapy.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenity_therapy.dao.custom.impl.ProgramDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType{
        PATIENT,PROGRAM
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case PATIENT: return new PatientDAOImpl();
            case PROGRAM: return new ProgramDAOImpl();
            default: return null;
        }
    }
}
