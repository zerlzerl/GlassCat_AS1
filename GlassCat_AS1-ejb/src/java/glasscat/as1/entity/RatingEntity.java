/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.entity;

import glasscat.as1.util.IDUtil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Entity
@Table(name = "as1_rating")
@NamedQueries({
    @NamedQuery(name = "findRatingsByItemId", query = "Select r from RatingEntity r where r.itemId = :itemId ORDER BY r.ratingDate DESC"),
    @NamedQuery(name = "findAverageMarkByItemId", query = "Select COALESCE(AVG(r.mark + 0.0), 0) from RatingEntity r where r.itemId = :itemId"),
    @NamedQuery(name = "findRatingBySubTransactionId", query = "Select r from RatingEntity r where r.subTransactionId = :subTransactionId")
})
public class RatingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rating_id")
    private String ratingId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "item_id")
    private String itemId;
    @Column(name = "subtransaction_id")
    private String subTransactionId;
    @Column(name = "mark")
    private Integer mark;
    @Column(name = "comment", length = 1023)
    private String comment;
    @Column(name = "rating_date")
    private Date ratingDate;
    @Column(name = "extend_1", length = 1023)
    private String extend1;
    @Column(name = "extend_2", length = 1023)
    private String extend2;
    @Column(name = "extend_3", length = 1023)
    private String extend3;
    @Column(name = "extend_4", length = 1023)
    private String extend4;
    @Column(name = "extend_5", length = 1023)
    private String extend5;

    public RatingEntity() {
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getSubTransactionId() {
        return subTransactionId;
    }

    public void setSubTransactionId(String subTransactionId) {
        this.subTransactionId = subTransactionId;
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

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4;
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingId != null ? ratingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingEntity)) {
            return false;
        }
        RatingEntity other = (RatingEntity) object;
        if ((this.ratingId == null && other.ratingId != null) || (this.ratingId != null && !this.ratingId.equals(other.ratingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.RatingEntity[ id=" + ratingId + " ]";
    }
    
}
