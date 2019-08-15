/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 * User table corresponding entity
 */
@Entity
@Table(name = "as1_user")
@NamedQueries({
    @NamedQuery(name = "findByEmail", query = "select u from UserEntity u where u.email=:email"),
    @NamedQuery(name = "findByUserName", query = "select u from UserEntity u where u.userName=:userName")})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_id")
    private String id;    
    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;    
    @Column(name = "user_name", length = 255, unique = true)
    private String userName;    
    @Column(name = "password", length = 255, nullable = false)
    private String password;    
    @Column(name = "membership_level", nullable = false)
    private Integer membershipLevel;
    @Column(name = "last_name", length = 255)
    private String lastName;
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "phone_number", length = 255)
    private String phoneNumber;
    @Column(name = "gender", length = 255)
    private String gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "profession", length = 255)
    private String profession;
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
    
    /**
     * default constructor
     */
    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(int membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "glasscat.as1.entity.UserEntity[ id=" + id + " ]";
    }
    
}
