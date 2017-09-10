package cn.camork.action;

import cn.camork.model.UserBean;
import cn.camork.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Camork on 2017-05-01.
 * userBean fuc
 */

@Controller
@RequestMapping("/user")
public class UserAction {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String, ArrayList<String>> login(UserBean userBean,boolean checked) {
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, ArrayList<String>> m = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();

        if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(userBean.getUserName(), userBean.getUserPass());
                token.setRememberMe(checked);
            try {
                currentUser.login(token);
                array.add("登录成功");
            }
            catch (AuthenticationException ae) {
                if (ae instanceof IncorrectCredentialsException){
                    array.add("密码错误");
                }else {
                    array.add(ae.getMessage());
                }
                array.add("登录失败");
                System.out.println("登录失败: " + ae.getMessage());
            }
        }

        m.put("login", array);
        return m;

    }

    @ResponseBody
    @RequestMapping("/reg")
    public Map<String, ArrayList<String>> reg(@Validated UserBean userBean, BindingResult result, String userPassConfirm) {

        Map<String, ArrayList<String>> m = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();

        if (result.hasErrors()) {
            List<ObjectError> a = result.getAllErrors();

            for (ObjectError objectError : a) {
                System.out.println(objectError.getCode());
                System.out.println(objectError.getDefaultMessage());
                array.add(objectError.getDefaultMessage());
            }

            array.add("注册失败");

            m.put("reg", array);
            return m;
        }

        if (!userPassConfirm.equals(userBean.getUserPass())) {
            array.add("两次密码输入不一样");
            array.add("注册失败");
        } else {
            if (userService.regUser(userBean)) {
                array.add("注册成功");
            } else {
                array.add("用户名已被注册");
            }
        }
        m.put("reg", array);
        return m;
    }

    @ResponseBody
    @RequestMapping("/checkUserName")
    public Map<String, Boolean> checkUserName(@RequestParam String userName) throws Exception {

        Index.log.warn(userName);

        boolean available = userService.checkUserName(userName);
        Map<String, Boolean> m = new HashMap<>();

        m.put("available", available);

        return m;
    }


    /**
     * 用户登出
     */
    @ResponseBody
    @RequestMapping("/logout")
    public Map<String, String> logout() throws Exception {
        // getSession().invalidate();
        Map<String, String> m = new HashMap<>();
        m.put("logout", "yes");
        return m;
    }


}
