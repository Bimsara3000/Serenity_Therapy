package lk.ijse.gdse71.serenity_therapy.bo.custom;

import lk.ijse.gdse71.serenity_therapy.bo.SuperBO;
import lk.ijse.gdse71.serenity_therapy.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;;
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
}
