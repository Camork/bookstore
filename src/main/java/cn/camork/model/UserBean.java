package cn.camork.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserBean {

    private int userId;

    @NotEmpty(message = "{user.userName.notEmpty}")
    @Size(max = 10, message = "{user.userName.length}")
    private String userName;

    private String userPass;

    private int userRole;

    private int userAge;

    private int userSex;

    private String userEmail;

    public UserBean() {

    }

    public UserBean(int userId, String userName, String userPass, int userRole, int userAge, int userSex, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userRole = userRole;
        this.userAge = userAge;
        this.userSex = userSex;
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole=" + userRole +
                ", userAge=" + userAge +
                ", userSex=" + userSex +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
