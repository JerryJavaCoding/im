package com.chavrusa.im.server.communication;

/**
 *
 * @author Jerry
 * @date 2017/11/22 0022
 */
public class CustomLoginResult {
    private boolean loginSuccess=false;
    private User user;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
