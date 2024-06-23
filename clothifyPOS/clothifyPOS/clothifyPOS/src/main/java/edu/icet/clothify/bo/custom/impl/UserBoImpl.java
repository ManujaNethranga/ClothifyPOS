package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.UserBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.UserDao;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.UserEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);


    @Override
    public int userCount() {
        return userDao.getUserCount();
    }

    @Override
    public String userLastId() {
        return userDao.lastUserId();
    }

    @Override
    public Boolean save(User user) {
        return userDao.save(new ModelMapper().map(user, UserEntity.class));
    }

    @Override
    public ObservableList<User> getAllUserDetails() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        List<UserEntity> list = userDao.getAllUserDetails();
        list.forEach(element ->{
            userList.add(new ModelMapper().map(element,User.class));
        });
        return userList;
    }
}
