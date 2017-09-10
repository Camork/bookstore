package cn.camork.service;

import cn.camork.model.UserBean;

/**
 * Created by Camork on 2017-05-01.
 *
 */
public interface UserService {

    UserBean userLogin(String userName);
    boolean regUser(UserBean userBean);
    boolean checkUserName(String userName);

}
