/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 86185
 */
@Entity
@Table(name = "as1_cart")
@NamedQueries({
    @NamedQuery(name = "findCartRecordsByUserId", query = "SELECT c from CartEntity c where c.userId = :userId"),
    @NamedQuery(name = "findCartByItemIdAndUserId", query = "SELECT c from CartEntity c where c.userId = :userId AND c.itemId = :itemId")
})
public class CartEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="cart_id", nullable=false)
    private String cartId;
    @Column(name = "user_id", nullable=false)
    private String userId;
    @Column(name = "item_id", nullable=false)
    private String itemId;
    @Column(name = "count", nullable=false)
    private Integer count;
    @Column(name = "extend_1", length = 1023)
    private String extend1;
    @Column(name = "extend_2", length = 1023)
    private String extend2;
    @Column(name = "extend_3", length = 1023)
    private String extend3;
    
    public CartEntity() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartEntity)) {
            return false;
        }
        CartEntity other = (CartEntity) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.CartEntity[ id=" + cartId + " ]";
    }
    
}
