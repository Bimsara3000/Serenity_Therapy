module lk.ijse.gdse71.serenity_therapy {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires java.mail;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;

    opens lk.ijse.gdse71.serenity_therapy.entity to org.hibernate.orm.core;
    opens lk.ijse.gdse71.serenity_therapy.config to jakarta.persistence;
    opens lk.ijse.gdse71.serenity_therapy.dto.tm to javafx.base;


    opens lk.ijse.gdse71.serenity_therapy to javafx.fxml;
    opens lk.ijse.gdse71.serenity_therapy.controller to javafx.fxml;
    exports lk.ijse.gdse71.serenity_therapy;
}