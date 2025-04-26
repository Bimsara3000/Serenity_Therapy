package lk.ijse.gdse71.serenity_therapy.bo;

import lk.ijse.gdse71.serenity_therapy.bo.custom.impl.PatientBOImpl;
import lk.ijse.gdse71.serenity_therapy.bo.custom.impl.ProgramBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }
    public static BOFactory getInstance() {
        return boFactory==null? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        PATIENT,PROGRAM
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case PATIENT: return new PatientBOImpl();
            case PROGRAM: return new ProgramBOImpl();
            default: return null;
        }
    }
}