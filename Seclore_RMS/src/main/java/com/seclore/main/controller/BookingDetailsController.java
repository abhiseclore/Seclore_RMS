package com.seclore.main.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.BookingViewDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.BookingDetailsServiceInterface;
import com.seclore.main.service.BookingViewDetailsServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("bookingdetails")
public class BookingDetailsController {

	@Autowired
	BookingDetailsServiceInterface bookingDetailsService;

	@Autowired
	BookingViewDetailsServiceInterface bookingViewDetailsService;

	@RequestMapping("add")
	public ModelAndView addBookingDetails(@RequestParam int roomId, @RequestParam String description,
			HttpSession httpSession) {
		System.out.println("addBookingDetails");
		ModelAndView modelAndView = new ModelAndView();
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		System.out.println(userDetails);
		LocalDate startDate = (LocalDate) httpSession.getAttribute("startDate");
		System.out.println(startDate);
		LocalDate endDate = (LocalDate) httpSession.getAttribute("endDate");
		LocalTime startTime = (LocalTime) httpSession.getAttribute("startTime");
		LocalTime endTime = (LocalTime) httpSession.getAttribute("endTime");
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setRoomId(roomId);
		if (!bookingViewDetailsService.checkRoomAvailabilityBySlot(roomDetails, startTime, endTime, startDate,
				endDate)) {
			modelAndView.addObject("message", "ROOM IS BOOKED. PLEASE CHECK FOR ANOTHER ROOM");
			modelAndView.setViewName("getroomrequirements");
			return modelAndView;
		}

		List<BookingDetails> allBookingDetails = bookingDetailsService.addBookingDetails(startDate, endDate, startTime,
				endTime, userDetails.getUserId(), roomDetails.getRoomId(), description);

		if (allBookingDetails.isEmpty()) {
			modelAndView.addObject("message", "Unable to add the room");
			modelAndView.setViewName("error");
			return modelAndView;
		}

		List<BookingViewDetails> allBookingViewDetails = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetails);

		modelAndView.addObject("allBookingViewDetails", allBookingViewDetails);
		modelAndView.setViewName("finalbookings");
		return modelAndView;
	}

	@RequestMapping("delete")
	@Transactional
	public String cancelExistingBookingDetails(@ModelAttribute BookingDetails bookingDetails) {
		if (bookingDetailsService.cancelExistingBookingDetails(bookingDetails)) {
			return "showallbooking";
		} else {
			return "error";
		}
	}

	@RequestMapping("update")
	public String updateExistingBookingDetails(@ModelAttribute BookingDetails bookingDetails) {
		BookingDetails updatedBookingDetails = bookingDetailsService.updateExistingBookingDetails(bookingDetails);
		return updatedBookingDetails != null ? "showallbooking" : "error";
	}

	@RequestMapping("get")
	public ModelAndView getExistingBookingDetails(@RequestParam int bookingId) {

		BookingDetails bookingDetails = bookingDetailsService.getExistingBookingDetails(bookingId);

		BookingViewDetails bookingViewDetails = bookingViewDetailsService.getStartEndTimeForSingleId(bookingDetails);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("bookingViewDetails", bookingViewDetails);
		modelAndView.setViewName("showallbooking");
		return modelAndView;
	}

	@RequestMapping("showallbookings")
	public ModelAndView getAllExistingBookingDetails(HttpSession httpSession) {
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		
		if(userDetails.getPosition().equals("Admin"))
			return getAllExistingBookingDetailsByAdmin();
		
		List<BookingDetails> allBookingDetailsByUserId = bookingDetailsService
				.getAllExistingBookingDetailsByUserId(userDetails.getUserId());
		System.out.println(allBookingDetailsByUserId.size());
		
		
		List<BookingViewDetails> allBookingViewDetailsByUserId = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetailsByUserId);
		
		System.out.println(allBookingDetailsByUserId.size());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allBookingViewDetailsByUserId", allBookingViewDetailsByUserId);
		modelAndView.setViewName("showallbooking");
		return modelAndView;
	}

	@RequestMapping("showallbookingbyadmin")
	public ModelAndView getAllExistingBookingDetailsByAdmin() {
		System.out.println("in admin show all booking");
		List<BookingDetails> allBookingDetails = bookingDetailsService.getAllExistingBookingDetailsByadmin();
		System.out.println(allBookingDetails.size());

		List<BookingViewDetails> allBookingViewDetailsByAdmin = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetails);
		
		System.out.println(allBookingViewDetailsByAdmin.size());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allBookingViewDetailsByAdmin", allBookingViewDetailsByAdmin);
		modelAndView.setViewName("showallbookingbyadmin");
		return modelAndView;
	}
	
	@RequestMapping("updateslot")
	public ModelAndView updateExistingBookingDetailsBySlot(@ModelAttribute BookingViewDetails bookingViewDetails) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("bookingViewDetails", bookingViewDetails);
		modelAndView.setViewName("updatebookingslots");
		return modelAndView;
	}

}
