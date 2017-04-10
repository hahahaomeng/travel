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
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="product"
    ,catalog="ordermanager"
)

public class Product  implements java.io.Serializable {


    // Fields    

     private Integer productid;
     private String productname;
     private String prodescribe;
     private Double price;
     private String imageurl;
     private String prostate;
     private String proplace;
     private String goplace;
     private String prodetail;
     private String hoteldetail;
     private Double discount;
     private Set<Order> orders = new HashSet<Order>(0);


    // Constructors

    /** default constructor */
    public Product() {
    }

	/** minimal constructor */
    public Product(String productname, String prodescribe, Double price, String imageurl, String prostate, String proplace, String goplace, String prodetail, String hoteldetail, Double discount) {
        this.productname = productname;
        this.prodescribe = prodescribe;
        this.price = price;
        this.imageurl = imageurl;
        this.prostate = prostate;
        this.proplace = proplace;
        this.goplace = goplace;
        this.prodetail = prodetail;
        this.hoteldetail = hoteldetail;
        this.discount = discount;
    }
    
    /** full constructor */
    public Product(String productname, String prodescribe, Double price, String imageurl, String prostate, String proplace, String goplace, String prodetail, String hoteldetail, Double discount, Set<Order> orders) {
        this.productname = productname;
        this.prodescribe = prodescribe;
        this.price = price;
        this.imageurl = imageurl;
        this.prostate = prostate;
        this.proplace = proplace;
        this.goplace = goplace;
        this.prodetail = prodetail;
        this.hoteldetail = hoteldetail;
        this.discount = discount;
        this.orders = orders;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="productid", unique=true, nullable=false)

    public Integer getProductid() {
        return this.productid;
    }
    
    public void setProductid(Integer productid) {
        this.productid = productid;
    }
    
    @Column(name="productname", nullable=false, length=45)

    public String getProductname() {
        return this.productname;
    }
    
    public void setProductname(String productname) {
        this.productname = productname;
    }
    
    @Column(name="prodescribe", nullable=false, length=200)

    public String getProdescribe() {
        return this.prodescribe;
    }
    
    public void setProdescribe(String prodescribe) {
        this.prodescribe = prodescribe;
    }
    
    @Column(name="price", nullable=false, precision=22, scale=0)

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Column(name="imageurl", nullable=false, length=45)

    public String getImageurl() {
        return this.imageurl;
    }
    
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    
    @Column(name="prostate", nullable=false, length=2)

    public String getProstate() {
        return this.prostate;
    }
    
    public void setProstate(String prostate) {
        this.prostate = prostate;
    }
    
    @Column(name="proplace", nullable=false, length=45)

    public String getProplace() {
        return this.proplace;
    }
    
    public void setProplace(String proplace) {
        this.proplace = proplace;
    }
    
    @Column(name="goplace", nullable=false, length=45)

    public String getGoplace() {
        return this.goplace;
    }
    
    public void setGoplace(String goplace) {
        this.goplace = goplace;
    }
    
    @Column(name="prodetail", nullable=false, length=1024)

    public String getProdetail() {
        return this.prodetail;
    }
    
    public void setProdetail(String prodetail) {
        this.prodetail = prodetail;
    }
    
    @Column(name="hoteldetail", nullable=false, length=45)

    public String getHoteldetail() {
        return this.hoteldetail;
    }
    
    public void setHoteldetail(String hoteldetail) {
        this.hoteldetail = hoteldetail;
    }
    
    @Column(name="discount", nullable=false, precision=22, scale=0)

    public Double getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")

    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
   








}