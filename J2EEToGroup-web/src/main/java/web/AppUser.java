/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class AppUser implements Serializable {

    private Long userId;

    private String userName;

    private String encryptedPassword;

    private String userEmail;

    private Integer enabled;

    private Date dateCreated;

    public AppUser() {
    }

    public AppUser(String userName, String encryptedPassword, String userEmail, boolean enabled) {
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.userEmail = userEmail;
        this.enabled = enabled ? 1 : 0;
        this.dateCreated = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Integer isEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public String toString() {
        return "{id:" + userId + "userName:" + this.userEmail + ", password: " + this.encryptedPassword + "}";
    }
}

