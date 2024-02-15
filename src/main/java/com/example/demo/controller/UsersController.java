package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;



@Controller
public class UsersController {
	@Autowired
	UsersService usservice;
	
	@Autowired
	 SongService sserv;
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
	{
		boolean userstatus=usservice.emailExists(user.getEmail());
		if(userstatus == false)
		{
		usservice.addUser(user);
	     return "registerSuccess";
		}
		else {
			return "registerFail";
		}
		
		
	}
	
	@PostMapping ("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session)
	{
		 
		
		
		if(usservice.validateUser(email,password) == true)
		{
			session.setAttribute("email", email);
			if(usservice.getRole(email).equals("admin"))
        {
	
			return "adminHome";
        }
		else
		{
			return "customerHome";
		}
		}
		else
		{
			return "loginFail";
		}
	}
	
	@GetMapping ("/exploreSongs")
	public String exploreSongs(HttpSession session,Model model) {
		String email = (String) session.getAttribute("email");
		Users user=usservice.getUser(email);
		
		boolean userStatus=user.isPremium();
		if(userStatus == true)
		{
			List<Songs> songslist=sserv.fetchAllsongs();
			model.addAttribute("songslist",songslist);
			return "premiumUser";
		}
		else {
			return "payment";
		}
	}
	
	@GetMapping ("/freeTrial")
	public String freeTrial(HttpSession session,Model model) {
		String email = (String) session.getAttribute("email");
		Users user=usservice.getUser(email);
		
		List<Songs> songslist = sserv.fetchAllsongs();
		model.addAttribute("songslist", songslist);
		return "displaysongs";
		
	}
	
	

}


