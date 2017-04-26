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
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="comment"
    ,catalog="ordermanager"
)

public class Comment  implements java.io.Serializable {


    // Fields    

     private Integer commentid;
     private Order order;
     private String content;
     private String commenturl;
     private Date commentdate;
     private String commentstate;


    // Constructors

    /** default constructor */
    public Comment() {
    }

	/** minimal constructor */
    public Comment(Integer commentid) {
        this.commentid = commentid;
    }
    
    /** full constructor */
    public Comment(Integer commentid, Order order, String content, String commenturl, Date commentdate, String commentstate) {
        this.commentid = commentid;
        this.order = order;
        this.content = content;
        this.commenturl = commenturl;
        this.commentdate = commentdate;
        this.commentstate = commentstate;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="commentid", unique=true, nullable=false)

    public Integer getCommentid() {
        return this.commentid;
    }
    
    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="orderid")

    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @Column(name="content", length=45)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="commenturl", length=120)

    public String getCommenturl() {
        return this.commenturl;
    }
    
    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="commentdate", length=0)

    public Date getCommentdate() {
        return this.commentdate;
    }
    
    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }
    
    @Column(name="commentstate", length=2)

    public String getCommentstate() {
        return this.commentstate;
    }
    
    public void setCommentstate(String commentstate) {
        this.commentstate = commentstate;
    }
   








}