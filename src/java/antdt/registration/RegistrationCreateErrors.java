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
public class RegistrationCreateErrors implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullNameLengthError;
    private String confirmNoMatched;
    private String usernameIsExisted;

    public RegistrationCreateErrors() {
    }

    public RegistrationCreateErrors(String usernameLengthError, String passwordLengthError, String fullNameLengthError, String confirmNoMatched, String usernameIsExisted) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.fullNameLengthError = fullNameLengthError;
        this.confirmNoMatched = confirmNoMatched;
        this.usernameIsExisted = usernameIsExisted;
    }
    
    public String getConfirmNoMatched() {
        return confirmNoMatched;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setConfirmNoMatched(String confirmNoMatched) {
        this.confirmNoMatched = confirmNoMatched;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
