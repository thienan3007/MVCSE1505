/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.registration;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class AccountErrors implements Serializable {

    private String usernameNoMatched;
    private String passwordNoMatched;
    private String usernameNotExisted;
    private String passwordWrong;
    private String usernameHasBeenRemoved;

    public AccountErrors() {
    }

    public AccountErrors(String usernameNoMatched, String passwordNoMatched, String usernameNotExisted, String passwordWrong, String usernameHasBeenRemoved) {
        this.usernameNoMatched = usernameNoMatched;
        this.passwordNoMatched = passwordNoMatched;
        this.usernameNotExisted = usernameNotExisted;
        this.passwordWrong = passwordWrong;
        this.usernameHasBeenRemoved = usernameHasBeenRemoved;
    }

    public String getUsernameNoMatched() {
        return usernameNoMatched;
    }

    public String getPasswordNoMatched() {
        return passwordNoMatched;
    }

    public void setUsernameNoMatched(String usernameNoMatched) {
        this.usernameNoMatched = usernameNoMatched;
    }

    public void setPasswordNoMatched(String passwordNoMatched) {
        this.passwordNoMatched = passwordNoMatched;
    }

    public String getPasswordWrong() {
        return passwordWrong;
    }

    public void setPasswordWrong(String passwordWrong) {
        this.passwordWrong = passwordWrong;
    }

    public String getUsernameNotExisted() {
        return usernameNotExisted;
    }

    public void setUsernameNotExisted(String usernameNotExisted) {
        this.usernameNotExisted = usernameNotExisted;
    }

    public void setUsernameHasBeenRemoved(String usernameHasBeenRemoved) {
        this.usernameHasBeenRemoved = usernameHasBeenRemoved;
    }

    public String getUsernameHasBeenRemoved() {
        return usernameHasBeenRemoved;
    }

}
