package com.order.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Application entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="application"
    ,catalog="ordermanager"
)

public class Application  implements java.io.Serializable {


    // Fields    

     private Integer appid;
     private Order order;
     private String appstate;
     private String apptype;
     private Date appdate;
     private String appnotice;


    // Constructors

    /** default constructor */
    public Application() {
    }

	/** minimal constructor */
    public Application(Integer appid, Order order, String appstate, String apptype, Date appdate) {
        this.appid = appid;
        this.order = order;
        this.appstate = appstate;
        this.apptype = apptype;
        this.appdate = appdate;
    }
    
    /** full constructor */
    public Application(Integer appid, Order order, String appstate, String apptype, Date appdate, String appnotice) {
        this.appid = appid;
        this.order = order;
        this.appstate = appstate;
        this.apptype = apptype;
        this.appdate = appdate;
        this.appnotice = appnotice;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="appid", unique=true, nullable=false)

    public Integer getAppid() {
        return this.appid;
    }
    
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="apporderid", nullable=false)

    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @Column(name="appstate", nullable=false, length=2)

    public String getAppstate() {
        return this.appstate;
    }
    
    public void setAppstate(String appstate) {
        this.appstate = appstate;
    }
    
    @Column(name="apptype", nullable=false, length=2)

    public String getApptype() {
        return this.apptype;
    }
    
    public void setApptype(String apptype) {
        this.apptype = apptype;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="appdate", nullable=false, length=0)

    public Date getAppdate() {
        return this.appdate;
    }
    
    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }
    
    @Column(name="appnotice", length=120)

    public String getAppnotice() {
        return this.appnotice;
    }
    
    public void setAppnotice(String appnotice) {
        this.appnotice = appnotice;
    }
   








}