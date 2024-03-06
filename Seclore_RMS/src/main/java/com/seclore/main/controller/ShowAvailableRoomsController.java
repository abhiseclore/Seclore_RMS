package com.seclore.main.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.BookingViewDetailsServiceInterface;
import com.seclore.main.service.RoomDetailsServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShowAvailableRoomsController {

	@Autowired
	RoomDetailsServiceInterface roomDetailsServiceInterface;
	@Autowired
	BookingViewDetailsServiceInterface bookingViewDetailsServiceInterface;

	@RequestMapping("/add")
	public ModelAndView getAvailableRoomsBySlot(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
			@RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam int seatingCapacity,
			@RequestParam boolean hasWhiteboard, @RequestParam boolean hasAudioVideo) {
		ModelAndView modelAndView = new ModelAndView();
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setCapacity(seatingCapacity);
		roomDetails.setHasAudioVideo(hasAudioVideo);
		roomDetails.setHasWhiteboard(hasWhiteboard);
		List<RoomDetails> allAvailableRoom = roomDetailsServiceInterface.getAvailableRoomsWithCondition(roomDetails);
		List<RoomDetails> availableRooms = bookingViewDetailsServiceInterface.getAvailableRoomsBySlot(allAvailableRoom,
				startTime, endTime, startDate, endDate);

		modelAndView.addObject("allAvailableRoom", availableRooms);
		modelAndView.setViewName("viewAvailable");
		return modelAndView;
	}
}
