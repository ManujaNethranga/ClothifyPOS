package edu.icet.clothify.controller;

import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CurrentUserObject {

    private CurrentUserObject(){}

    private User user;

    private static CurrentUserObject currentUserObject =null;

    public static CurrentUserObject getInstance(){
        if(currentUserObject==null){
            return currentUserObject = new CurrentUserObject();
        }
        return currentUserObject;
    }

}
