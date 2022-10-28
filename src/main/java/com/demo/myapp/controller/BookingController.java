package com.demo.myapp.controller;
import com.demo.myapp.payloads.OccupancyDto;
import com.demo.myapp.payloads.Response;
import com.demo.myapp.payloads.BookingDto;
import com.demo.myapp.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("app/bookings")
public class BookingController {
    @Autowired
    private BookingService BookingsService;

    @PostMapping("/")
    public ResponseEntity<BookingDto> createbooking(@RequestBody BookingDto BookingsDto) throws MessagingException {
        BookingDto createbookingDto = this.BookingsService.createbooking(BookingsDto);
        return new ResponseEntity<>(createbookingDto, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDto> updatebooking(@RequestBody BookingDto BookingsDto, @PathVariable Integer bookingId) {
        BookingDto updatebooking = this.BookingsService.updatebooking(BookingsDto, bookingId);
        return ResponseEntity.ok(updatebooking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Response> deletebooking(@PathVariable("bookingId") Integer bid) {
        this.BookingsService.deletebooking(bid);
        return new ResponseEntity(new Response("user deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookingDto>> getAllbooking() {
        return ResponseEntity.ok(this.BookingsService.getAllbooking());
    }

    @GetMapping("/{bookingId}/{userId}")
    public ResponseEntity<BookingDto> getSinglebooking(@PathVariable Integer bookingId,
                                                       @PathVariable Integer userId) {
        return ResponseEntity.ok(this.BookingsService.getbookingById(bookingId));
    }

    @GetMapping("/bus/date")
    public List<Integer> getByBusAndDate(@RequestParam(name = "bus") int bus_id,
                                         @RequestParam(name = "date") Date date) {
        return BookingsService.getByBusAndDate(bus_id, date);
    }

    @GetMapping("/getByBusIdAndDateAndSrcAndDest")
    public List<Integer> getByBusIdAndDateAndSrcAndDest(@RequestParam(name = "bus") int bus_id,
                                         @RequestParam(name = "date") Date date,
                                                        @RequestParam(name = "src") String src, @RequestParam(name = "dest") String dest) {
        return BookingsService.getByBusIdAndDateAndSrcAndDest(bus_id, date, src, dest);
    }


    @GetMapping("/getBookByUser")
    public List<BookingDto> getBookingByUser(@RequestParam(name = "user_id") int user_id) {
        return BookingsService.getBookingByUser(user_id);
    }

}