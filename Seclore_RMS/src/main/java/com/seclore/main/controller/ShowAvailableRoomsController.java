package com.seclore.main.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.RoomDetails;
import com.seclore.main.service.BookingViewDetailsServiceInterface;
import com.seclore.main.service.RoomDetailsServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShowAvailableRoomsController {

	@Autowired
	RoomDetailsServiceInterface roomDetailsServiceInterface;
	@Autowired
	BookingViewDetailsServiceInterface bookingViewDetailsServiceInterface;

	@RequestMapping(value = "add" , method = RequestMethod.POST)
	public ModelAndView getAvailableRoomsBySlot(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
			@RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam int seatingCapacity,
			@RequestParam boolean hasWhiteboard, @RequestParam boolean hasAudioVideo,HttpSession httpSession) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setCapacity(seatingCapacity);
		roomDetails.setHasAudioVideo(hasAudioVideo);
		roomDetails.setHasWhiteboard(hasWhiteboard);
		
		httpSession.setAttribute("startTime", startTime);
		httpSession.setAttribute("endTime", endTime);
		httpSession.setAttribute("startDate", startDate);
		httpSession.setAttribute("endDate", endDate);
		
		List<RoomDetails> allAvailableRoom = roomDetailsServiceInterface.getAvailableRoomsWithCondition(roomDetails);
		if (allAvailableRoom == null || allAvailableRoom.size() == 0) {
			modelAndView.addObject("message", "NO ROOMS FILLS YOUR REQUIREMENTS");
			modelAndView.setViewName("getroomrequirements");
			return modelAndView;
		}
		List<RoomDetails> availableRooms = bookingViewDetailsServiceInterface.getAvailableRoomsBySlot(allAvailableRoom,
				startTime, endTime, startDate, endDate);
		if (availableRooms == null || availableRooms.size() == 0) {
			modelAndView.addObject("message", "ALL ROOMS FULFILLING YOUR CRITERIA ARE BOOKED");
			modelAndView.setViewName("getroomrequirements");
			return modelAndView;
		}
		modelAndView.addObject("allAvailableRoom", availableRooms);
		modelAndView.setViewName("showavailablerooms");
		return modelAndView;
	}
}
