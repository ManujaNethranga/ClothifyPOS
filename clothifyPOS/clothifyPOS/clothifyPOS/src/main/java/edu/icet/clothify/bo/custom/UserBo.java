package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.User;
import javafx.collections.ObservableList;

public interface UserBo extends SuperBo {


    int userCount();

    String userLastId();

    Boolean save(User user);

    ObservableList<User> getAllUserDetails();
}
