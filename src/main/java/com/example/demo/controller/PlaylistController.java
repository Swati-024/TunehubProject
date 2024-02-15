
package com.example.demo.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class PlaylistController 
{
	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model) {
		
		//Fetching the songs using song service
		List<Songs> songslist=sserv.fetchAllsongs();
		
		//Adding the songs in the model
		model.addAttribute("songslist",songslist);
		
		
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlayList(@ModelAttribute Playlist playlist) {
		//adding playlist	
		
		pserv.addPlaylist(playlist);
		
		List<Songs> songsList = playlist.getSongs();
		
		for(Songs song : songsList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> plist= pserv.fetchPlaylists();
		//System.out.println(plist);
		model.addAttribute("plist", plist);
		return "viewPlaylists";
	}
	
	@GetMapping("/PlaylistsView")
	public String viewCustomerPlaylists(Model model) {
		List<Playlist> plist= pserv.fetchPlaylists();
		//System.out.println(plist);
		model.addAttribute("plist", plist);
		return "viewPlaylists";
	}
	
	@GetMapping("/PlaylistCreate")
	public String PlaylistCreate(Model model) {
		
		//Fetching the songs using song service
		List<Songs> songslist=sserv.fetchAllsongs();
		
		//Adding the songs in the model
		model.addAttribute("songslist",songslist);
		
		
		return "createplaylist";
	}
	@GetMapping("/favourite")
	public String addSongs(@ModelAttribute Songs song,Model model)
	{
		//Fetching the songs using song service
				List<Songs> songslist=sserv.fetchAllsongs();
				
				//Adding the songs in the model
				model.addAttribute("songslist",songslist);
		 return "favouriteList";
	
	}
	
	@PostMapping("/favouriteplaylist")
	public String favouriteplaylist(@ModelAttribute Playlist playlist) {
		//adding playlist	
		
		pserv.addPlaylist(playlist);
		
		List<Songs> songsList = playlist.getSongs();
		
		for(Songs song : songsList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	@GetMapping("/viewFavouritePlaylists")
	public String viewFavouritePlaylists(Model model) {
		List<Playlist> plist= pserv.fetchFavPlaylists();
		//System.out.println(plist);
		model.addAttribute("plist", plist);
		return "viewFavouritePlaylists";
	}
}









