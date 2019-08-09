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
import javax.persistence.Table;
import java.sql.Date;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Entity
@Table(name = "as1_transaction")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "transaction_datetime")
    private Date transactionDatetimeDate;
    @Column(name = "extend_1", length = 1023)
    private String extend1;
    @Column(name = "extend_2", length = 1023)
    private String extend2;
    @Column(name = "extend_3", length = 1023)
    private String extend3;

    public TransactionEntity() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTransactionDatetimeDate() {
        return transactionDatetimeDate;
    }

    public void setTransactionDatetimeDate(Date transactionDatetimeDate) {
        this.transactionDatetimeDate = transactionDatetimeDate;
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
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.Transaction[ id=" + transactionId + " ]";
    }
    
}
