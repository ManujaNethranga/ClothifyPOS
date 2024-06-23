package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.UserEntity;

import java.util.List;

public interface UserDao  extends CrudDao<UserEntity> {
    int getUserCount();

    String lastUserId();

    List<UserEntity> getAllUserDetails();
}
