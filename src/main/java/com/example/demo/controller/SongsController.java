package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Songs;

import com.example.demo.services.SongService;

@Controller
public class SongsController {
	@Autowired
	 SongService sserv;
	
		@PostMapping("/addsongs")
		public String addSongs(@ModelAttribute Songs song)
		{
		 boolean status = sserv.songExists(song.getName());
		 if(status == false)
		 {
			 sserv.addSongs(song);
			 return "songsuccess";
		 }
		 else
		 {
			 return "songfail";
		 }
		}
		
		@GetMapping("/map-viewsongs")
		public String viewCustomerSongs(Model model)
		{
			boolean primeCustomerStatus=true;
			if(primeCustomerStatus==true)
			{
				List<Songs> songslist = sserv.fetchAllsongs();
				model.addAttribute("songslist", songslist);
				return "displaysongs";
			}
			else
			{
				return "makepayment";
			}
		}
		
		@GetMapping("/premiumUser")
		public String viewCustomerPremiumSongs(Model model)
		{
			boolean primeCustomerStatus=true;
			if(primeCustomerStatus==true)
			{
				return "premiumUser";
			}
			else
			{
				return "makepayment";
			}
		}
		
		@GetMapping("/viewsongs")
		public String viewSongs(Model model)
		{
			
				List<Songs> songslist = sserv.fetchAllsongs();
				model.addAttribute("songslist", songslist);
				return "displaysongs";
			
		}
		

}
