/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.product;

import antdt.registration.RegistrationDTO;
import antdt.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDAO implements Serializable{

    private List<ProductDTO> productList = new ArrayList<>();

    public List<ProductDTO> getList() {
        return productList;
    }

    public void loadProductData() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect db 
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String sql = "SELECT * "
                        + "FROM Product ";
                //3. Create Statement object 
                stm = conn.prepareStatement(sql);
                //4. execute Query 
                rs = stm.executeQuery();
                //5. process resultSet
                while (rs.next()) {
                    //get field/column of a row
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    // create Object DTO 
                    ProductDTO dto = new ProductDTO(id, name, price, quantity, description);
                    // add dto to list 
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    } // end accountList is not initialize 
                    // account List is available
                    this.productList.add(dto);
                } // end more then one record are returned 
            } // end connection has opened

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ProductDTO loadProductDataByid(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect db 
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                //2. Create SQL Statement String 
                String sql = "SELECT * "
                        + "FROM Product "
                        + "WHERE id = ?";
                //3. Create Statement object 
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute Query 
                rs = stm.executeQuery();

                //5. process resultSet
                if (rs.next()) {
                    //get field/column of a row
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    // create Object DTO 
                    ProductDTO dto = new ProductDTO(id, name, price, quantity, description);
                    return dto;
                } // end more then one record are returned 
            } // end connection has opened

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
        return null;
    }

    public boolean insertProductToOrderDetail(String id, String name, int quantity, double price, String description) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO "
                        + "OrderDetails(id, name, quantity, price, description) "
                        + "VALUES(?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, name);
                stm.setInt(3, quantity);
                stm.setDouble(4, price);
                stm.setString(5, description);
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
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
    
    public boolean insertProductToOrderDetailByProduct(ProductDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO "
                        + "OrderDetails(id, name, quantity, price, total) "
                        + "VALUES(?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getName());
                stm.setInt(3, dto.getQuantity());
                stm.setDouble(4, dto.getPrice());
                stm.setDouble(5, dto.getPrice()*dto.getQuantity());
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
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
    
    public boolean removeAllProductFromOrderDetails() throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                String sql = "DELETE FROM OrderDetails";
                stm = conn.prepareStatement(sql);
                if (stm.executeUpdate() != 0) {
                    return true;
                }
            }
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
}
