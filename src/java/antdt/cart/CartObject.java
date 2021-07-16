/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cart;

import antdt.product.ProductDAO;
import antdt.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

public class CartObject implements Serializable {

    private Map<String, ProductDTO> items;

    public Map<String, ProductDTO> getItems() {
        return items;
    }

//    public void addToCart (ProductDTO dto) {
//        //1. checking id existed
//        if (dto.getId() == null) {
//            return;
//        }
//        //2. checking items existed 
//        if (this.items == null) {
//            this.items = new HashMap<>();
//        }
//        // 3. When items existed, check id existed in items
//        int quantity = 1;
//        if (this.items.containsKey(dto.getId())) {
////            quantity = this.items.get(dto.getId()) + 1;
//            //dto.setQuantity(quantity);
//            this.items.get(dto.getId()).setQuantity(quantity + 1);
//        }
//        // 4. update items
//        this.items.put(dto.getId(), dto);
//    }
    public void addToCart(String id) throws SQLException, NamingException {
        //1. checking id existed
        if (id == null) {
            return;
        }
        //2. checking items existed 
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        // 3. When items existed, check id existed in items
        int quantity = 1;
        if (this.items.containsKey(id)) {
//            quantity = this.items.get(dto.getId()) + 1;
            //dto.setQuantity(quantity);
            quantity = this.items.get(id).getQuantity();
            this.items.get(id).setQuantity(quantity + 1);
            this.items.put(id, this.items.get(id));
        } else {
            // 4. update items
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.loadProductDataByid(id);
            this.items.put(id, dto);
        }

    }

    public void removeItemFromCart(String id) {
        //1. checking items existed 
        if (this.items == null) {
            return;
        }
        //2. when items exited, check id existed in items 
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
