package com.seclore.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seclore.main.domain.BookingDetails;
import com.seclore.main.domain.RoomDetails;
import com.seclore.main.domain.UserDetails;
import com.seclore.main.service.BookingDetailsServiceInterface;


@RequestMapping("book")
@RestController
public class BookingDetailsController {
	@Autowired
	BookingDetailsServiceInterface bookingDetailsService;
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public int insertintobookingDetails() {
		UserDetails userDetails=new UserDetails();
		userDetails.setUserId(3);
		RoomDetails roomDetails=new RoomDetails();
		roomDetails.setRoomId(2);
		BookingDetails bookingDetails=new BookingDetails();
		bookingDetails.setUser(userDetails);
		bookingDetails.setRoom(roomDetails);
		bookingDetails.setDescription(("description"));
		bookingDetails.setStatus(("CANCELLED"));
		return bookingDetailsService.addBookingDetails(bookingDetails);
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public BookingDetails updateintobookingDetails() {
		UserDetails userDetails=new UserDetails();
		userDetails.setUserId(3);
		RoomDetails roomDetails=new RoomDetails();
		roomDetails.setRoomId(2);
		BookingDetails bookingDetails=new BookingDetails();
		bookingDetails.setUser(userDetails);
		bookingDetails.setRoom(roomDetails);
		bookingDetails.setDescription(("description"));
		bookingDetails.setStatus(("CANCELLED"));
		return bookingDetailsService.updateExistingBookingDetails(bookingDetails);
	}
	@RequestMapping(value="get",method=RequestMethod.GET)
	public BookingDetails getexsistingBookingdetailsbybookingid() {
		
		return bookingDetailsService.getExistingBookingDetails(2);
	}
	@RequestMapping(value="getall",method=RequestMethod.GET)
	public List<BookingDetails> getexsistingBookingdetailsbyuserid() {
		
		return bookingDetailsService.getAllExistingBookingDetailsByUserId(3);
	}
	@RequestMapping(value="getallbyadmin",method=RequestMethod.GET)
	public List<BookingDetails> getexsistingBookingdetailsbyadmin() {
		
		return bookingDetailsService.g(3);
	}
}
