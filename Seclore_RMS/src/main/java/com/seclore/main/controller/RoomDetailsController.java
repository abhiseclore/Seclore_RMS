package com.seclore.main.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.RoomDetailsServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/roomdetails")
public class RoomDetailsController {
	
	@Autowired
	private RoomDetailsServiceInterface roomDetailsService;
	
	@RequestMapping(value = "/addroom", method = RequestMethod.GET)
	public ModelAndView showAddNewRoomPage(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addroom");
		RoomDetails roomDetails = new RoomDetails();
		modelAndView.addObject("roomDetails", roomDetails);
			
		return modelAndView;
	}
	
	@RequestMapping(value = "/addroom", method = RequestMethod.POST)
	public ModelAndView addNewRoom(@ModelAttribute RoomDetails roomDetails) {
				
		boolean success = roomDetailsService.addNewRoom(roomDetails);
		if(success)
			return allRooms();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateroom", method = RequestMethod.GET)
	public ModelAndView showUpdateRoomPage(@RequestParam("roomId") int roomId) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("updateroom");
		RoomDetails roomDetails = roomDetailsService.getOneRoomDetails(roomId);
		modelAndView.addObject("roomDetails", roomDetails);
			
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateroom", method = RequestMethod.POST)
	public ModelAndView updateRoom(@ModelAttribute RoomDetails roomDetails) {
				
		boolean success = roomDetailsService.updateRoom(roomDetails);
		if(success)
			return allRooms();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
	}
	
	@RequestMapping("/allrooms")
	public ModelAndView allRooms() {
		ModelAndView modelAndView = new ModelAndView();
		List<RoomDetails> allRooms = roomDetailsService.getAllRoomDetails();
		modelAndView.setViewName("allrooms");
		
		return modelAndView; 
	}
}
