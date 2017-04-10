package com.order.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="ordermanager"
)

public class User  implements java.io.Serializable {


    // Fields    

     private Integer userid;
     private String username;
     private String type;
     private String password;
     private String phone;
     private String email;
     private String state;
     private String checkcode;
     private String notice;
     private Set<Order> orders = new HashSet<Order>(0);


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String username, String type, String password, String phone, String email, String state) {
        this.username = username;
        this.type = type;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.state = state;
    }
    
    /** full constructor */
    public User(String username, String type, String password, String phone, String email, String state, String checkcode, String notice, Set<Order> orders) {
        this.username = username;
        this.type = type;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.state = state;
        this.checkcode = checkcode;
        this.notice = notice;
        this.orders = orders;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="userid", unique=true, nullable=false)

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    @Column(name="username", nullable=false, length=8)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="type", nullable=false, length=2)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="password", nullable=false, length=11)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="phone", nullable=false, length=11)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="email", nullable=false, length=30)

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="state", nullable=false, length=2)

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="checkcode", length=6)

    public String getCheckcode() {
        return this.checkcode;
    }
    
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
    @Column(name="notice", length=45)

    public String getNotice() {
        return this.notice;
    }
    
    public void setNotice(String notice) {
        this.notice = notice;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")

    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
   








}