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
import com.seclore.main.service.BookingViewDetailsServiceInterface;
import com.seclore.main.service.RoomDetailsServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ShowAvailableRoomsController {

	@Autowired
	RoomDetailsServiceInterface roomDetailsServiceInterface;
	@Autowired
	BookingViewDetailsServiceInterface bookingViewDetailsServiceInterface;

	@RequestMapping("add")
	public ModelAndView getAvailableRoomsBySlot(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
			@RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam int seatingCapacity,
			HttpSession httpSession, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		if (startDate.compareTo(endDate) > 1) {
			modelAndView.addObject("message", "START DATE CANNOT BE GREATER THAN END DATE");
			modelAndView.setViewName("getbookingrequirements");
			return modelAndView;
		}
		if (startDate.compareTo(endDate) == 0 && startTime.compareTo(endTime) >= 1) {
			modelAndView.addObject("message", "START TIME CANNOT BE GREATER THAN END TIME");
			modelAndView.setViewName("getbookingrequirements");
			return modelAndView;
		}

		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setCapacity(seatingCapacity);
		if (request.getParameter("hasWhiteboard") == null)
			roomDetails.setHasWhiteboard(false);
		else
			roomDetails.setHasWhiteboard(true);
		if (request.getParameter("hasAudioVideo") == null)
			roomDetails.setHasAudioVideo(false);
		else
			roomDetails.setHasAudioVideo(true);

		httpSession.setAttribute("startTime", startTime);
		httpSession.setAttribute("endTime", endTime);
		httpSession.setAttribute("startDate", startDate);
		httpSession.setAttribute("endDate", endDate);

		List<RoomDetails> allAvailableRoom = roomDetailsServiceInterface.getAvailableRoomsWithCondition(roomDetails);
		if (allAvailableRoom == null || allAvailableRoom.size() == 0) {
			modelAndView.addObject("message", "NO ROOMS FILLS YOUR REQUIREMENTS");
			modelAndView.setViewName("getbookingrequirements");
			return modelAndView;
		}

		List<RoomDetails> availableRooms = bookingViewDetailsServiceInterface.getAvailableRoomsBySlot(allAvailableRoom,
				startTime, endTime, startDate, endDate);
		if (availableRooms == null || availableRooms.size() == 0) {
			modelAndView.addObject("message", "ALL ROOMS FULFILLING YOUR CRITERIA ARE BOOKED");
			modelAndView.setViewName("getbookingrequirements");
			return modelAndView;
		}
		modelAndView.addObject("allAvailableRoom", availableRooms);
		modelAndView.setViewName("showavailablerooms");
		return modelAndView;
	}
}
