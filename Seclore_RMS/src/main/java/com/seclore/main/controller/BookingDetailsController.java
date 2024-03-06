package com.seclore.main.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.seclore.main.service.BookingSlotsServiceInterface;
import com.seclore.main.service.BookingViewDetailsServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("bookingdetails")
public class BookingDetailsController {
	@Autowired
	BookingDetailsServiceInterface bookingDetailsService;
	@Autowired
	BookingViewDetailsServiceInterface bookingViewDetailsService;
//	@RequestMapping(value="insert",method=RequestMethod.POST)
//	public int insertintobookingDetails() {
//		UserDetails userDetails=new UserDetails();
//		userDetails.setUserId(3);
//		RoomDetails roomDetails=new RoomDetails();
//		roomDetails.setRoomId(2);
//		BookingDetails bookingDetails=new BookingDetails();
//		bookingDetails.setUser(userDetails);
//		bookingDetails.setRoom(roomDetails);
//		bookingDetails.setDescription(("description"));
//		bookingDetails.setStatus(("CANCELLED"));
//		return bookingDetailsService.addBookingDetails(bookingDetails);
//	}
//	

//	 @Autowired
//	    private UserService userService;
//
//	    @RequestMapping(value = "/user/signIn", method = RequestMethod.POST)
//	    public ResponseEntity<DataUser> signIn(@RequestBody @Valid SignInUser signInUser,
//	                                           HttpSession session) {
//	        User user = userService.getUser(signInUser.getEmail(), signInUser.getPassword());
//	        session.setAttribute("user", user);
//	        DataUser dataUser = new DataUser((User) session.getAttribute("user"));
//	        return ResponseEntity.ok(dataUser);
//	    }

	@RequestMapping("/add")
	public ModelAndView addBookingDetails(@RequestParam RoomDetails roomDetails, @RequestParam String description,
			@RequestParam HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		LocalDate startDate = (LocalDate) httpSession.getAttribute("startDate");
		LocalDate endDate = (LocalDate) httpSession.getAttribute("endDate");
		LocalTime startTime = (LocalTime) httpSession.getAttribute("startTime");
		LocalTime endTime = (LocalTime) httpSession.getAttribute("endTime");

		if (!bookingViewDetailsService.checkRoomAvailabilityBySlot(roomDetails, startTime, endTime, startDate,
				endDate)) {
			modelAndView.addObject("message", "	ROOM IS BOOKED PLEASE CHECK FOR ANOTHER ROOM");
			modelAndView.setViewName("getroomrequirements");
			return modelAndView;
		}

		BookingDetails bookingDetails = bookingDetailsService.addBookingDetails(startDate, endDate, startTime, endTime,
				userDetails.getUserId(), roomDetails.getRoomId(), description);
		
		if(bookingDetails==null) {
			modelAndView.addObject("message", "	Unable to add the room");
			modelAndView.setViewName("error");
		}

		modelAndView.addObject("bookingDetails", bookingDetails);
		modelAndView.setViewName("finalbooking");
		return modelAndView;

	}

	@RequestMapping("/delete")
	@Transactional
	public String cancelExistingBookingDetails(@RequestParam BookingDetails bookingDetails) {

		if (bookingDetailsService.cancelExistingBookingDetails(bookingDetails)) {
			return "showallbooking";
		} else {
			return "error";
		}
	}

	@RequestMapping("/update")
	public String updateExistingBookingDetails(@RequestParam BookingDetails bookingDetails) {
		BookingDetails updatedBookingDetails = bookingDetailsService.updateExistingBookingDetails(bookingDetails);
		if (updatedBookingDetails != null) {
			return "showallbooking";
		} else {
			return "error";
		}

	}

	@RequestMapping("")
	public ModelAndView getExistingBookingDetails(@RequestParam int bookingID) {
		BookingDetails bookingDetails = bookingDetailsService.getExistingBookingDetails(bookingID);
		BookingViewDetails bookingViewDetails = bookingViewDetailsService.getStartEndTimeForSingleId(bookingDetails);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("bookingViewDetails", bookingViewDetails);
		modelAndView.setViewName("showallbooking");
		return modelAndView;

	}

	@RequestMapping("/showallbooking")
	public ModelAndView getAllExistingBookingDetails(@RequestParam HttpSession httpSession) {

		UserDetails userDetails = (UserDetails) httpSession.getAttribute("loggedInUser");
		if (userDetails.getPosition().equals("Admin")) {
			return getAllExistingBookingDetailsByadmin();
		}

		List<BookingDetails> allBookingDetailsByUserId = bookingDetailsService
				.getAllExistingBookingDetailsByUserId(userDetails.getUserId());
		List<BookingViewDetails> allBookingViewDetailsByUserId = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetailsByUserId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allBookingViewDetailsByUserId", allBookingViewDetailsByUserId);
		modelAndView.setViewName("showallbooking");
		return modelAndView;

	}

	@RequestMapping("")
	public ModelAndView getAllExistingBookingDetailsByadmin() {
		List<BookingDetails> allBookingDetails = bookingDetailsService.getAllExistingBookingDetailsByadmin();
		List<BookingViewDetails> allBookingViewDetailsByAdmin = bookingViewDetailsService
				.getStartEndTimeByBookingId(allBookingDetails);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allBookingViewDetailsByAdmin", allBookingViewDetailsByAdmin);
		modelAndView.setViewName("showallbookingbyadmin");
		return modelAndView;

	}

//	
//	@RequestMapping(value="update",method=RequestMethod.POST)
//	public BookingDetails updateintobookingDetails() {
//		UserDetails userDetails=new UserDetails();
//		userDetails.setUserId(3);
//		RoomDetails roomDetails=new RoomDetails();
//		roomDetails.setRoomId(2);
//		BookingDetails bookingDetails=new BookingDetails();
//		bookingDetails.setUser(userDetails);
//		bookingDetails.setRoom(roomDetails);
//		bookingDetails.setDescription(("description"));
//		bookingDetails.setStatus(("CANCELLED"));
//		return bookingDetailsService.updateExistingBookingDetails(bookingDetails);
//	}
//	@RequestMapping(value="get",method=RequestMethod.GET)
//	public BookingDetails getexsistingBookingdetailsbybookingid() {
//		
//		return bookingDetailsService.getExistingBookingDetails(2);
//	}
//	@RequestMapping(value="getall",method=RequestMethod.GET)
//	public List<BookingDetails> getexsistingBookingdetailsbyuserid() {
//		
//		return bookingDetailsService.getAllExistingBookingDetailsByUserId(3);
//	}
//	@RequestMapping(value="getallbyadmin",method=RequestMethod.GET)
//	public List<BookingDetails> getexsistingBookingdetailsbyadmin() {
//		
//		return bookingDetailsService.g(3);
//	}
}
