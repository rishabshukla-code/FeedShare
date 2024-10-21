package com.mycompany.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.DAO.PostDao;
import com.mycompany.DAO.UserDAO;
import com.mycompany.model.pojo.FileUploadResponse;
import com.mycompany.model.pojo.LoginUser;
import com.mycompany.model.pojo.Post;
import com.mycompany.model.pojo.User;
import com.mycompany.util.FileUploadUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class FileUploadController {
	
	@Autowired
	PostDao postdao;
	
	@Autowired
	UserDAO userdao;
	
	@PostMapping("/uploadFile.htm")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile,
    						@RequestParam("caption") String caption, 
    						HttpSession session,
    						UserDAO userdao) throws IOException{
        
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null & multipartFile !=null) {
    		// User is already logged in, redirect to user-feed page
    		try {
            	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                long size = multipartFile.getSize();
                
                Post post = PostDao.saveFile(fileName, multipartFile, caption, loggedInUser);
                

                
                return new ModelAndView("redirect");
                
    		} catch (Exception e) {
    			e.printStackTrace();
    			return new ModelAndView("post-error");
    		}
    		
    		
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
		
    }
	
	
    @GetMapping("/profile.htm")
    public ModelAndView showUserProfile(@RequestParam(value = "pageNumber", defaultValue = "1") int pgNum,HttpSession session) {
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	
    	if (loggedInUser != null) {
    		// User is already logged in, redirect to user-feed page

    		System.out.println("The posts are getting retrieved for profile page");
    		List<Post> TotalPosts = postdao.getUserPosts(loggedInUser);
    		List<Post> posts = postdao.getLimitedUserPosts(loggedInUser, pgNum);
    		int rsltNum = TotalPosts.size();
    		int numberOfPages = (int) Math.ceil((double)rsltNum / 5.0);
    		System.out.println("The number of pages: " + numberOfPages +  " " + rsltNum);
    		ModelAndView mv = new ModelAndView("profile");
            mv.addObject("posts", posts);
            mv.addObject("numberOfPages", numberOfPages);
    		return mv;
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
    }
    
    @GetMapping("/AjaxProfile.htm")
    public ModelAndView ajaxProfile(@RequestParam(value = "pageNumber", defaultValue = "1") int pgNum,HttpSession session) {
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	
    	if (loggedInUser != null) {
    		// User is already logged in, redirect to ajax jsp page

    		System.out.println("The posts are getting retrieved for profile page");
    		List<Post> TotalPosts = postdao.getUserPosts(loggedInUser);
    		List<Post> posts = postdao.getLimitedUserPosts(loggedInUser, pgNum);
    		int rsltNum = TotalPosts.size();
    		int numberOfPages = (int) Math.ceil((double)rsltNum / 5.0);
    		System.out.println("The number of pages: " + numberOfPages +  " " + rsltNum);
    		ModelAndView mv = new ModelAndView("profile-ajax");
            mv.addObject("posts", posts);
            mv.addObject("numberOfPages", numberOfPages);
    		return mv;
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
    }
    
    @GetMapping("/post.htm")
    public ModelAndView showPostForm(HttpSession session) {
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    		// User is already logged in, redirect to user-feed page
    		ModelAndView mv = new ModelAndView("post");
    		return mv;
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
    }
    

    
    @GetMapping("/editPost.htm")
    public ModelAndView showEditPostForm(@ModelAttribute("post") Post post, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        		// User is already logged in, redirect to edit
        		ModelAndView mv = new ModelAndView("post-edit");
        		mv.addObject(post);
        		return mv;
        	}
        	
        	// User is not logged in, proceed to the login page
        	return new ModelAndView("index");
    }
    
    @PostMapping("/updatePost.htm")
    public ModelAndView update(@ModelAttribute("post") Post post, @RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    	Post dbPost = postdao.getPostById(post.getId());
		    if(!post.getCaption().isEmpty() || multipartFile != null) {
    			if(!post.getCaption().isEmpty()) {
		    		dbPost.setCaption(post.getCaption());
		    	}
		    	if(multipartFile != null && !multipartFile.isEmpty()) {
		    		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		    		String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
		    		dbPost.setImageUrl("/FS-Uploads/" + filecode + "-" + fileName);
		    	}
		    	postdao.updatePost(dbPost);
		    	
		    }
		    if(loggedInUser.getUname().equals("admin")) {
		    	ModelAndView mv = new ModelAndView("admin-redirect");
		    	return mv;
		    } 
		    else {
		    	ModelAndView mv = new ModelAndView("redirect");
		    	return mv;
		    }
		    
    	}
    
    return new ModelAndView("index");
    }
    
    @GetMapping("/deletePost.htm")
    public ModelAndView deletePost(@ModelAttribute("post") Post post, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        		Post dbPost = postdao.getPostById(post.getId());
        		Long loggedInUserId = loggedInUser.getUserid();
        		Long dbPostUserId = dbPost.getUser().getUserid();
        		if (dbPostUserId == loggedInUserId || loggedInUser.getUname().equals("admin")) {
        		// User is already logged in, redirect to edit
        		postdao.deletePost(dbPost);
        		} else {
        			session.invalidate();
        			return new ModelAndView("post-error");
        		}
        	}
        	
        	if (loggedInUser != null) {
        	if(loggedInUser.getUname().equals("admin")) {
        		return new ModelAndView("admin-redirect");
        	}
        	}
        	
        	
        	// User is not logged in, proceed to the login page
        	return new ModelAndView("redirect");
    }
    
}
