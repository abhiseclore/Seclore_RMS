package com.seclore.main.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
		ModelAndView modelAndView = new ModelAndView();
		RoomDetails roomDetails = new RoomDetails();
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		LocalDate startDate = (LocalDate) httpSession.getAttribute("startDate");
		LocalDate endDate = (LocalDate) httpSession.getAttribute("endDate");
		LocalTime startTime = (LocalTime) httpSession.getAttribute("startTime");
		LocalTime endTime = (LocalTime) httpSession.getAttribute("endTime");
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

	@Transactional
	@RequestMapping("delete")
	public ModelAndView cancelExistingBookingDetails(@RequestParam int index, HttpSession httpSession) {
		System.out.println("hi");
		List<BookingViewDetails> allBookingViewDetails = (List<BookingViewDetails>) httpSession
				.getAttribute("allBookingViewDetails");
		System.out.println(allBookingViewDetails);
		UserDetails userDetails=(UserDetails) httpSession.getAttribute("loggedInUser");
		
		 ModelAndView redirectModelAndView = new ModelAndView();
		 if(userDetails.getPosition().equals("Admin")){
			 redirectModelAndView.setViewName("admindashboard");
		 }
		 else
			 redirectModelAndView.setViewName("userdashbord");
		 

		BookingDetails bookingDetails = allBookingViewDetails.get(index).getBookingSlots().getBooking();
		
		if (bookingDetailsService.cancelExistingBookingDetails(bookingDetails)) {
			return redirectModelAndView;
		}
		return redirectModelAndView;

	}

	@RequestMapping("update")
	public String updateExistingBookingDetails(@ModelAttribute BookingDetails bookingDetails) {

		return bookingDetailsService.updateExistingBookingDetails(bookingDetails) != null ? "showallbookings" : "error";
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
		System.out.println("showallbookings");
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		ModelAndView redirectModelAndView = new ModelAndView("redirect:/showallbookingsbyadmin");
		if (userDetails.getPosition().equals("Admin"))
			return redirectModelAndView;

		List<BookingDetails> allBookingDetailsByUserId = bookingDetailsService
				.getAllExistingBookingDetailsByUserId(userDetails.getUserId());

		List<BookingViewDetails> allBookingViewDetailsByUserId = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetailsByUserId);

		httpSession.setAttribute("allBookingViewDetails", allBookingViewDetailsByUserId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allBookingViewDetailsByUserId", allBookingViewDetailsByUserId);
		modelAndView.setViewName("showallbooking");
		return modelAndView;
	}

	@RequestMapping("showallbookingsbyadmin")
	public ModelAndView getAllExistingBookingDetailsByAdmin(HttpSession httpSession) {

		List<BookingDetails> allBookingDetails = bookingDetailsService.getAllExistingBookingDetailsByadmin();

		List<BookingViewDetails> allBookingViewDetailsByAdmin = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetails);
		httpSession.setAttribute("allBookingViewDetails", allBookingViewDetailsByAdmin);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("allBookingViewDetailsByAdmin", allBookingViewDetailsByAdmin);
		modelAndView.setViewName("showallbookingbyadmin");
		return modelAndView;
	}

	@RequestMapping("updateslot")
	public ModelAndView updateExistingBookingDetailsBySlot(@RequestParam int index, HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		List<BookingViewDetails> allBookingViewDetails = (List<BookingViewDetails>) httpSession
				.getAttribute("allBookingViewDetails");
		BookingViewDetails bookingViewDetails = allBookingViewDetails.get(index);
		httpSession.setAttribute("bookingViewDetails", bookingViewDetails);
		modelAndView.addObject("bookingViewDetails", bookingViewDetails);
		modelAndView.setViewName("updatebookingslots");
		return modelAndView;
	}

	@RequestMapping("cancelpartialbooking")
	public String updateExistingBookingDetailsBySlot(HttpSession httpSession, @RequestParam LocalTime newStartTime,
			@RequestParam LocalTime newEndTime, @RequestParam String action) {

		ModelAndView modelAndView = new ModelAndView();
		BookingViewDetails bookingViewDetails = (BookingViewDetails) httpSession.getAttribute("bookingViewDetails");
		if (bookingDetailsService.cancelPartialBooking(bookingViewDetails.getBookingSlots().getBooking(),
				bookingViewDetails.getSlotMaster().getStartTime(), bookingViewDetails.getSlotMaster().getEndTime(),
				newStartTime, newEndTime, bookingViewDetails.getSlotMaster().getDate(), action)) {
			return "showallbookings";
		} else {
			return "error";
		}

	}

}
