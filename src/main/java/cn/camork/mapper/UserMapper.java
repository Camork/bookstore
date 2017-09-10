package cn.camork.mapper;

import cn.camork.model.UserBean;

public interface UserMapper {
    UserBean userLogin(String userName);
    int regUser(UserBean userBean);
    int checkUserName(UserBean userBean);
}
