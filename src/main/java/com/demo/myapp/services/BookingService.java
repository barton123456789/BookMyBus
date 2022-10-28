package com.demo.myapp.services;
import com.demo.myapp.entity.Booking;
import com.demo.myapp.payloads.BookingDto;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.List;
public interface BookingService {
    BookingDto createbooking(BookingDto booking) throws MessagingException;
    BookingDto updatebooking(BookingDto booking, Integer bookingId );
    BookingDto getbookingById(Integer bookingId);
    List<BookingDto> getAllbooking();
    void deletebooking(Integer bookingId);

    List<Integer> getByBusAndDate(int bus_id, Date date);

    List<Integer> getByBusIdAndDateAndSrcAndDest(int busId, Date date, String src, String dest);
    List<BookingDto> getBookingByUser(int user_id);
}
