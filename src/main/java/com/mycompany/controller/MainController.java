package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.DAO.PostDao;
import com.mycompany.DAO.UserDAO;
import com.mycompany.model.pojo.LoginUser;
import com.mycompany.model.pojo.Post;
import com.mycompany.model.pojo.User;
import com.mycompany.validator.UserValidator;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	

    @Autowired
    UserValidator validator;
    
    @Autowired
    PostDao postdao;
    
    @Autowired
    UserDAO userdao;

    @GetMapping("/user.htm")
    public ModelAndView loginPage(LoginUser loginuser) {
        ModelAndView mv = new ModelAndView("redirect");
        mv.addObject("loginuser", loginuser);
        return mv;

    }

    @PostMapping("/register.htm")
    public String register(@ModelAttribute("user") User user, BindingResult results, SessionStatus status, Model model) {
        try {
            validator.validate(user, results);
            if (results.hasErrors()) {
                return "signUp";
            }
            status.setComplete();
            model.addAttribute("user", user);
            boolean created = userdao.save(user);
            if(created) {
            	return "success";
            }
            return "exists";
        } catch (Exception e) {
        	e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/signUp.htm")
    public String signUp(Model model, User user) {
        return "signUp";
    }

    @PostMapping("/login.htm")
    public ModelAndView loginUser(@ModelAttribute("loginuser") LoginUser loginuser, BindingResult results, SessionStatus status, HttpSession session) {

    	System.out.println("Reached loginUser mapping");
        String usn = loginuser.getUname();
        String LgPswd = loginuser.getPswd();
        System.out.println(usn + " Tried to login");

        User user = userdao.retrieveUser(usn);
        if(null!=user){
        	if(usn.equals("admin") && LgPswd.equals(user.getPswd())) {
        		session.setAttribute("loggedInUser", user);
        		ModelAndView mv = new ModelAndView("admin-redirect");
        		return mv;
        	} else {
            String AcPswd = user.getPswd();
            if ( LgPswd.equals(AcPswd)) {
            	List<Post> posts = postdao.getAllPosts();
            	//This part is only for debugging
//            	for (int i = 0; i < posts.size(); i++) {
//            	    // Print the post number (index + 1) and its details
//            		Post post = posts.get(i);
//            	    System.out.println("Post " + (i + 1));
//            	    System.out.println("ID: " + post.getId());
//            	    System.out.println("Image URL: " + post.getImageUrl());
//            	    System.out.println("Caption: " + post.getCaption());
//            	    System.out.println("Timestamp: " + post.getTimestamp());
//            	    //System.out.println("User: " + post.getUser());
//            	}
            	
            ModelAndView mv = new ModelAndView("redirect");
            
            session.setAttribute("loggedInUser", user);
            status.setComplete();
            return mv;
        }
        }
        }

        
        
            
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("loginuser", loginuser);
            status.setComplete();
            return mv;
        
    }
    
    
    @GetMapping("/login.htm")
    public ModelAndView loginFilter(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        		// User is already logged in, redirect to user-feed page
        		List<Post> posts = postdao.getAllPosts();
//        		int rsltNum = posts.size();
//        		int numberOfPages = (int) Math.ceil((double)rsltNum / 5.0);
//        		System.out.println("The number of pages: " + numberOfPages +  " " + rsltNum);
        		ModelAndView mv = new ModelAndView("user-feed");
                mv.addObject("posts", posts);
//              mv.addObject("numberOfPages", numberOfPages);
        		return mv;
        	}
        	
        	// User is not logged in, proceed to the login page
        	return new ModelAndView("index");
    }
    
    @GetMapping("/adminLogin.htm")
    public ModelAndView adminLoginFilter(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        		
        		if(loggedInUser.getUname().equals("admin")) {
        		// Only admin should be redirected to admin.jsp
        		List<Post> posts = postdao.getAllPosts();
        		ModelAndView mv = new ModelAndView("admin");
                mv.addObject("posts", posts);
        		return mv;
        		}
        		else {
        			session.invalidate();
        			ModelAndView mv = new ModelAndView("index");
            		return mv;
        		}
        		
        	}
        	
        	// User is not logged in, proceed to the login page
        	return new ModelAndView("index");
    }
    
    @GetMapping("/logout.htm")
    public ModelAndView logout(HttpSession session) {
    	session.invalidate();
    	return new ModelAndView("redirect");
    }
    
    @GetMapping("/editUser.htm")
    public ModelAndView showEditUserForm(@ModelAttribute("user")User user, HttpSession session, @RequestParam("uname")String uname) {
    	
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    		if(loggedInUser.getUname().equals("admin")) {
        		// Only admin should be redirected to admin-profile.jsp
        		User dbUser = userdao.retrieveUser(uname);
        		ModelAndView mv = new ModelAndView("admin-user-edit");
                mv.addObject("user", dbUser);
                mv.addObject("uname", uname);
        		return mv;
        		} else {
    		ModelAndView mv = new ModelAndView("user-edit");
            mv.addObject("user", loggedInUser);
    		return mv;
        		}
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
    	
    }
    
    
    @GetMapping("/manageUser.htm")
    public ModelAndView showAdminEditUserForm(@ModelAttribute("user")User user, HttpSession session) {
    	
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    		if(loggedInUser.getUname().equals("admin")) {
        		// Only admin should be redirected to admin-profile.jsp
        		List<User> users = userdao.getAllUsers();
        		ModelAndView mv = new ModelAndView("admin-profile");
                mv.addObject("users", users);
        		return mv;
        		}
        		else {
        			session.invalidate();
        			ModelAndView mv = new ModelAndView("index");
            		return mv;
        		}
    	}
    	
    	// User is not logged in, proceed to the login page
    	return new ModelAndView("index");
    	
    }
    
    @PostMapping("/updateUser.htm")
    public String updateUser(@ModelAttribute("user")User user, HttpSession session, BindingResult results, SessionStatus status, Model model) {
    	
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    		
    		try {
                validator.validate(user, results);
                if (results.hasErrors()) {
                    return "user-edit";
                }
                User existUser = userdao.retrieveUser(user.getUname());
                if (existUser != null && existUser.getUname().equalsIgnoreCase(user.getUname())) {
                    return "exists";
                }
                User dbUser = loggedInUser;
                dbUser.setFname(user.getFname());
                dbUser.setLname(user.getLname());
                dbUser.setUname(user.getUname());
                dbUser.setEmail(user.getEmail());
                dbUser.setPswd(user.getPswd());
                model.addAttribute("user", user);
                boolean created = userdao.updateUser(dbUser);
                status.setComplete();
                if(created) {
                	return "success";
                }
                return "exists";
            } catch (Exception e) {
            	e.printStackTrace();
                return "error";
            }
    		
    		
    	}
    	
    	// User is not logged in, proceed to the login page
    	return "index";
    	
    }
    
    
    @PostMapping("/updateUserAdmin.htm")
    public String updateUserByAdmin(@ModelAttribute("user")User user, HttpSession session, BindingResult results, SessionStatus status, Model model, @RequestParam("usn")String usn) {
    	
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
    		if(loggedInUser.getUname().equals("admin")) {
    		
    		try {
                validator.validate(user, results);
                if (results.hasErrors()) {
                    return "admin-user-edit";
                }
                User existUser = userdao.retrieveUser(user.getUname());
                if (existUser != null && existUser.getUname().equalsIgnoreCase(user.getUname())) {
                    return "admin-exists";
                }
                User dbUser = userdao.retrieveUser(usn);
                dbUser.setFname(user.getFname());
                dbUser.setLname(user.getLname());
                dbUser.setUname(user.getUname());
                dbUser.setEmail(user.getEmail());
                dbUser.setPswd(user.getPswd());
                model.addAttribute("user", user);
                boolean created = userdao.updateUser(dbUser);
                status.setComplete();
                if(created) {
                	return "admin-success";
                }
                return "exists";
            } catch (Exception e) {
            	e.printStackTrace();
                return "error";
            }
    		
    		}
    	}
    	
    	// If admin is not logged in, proceed to the login page and logout current user
    	session.invalidate();
    	return "index";
    	
    }
    
    
    @GetMapping("/deleteUser.htm")
    public ModelAndView deleteUser(@ModelAttribute("user") User user, HttpSession session, @RequestParam(value = "usn", required = false, defaultValue = "usn") String usn) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        		if(loggedInUser.getUname().equals("admin")) {
        			User dbUser = userdao.retrieveUser(usn);
        			userdao.deleteUser(dbUser);
            		return new ModelAndView("admin-success");
        		}
        		else {
        		userdao.deleteUser(loggedInUser);
        		session.invalidate();
        		return new ModelAndView("index");
        		}

        	}
        	
        	// User is not logged in, proceed to the login page
        	return new ModelAndView("admin-redirect");
    }


}