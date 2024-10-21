package com.mycompany.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.model.pojo.Post;
import com.mycompany.model.pojo.User;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class PostDao {
	
	public static Post saveFile(String fileName, MultipartFile multipartFile, String caption, User user) throws IOException {
		
		Path desktopDir = Paths.get(System.getProperty("user.home"), "Desktop");
        
        // Define the path to the "File-Upload" folder on the desktop
        Path uploadDir = desktopDir.resolve("FS-Uploads");
        
        
        
        String filecode = RandomStringUtils.randomAlphanumeric(8);
        
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDir.resolve(filecode + "-" + fileName);
            System.out.println("File saved to: " + filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Refresh the directory
            //refreshDirectory(uploadDir); 
            
            Post post = new Post();
            post.setImageUrl("/FS-Uploads/" + filecode + "-" + fileName);
            post.setCaption(caption);
            post.setTimestamp(new Date());
            post.setUser(user);
            Session session = DAO.getSessionFactory().openSession();
    		try {
    			
                session.beginTransaction();
                session.persist(post);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e; // or handle the exception as needed
            } finally {
                session.close();
            }
            
            
            
            return post;
            
        }catch(IOException ioe){
            throw new IOException("Error saving uploaded file: "+ fileName,ioe);
        }
        
    }
	
//	private static void refreshDirectory(Path directoryPath) throws IOException {
//        try (Stream<Path> paths = Files.list(directoryPath)) {
//            // Triggering the listing of directory contents refreshes the directory
//            paths.findFirst();
//        }
//    }
	
	
	public List<Post> getAllPosts(){
		System.out.println("Reached getAllPosts method in postdao ");
    	Session session = DAO.getSessionFactory().openSession();
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Post> cqQuery = cb.createQuery(Post.class);
            Root<Post> root = cqQuery.from(Post.class);
            
            //Order by timestamp column in descending order
            cqQuery.orderBy(cb.desc(root.get("timestamp")));

			//String caption = "woof";
			//Predicate usnpr = cb.equal(root.get("caption"), caption);
			//
			//cqQuery.where(usnpr);

            
            
            cqQuery.select(root);

            System.out.println("just before creating an instance of Post for retriving ");
            List<Post> posts = session.createQuery(cqQuery).getResultList();
            session.getTransaction().commit();
            return posts;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
	
	}
	
	
	public List<Post> getLimitedUserPosts(User loggedInUser, int pgNum){
		System.out.println("Reached getUserPosts method in postdao ");
    	Session session = DAO.getSessionFactory().openSession();
    	
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Post> cqQuery = cb.createQuery(Post.class);
            Root<Post> root = cqQuery.from(Post.class);
            
// 			Order by timestamp column in descending order
            cqQuery.orderBy(cb.desc(root.get("timestamp")));

//          String caption = "woof";
            Predicate usnpr = cb.equal(root.get("user"), loggedInUser);
//
            cqQuery.where(usnpr);

            int pageSize = 5;
            int offset = (pgNum - 1) * pageSize;
            List<Post> TotalPosts = session.createQuery(cqQuery).getResultList();
            
//           cqQuery.select(root);

            System.out.println("just before creating an instance of Post for retriving user posts ");
            List<Post> posts = session.createQuery(cqQuery).setFirstResult(offset).setMaxResults(pageSize).getResultList();
            session.getTransaction().commit();
            return posts;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
	
	}
	
	public List<Post> getUserPosts(User loggedInUser){
		System.out.println("Reached getUserPosts method in postdao ");
    	Session session = DAO.getSessionFactory().openSession();
    	
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Post> cqQuery = cb.createQuery(Post.class);
            Root<Post> root = cqQuery.from(Post.class);
            
// 			Order by timestamp column in descending order
            cqQuery.orderBy(cb.desc(root.get("timestamp")));


            Predicate usnpr = cb.equal(root.get("user"), loggedInUser);

            cqQuery.where(usnpr);


            System.out.println("just before creating an instance of Post for retriving user posts ");
            List<Post> TotalPosts = session.createQuery(cqQuery).getResultList();
            session.getTransaction().commit();
            return TotalPosts;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
	
	}
	
	
	public Post getPostById(Long id){
		System.out.println("Reached getUserPosts method in postdao ");
    	Session session = DAO.getSessionFactory().openSession();
    	
        try {
        	session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Post> cqQuery = cb.createQuery(Post.class);
            Root<Post> root = cqQuery.from(Post.class);
            
// 			Order by timestamp column in descending order
//            cqQuery.orderBy(cb.desc(root.get("timestamp")));

//          String caption = "woof";
            Predicate idpr = cb.equal(root.get("id"), id);
//
            cqQuery.where(idpr);

            
            
//           cqQuery.select(root);

            System.out.println("just before creating an instance of Post for retriving user posts ");
            Post post = session.createQuery(cqQuery).getSingleResult();
            session.getTransaction().commit();
            return post;

        } catch (NoResultException e) {

        	session.getTransaction().rollback();
            return null;

        } finally {
            session.close();
        }
	
	}
	
	
	
	public Post updatePost(Post post) {
		Session session = DAO.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        // Use session's update method to update the post object
	        Post updatedPost = session.merge(post);
	        
	        session.getTransaction().commit();
	        return updatedPost; // Return the updated post object
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e; // Handle the exception as needed
	    } finally {
	        session.close();
	    }
	}
	
	
	
	public void deletePost(Post post) {
		Session session = DAO.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        
	        // Use session's update method to update the post object
	        session.remove(post);
	        
	        session.getTransaction().commit();
	         
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        throw e; // Handle the exception as needed
	    } finally {
	        session.close();
	    }
	}
	
}
