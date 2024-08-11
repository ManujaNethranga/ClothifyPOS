package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.UserEntity;
import javafx.collections.ObservableList;

public interface UserBo extends SuperBo {


    int userCount();

    String userLastId();

    Boolean save(User user);

    ObservableList<User> getAllUserDetails();

    UserEntity getIdFromEmail(String email);

    Boolean update(UserEntity entity);
}
