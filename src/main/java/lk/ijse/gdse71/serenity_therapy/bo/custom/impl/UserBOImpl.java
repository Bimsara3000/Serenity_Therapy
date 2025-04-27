package lk.ijse.gdse71.serenity_therapy.bo.custom.impl;

import lk.ijse.gdse71.serenity_therapy.bo.custom.UserBO;
import lk.ijse.gdse71.serenity_therapy.dao.DAOFactory;
import lk.ijse.gdse71.serenity_therapy.dao.custom.UserDAO;
import lk.ijse.gdse71.serenity_therapy.dto.PatientDTO;
import lk.ijse.gdse71.serenity_therapy.dto.UserDTO;
import lk.ijse.gdse71.serenity_therapy.entity.Patient;
import lk.ijse.gdse71.serenity_therapy.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) userDAO.getAll();

        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getRole()));
        }
        return userDTOS;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return userDAO.getNextId();
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setId(userDTO.getId());
        return userDAO.save(user);
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setId(userDTO.getId());
        return userDAO.update(user);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }
}
