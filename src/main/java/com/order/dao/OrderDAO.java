package com.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.Order;

/**
 	* A data access object (DAO) providing persistence and search support for Order entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.order.entity.Order
  * @author MyEclipse Persistence Tools 
 */
@Transactional
public class OrderDAO  {
	     private static final Logger log = LoggerFactory.getLogger(OrderDAO.class);
		//property constants
	public static final String PRICE = "price";
	public static final String NOTICE = "notice";
	public static final String STATE = "state";
	public static final String GONUMBER = "gonumber";



	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

    public void save(Order transientInstance) {
        log.debug("saving Order instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Order persistentInstance) {
        log.debug("deleting Order instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Order findById( java.lang.Integer id) {
        log.debug("getting Order instance with id: " + id);
        try {
            Order instance = (Order) getCurrentSession()
                    .get("com.order.entity.Order", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Order> findByExample(Order instance) {
        log.debug("finding Order instance by example");
        try {
            List<Order> results = (List<Order>) getCurrentSession()
                    .createCriteria("com.order.entity.Order")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Order instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Order as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Order> findByPrice(Object price
	) {
		return findByProperty(PRICE, price
		);
	}
	
	public List<Order> findByNotice(Object notice
	) {
		return findByProperty(NOTICE, notice
		);
	}
	
	public List<Order> findByState(Object state
	) {
		return findByProperty(STATE, state
		);
	}
	
	public List<Order> findByGonumber(Object gonumber
	) {
		return findByProperty(GONUMBER, gonumber
		);
	}
	

	public List findAll() {
		log.debug("finding all Order instances");
		try {
			String queryString = "from Order";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Order merge(Order detachedInstance) {
        log.debug("merging Order instance");
        try {
            Order result = (Order) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Order instance) {
        log.debug("attaching dirty Order instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Order instance) {
        log.debug("attaching clean Order instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}