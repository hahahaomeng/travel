package com.order.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="order"
    ,catalog="ordermanager"
)

public class Order  implements java.io.Serializable {


    // Fields    

     private Integer orderid;
     private Product product;
     private User user;
     private Date paydate;
     private Double price;
     private String notice;
     private String state;
     private Date godate;
     private Integer gonumber;
     private Set<Comment> comments = new HashSet<Comment>(0);
     private Set<Application> applications = new HashSet<Application>(0);


    // Constructors

    /** default constructor */
    public Order() {
    }

	/** minimal constructor */
    public Order(Product product, User user, Date paydate, Double price, String state, Date godate, Integer gonumber) {
        this.product = product;
        this.user = user;
        this.paydate = paydate;
        this.price = price;
        this.state = state;
        this.godate = godate;
        this.gonumber = gonumber;
    }
    
    /** full constructor */
    public Order(Product product, User user, Date paydate, Double price, String notice, String state, Date godate, Integer gonumber, Set<Comment> comments, Set<Application> applications) {
        this.product = product;
        this.user = user;
        this.paydate = paydate;
        this.price = price;
        this.notice = notice;
        this.state = state;
        this.godate = godate;
        this.gonumber = gonumber;
        this.comments = comments;
        this.applications = applications;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="orderid", unique=true, nullable=false)

    public Integer getOrderid() {
        return this.orderid;
    }
    
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="productid", nullable=false)

    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="userid", nullable=false)

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="paydate", nullable=false, length=0)

    public Date getPaydate() {
        return this.paydate;
    }
    
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }
    
    @Column(name="price", nullable=false, precision=22, scale=0)

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Column(name="notice", length=45)

    public String getNotice() {
        return this.notice;
    }
    
    public void setNotice(String notice) {
        this.notice = notice;
    }
    
    @Column(name="state", nullable=false, length=2)

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="godate", nullable=false, length=0)

    public Date getGodate() {
        return this.godate;
    }
    
    public void setGodate(Date godate) {
        this.godate = godate;
    }
    
    @Column(name="gonumber", nullable=false)

    public Integer getGonumber() {
        return this.gonumber;
    }
    
    public void setGonumber(Integer gonumber) {
        this.gonumber = gonumber;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="order")
//
//    public Set<Comment> getComments() {
//        return this.comments;
//    }
//    
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="order")
//
//    public Set<Application> getApplications() {
//        return this.applications;
//    }
//    
//    public void setApplications(Set<Application> applications) {
//        this.applications = applications;
//    }
//   








}