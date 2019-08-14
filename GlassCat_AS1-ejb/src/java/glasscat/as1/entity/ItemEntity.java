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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 */
@Entity
@Table(name = "as1_item")
@NamedQueries({
    @NamedQuery(name = "findByTitleLike", query = "select i from ItemEntity i where UPPER(i.title) LIKE CONCAT('%',UPPER(:title),'%')"),
    @NamedQuery(name = "findByAttributesLike", query = "select i from ItemEntity i where CONCAT(UPPER(i.title),UPPER(i.category),UPPER(i.color),UPPER(i.style),UPPER(i.season)) LIKE CONCAT('%',UPPER(:keyword),'%')")})
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "item_id")
    private String id;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "image_url", length = 2047)
    private String imageUrl;
    @Column(name = "image_url_thumb", length = 2047)
    private String imageUrlThumb;
    @Column(name = "category", length = 1023)
    private String category;
    @Column(name = "price")
    private Double price;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "color", length = 1023)
    private String color;
    @Column(name = "style", length = 1023)
    private String style;
    @Column(name = "season", length = 1023)
    private String season;
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

    public ItemEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlThumb() {
        return imageUrlThumb;
    }

    public void setImageUrlThumb(String imageUrlThumb) {
        this.imageUrlThumb = imageUrlThumb;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemEntity)) {
            return false;
        }
        ItemEntity other = (ItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.ItemEntity[ id=" + id + " ]";
    }
    
}
