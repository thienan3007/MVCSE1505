/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import antdt.utils.DBHelpers;

public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.DB Connect
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select username , password, lastname "
                        + " From Registration "
                        + " Where username = ? And password = ? And status = 'true'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
//                    String lastname = rs.getString("lastname");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
//                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);
                    return true;
                }
            }//end connecttion has opened

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.DB Connect
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = " Select username , password, lastname, isAdmin "
                        + " From Registration "
                        + " Where lastname like ? and status = 'true'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //get field/colum or a row
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //create DTO
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastname, role, true);

                    //add dto to list
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end accountList is not initialized
                    //account List is available

                    this.accountList.add(dto);

                }//end more than one record are returned
            }//end connecttion has opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public RegistrationDTO searchRegistration(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.DB Connect
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = " Select password, lastname, isAdmin "
                        + " From Registration "
                        + " Where username = ? and status = 'true'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //get field/colum or a row
                    String lastname = rs.getString("lastname");
                    String password = rs.getString("password");
                    boolean role = rs.getBoolean("isAdmin");
                    //create DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role, true);
                    return dto;

                }//end more than one record are returned
            }//end connecttion has opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.DB Connect
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "UPDATE Registration SET status = 'false'"
                        + " WHERE username = ? AND isAdmin = 'False'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
                    result = true;
                }
                System.out.println(result);
            }//end connecttion has opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            //1. Connect db 
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String url = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(url);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. execute Query 
                int effectRow = stm.executeUpdate();
                //5. process resultSet
                if (effectRow > 0) {
                    return true;
                } // end more then one record are returned 
            } // end connection has opened

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean createAccount(String username, String password, String fullName, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.DB Connect
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String
                String sql = "INSERT INTO "
                        + "Registration(username, password, lastname, isAdmin, status) "
                        + "VALUES(?, ?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareCall(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setBoolean(4, role);
                stm.setBoolean(5, true);
                //4. Execute Query
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
                    return true;
                }
            }//end connecttion has opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean findUsername(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect db 
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String url = "SELECT username "
                        + "FROM Registration "
                        + "WHERE username = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(url);
                stm.setString(1, username);

                rs = stm.executeQuery();
                //5. process resultSet
                if (rs.next()) {
                    return true;
                } // end more then one record are returned 
            } // end connection has opened

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean findPassword(String password, String username) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect db 
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String url = "SELECT password "
                        + "FROM Registration "
                        + "WHERE username = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(url);
                stm.setString(1, username);
                rs = stm.executeQuery();
                //5. process resultSet
                if (rs.next()) {
                    String passwordDB = rs.getString("password");
                    if (passwordDB.equals(password)) {
                        return true;
                    }
                } // end more then one record are returned 
            } // end connection has opened

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
