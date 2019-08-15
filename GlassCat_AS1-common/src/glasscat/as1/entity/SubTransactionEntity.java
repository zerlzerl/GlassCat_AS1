/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Entity
@Table(name = "as1_subtransaction")
@NamedQueries({
    @NamedQuery(name = "findSubTransactionRecordsByTransactionId", query = "SELECT s FROM SubTransactionEntity s WHERE s.transactionId=:transactionId")
})
public class SubTransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "subtranscation_id")
    private String subtransactionId;
    @Column(name = "transcation_id")
    private String transactionId;
    @Column(name = "item_id")
    private String itemId;
    @Column(name = "count")
    private Integer count;
    @Column(name = "extend_1", length = 1023)
    private String extend1;
    @Column(name = "extend_2", length = 1023)
    private String extend2;
    @Column(name = "extend_3", length = 1023)
    private String extend3;

    public SubTransactionEntity() {
    }

    public String getSubtransactionId() {
        return subtransactionId;
    }

    public void setSubtransactionId(String subtransactionId) {
        this.subtransactionId = subtransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
        hash += (subtransactionId != null ? subtransactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubTransactionEntity)) {
            return false;
        }
        SubTransactionEntity other = (SubTransactionEntity) object;
        if ((this.subtransactionId == null && other.subtransactionId != null) || (this.subtransactionId != null && !this.subtransactionId.equals(other.subtransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.SubTransactionEntity[ id=" + subtransactionId + " ]";
    }
    
}
