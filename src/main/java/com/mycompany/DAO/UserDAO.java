package com.mycompany.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.mycompany.model.pojo.Post;
import com.mycompany.model.pojo.User;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserDAO {
	
	
	
    
	
    
	public boolean save(User user) {
		System.out.print("Reached save method in userdao ");
		Session session = DAO.getSessionFactory().openSession();
		try {
			
            session.beginTransaction();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cqQuery = cb.createQuery(User.class);
            Root<User> root = cqQuery.from(User.class);
            String uname = user.getUname();
            
            Predicate usnpr = cb.equal(root.get("uname"), uname);

            cqQuery.where(usnpr);

            System.out.println("just before creating an instance of user for retriving ");
            List<User> existingUser = session.createQuery(cqQuery).getResultList();
            
            if (existingUser.isEmpty()) {
                session.persist(user);
                session.getTransaction().commit();
                return true;
            } else {
            	return false;
            }
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; // or handle the exception as needed
        } finally {
            session.close();
        }
	}
	
	
	
	public boolean updateUser(User user) {
		Session session = DAO.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        // Use session's update method to update the post object
	        User updatedUser = session.merge(user);
	        
	        session.getTransaction().commit();
	        return true; // Return the updated post object
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e; // Handle the exception as needed
	    } finally {
	        session.close();
	    }
	}
	
	
	
    
	public User retrieveUser(String uname) {
    	
    	System.out.println("Reached retrieveUser method in userdao ");
    	Session session = DAO.getSessionFactory().openSession();
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cqQuery = cb.createQuery(User.class);
            Root<User> root = cqQuery.from(User.class);

            Predicate usnpr = cb.equal(root.get("uname"), uname);

            cqQuery.where(usnpr);

            System.out.println("just before creating an instance of user for retriving ");
            User user = session.createQuery(cqQuery).getSingleResult();
            session.getTransaction().commit();
            return user;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
    }
    
    
	public void deleteUser(User user) {
		Session session = DAO.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        // Use session's update method to update the post object
	        session.remove(user);
	        
	        session.getTransaction().commit();
	         
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e; // Handle the exception as needed
	    } finally {
	        session.close();
	    }
	}

    
    
    
    public void closeSession() {
    	Session session = DAO.getSessionFactory().openSession();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    
    public List<User> getAllUsers(){
		System.out.println("Reached getAllUsers method in userdao ");
    	Session session = DAO.getSessionFactory().openSession();
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cqQuery = cb.createQuery(User.class);
            Root<User> root = cqQuery.from(User.class);
            
            
            cqQuery.select(root);

            System.out.println("just before creating an instance of List of Users for retriving ");
            List<User> users = session.createQuery(cqQuery).getResultList();
            session.getTransaction().commit();
            return users;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
	
	}

}
