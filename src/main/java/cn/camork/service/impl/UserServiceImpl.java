package cn.camork.service.impl;

import cn.camork.mapper.UserMapper;
import cn.camork.model.UserBean;
import cn.camork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Camork on 2017-05-01.
 * userBean service
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean userLogin(String userName) {
        UserBean a=userMapper.userLogin(userName);
        return a;
    }

    @Override
    public boolean regUser(UserBean userBean) {
        try {
            int i = userMapper.regUser(userBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkUserName(String userName) {
        UserBean u = new UserBean();
        u.setUserName(userName);

        try {
            int a = userMapper.checkUserName(u);
        } catch (Exception e) {
            return true;
        }

        return false;
    }
}
