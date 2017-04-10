package com.order.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.Application;

/**
 	* A data access object (DAO) providing persistence and search support for Application entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.order.entity.Application
  * @author MyEclipse Persistence Tools 
 */
@Transactional
public class ApplicationDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ApplicationDAO.class);
		//property constants
	public static final String APPSTATE = "appstate";
	public static final String APPTYPE = "apptype";
	public static final String APPNOTICE = "appnotice";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


    
    public void save(Application transientInstance) {
        log.debug("saving Application instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Application persistentInstance) {
        log.debug("deleting Application instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Application findById( java.lang.Integer id) {
        log.debug("getting Application instance with id: " + id);
        try {
            Application instance = (Application) getCurrentSession()
                    .get("com.order.entity.Application", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Application> findByExample(Application instance) {
        log.debug("finding Application instance by example");
        try {
            List<Application> results = (List<Application>) getCurrentSession()
                    .createCriteria("com.order.entity.Application")
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
      log.debug("finding Application instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Application as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Application> findByAppstate(Object appstate
	) {
		return findByProperty(APPSTATE, appstate
		);
	}
	
	public List<Application> findByApptype(Object apptype
	) {
		return findByProperty(APPTYPE, apptype
		);
	}
	
	public List<Application> findByAppnotice(Object appnotice
	) {
		return findByProperty(APPNOTICE, appnotice
		);
	}
	

	public List findAll() {
		log.debug("finding all Application instances");
		try {
			String queryString = "from Application";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Application merge(Application detachedInstance) {
        log.debug("merging Application instance");
        try {
            Application result = (Application) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Application instance) {
        log.debug("attaching dirty Application instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Application instance) {
        log.debug("attaching clean Application instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}