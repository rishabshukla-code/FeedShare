package com.mycompany.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.mycompany.model.pojo.Post;
import com.mycompany.model.pojo.User;

public class DAO {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySetting(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
						.applySetting(Environment.URL, "jdbc:mysql://localhost:3306/wevdev")
						.applySetting(Environment.USER, "root")
						.applySetting(Environment.PASS, "Rishab1234")
						.applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect")
						.applySetting(Environment.SHOW_SQL, "true")
						.applySetting(Environment.HBM2DDL_AUTO, "update")
						//make sure to modify the ddl pattern from create to update to avoid the dropping of table
						.build();
				
				MetadataSources metadataSources = new MetadataSources(serviceRegistry);
				metadataSources.addAnnotatedClass(User.class);
				metadataSources.addAnnotatedClass(Post.class);
				
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				
				sessionFactory = metadata.getSessionFactoryBuilder().build();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
		
	}

}
