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
public class DeleteAccountErrors implements Serializable{
    private String deleteAdminRoleErrors;
    private String deleteMyselfErrors;

    public DeleteAccountErrors() {
    }

    public DeleteAccountErrors(String deleteAdminRoleErrors, String deleteMyselfErrors) {
        this.deleteAdminRoleErrors = deleteAdminRoleErrors;
        this.deleteMyselfErrors = deleteMyselfErrors;
    }

    public void setDeleteAdminRoleErrors(String deleteAdminRoleErrors) {
        this.deleteAdminRoleErrors = deleteAdminRoleErrors;
    }

    public void setDeleteMyselfErrors(String deleteMyselfErrors) {
        this.deleteMyselfErrors = deleteMyselfErrors;
    }

    public String getDeleteAdminRoleErrors() {
        return deleteAdminRoleErrors;
    }

    public String getDeleteMyselfErrors() {
        return deleteMyselfErrors;
    }
}
