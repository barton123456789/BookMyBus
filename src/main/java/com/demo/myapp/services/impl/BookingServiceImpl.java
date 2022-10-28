package com.demo.myapp.services.impl;
import com.demo.myapp.entity.Booking;
import com.demo.myapp.entity.Occupancy;
import com.demo.myapp.exception.ResourceNotFoundException;
import com.demo.myapp.payloads.EmailDto;
import com.demo.myapp.payloads.BookingDto;
import com.demo.myapp.payloads.OccupancyDto;
import com.demo.myapp.payloads.SrcDestDto;
import com.demo.myapp.repository.*;
import com.demo.myapp.services.BookingService;
import com.demo.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo BookingsRepo;
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private com.demo.myapp.repository.OccupancyRepo OccupancyRepo;
    @Autowired
    private BusRepo busrepo;

    @Autowired
    private RouteRepo routeRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    private UserService userservice;
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ConcurrencyRepo concurrencyRepo;

    @Override
    public BookingDto createbooking(BookingDto BookingsDto) throws MessagingException {
        Booking Bookings=this.dtoTobookings(BookingsDto);
        Booking savedbooking=this.BookingsRepo.save(Bookings);
        BookingsDto.setBooking_id(savedbooking.getBooking_id());

       // List<OccupancyDto> passengers = null; // call the new method
        EmailDto emailTemplate = new EmailDto();
        emailTemplate.setBooking(BookingsDto);
      //  emailTemplate.setPassengerDetaials(passengers);

        emailService.ticketConfirmMailSender(emailTemplate);

    return this.bookingsToDto(savedbooking);
    }



    @Override
    public BookingDto updatebooking(BookingDto BookingDto, Integer bookingId) {
        Booking Booking=this.BookingsRepo.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("bookings","booking_id",bookingId));
        Booking.setBooking_id(BookingDto.getBooking_id());
        Booking.setBooking_date(BookingDto.getBooking_date());
        Booking.setBooking_status(BookingDto.getBooking_status());
        Booking.setCount(BookingDto.getCount());
        Booking.setBus_id(BookingDto.getBus_id());
        Booking.setTotal_fare(BookingDto.getTotal_fare());
       // Booking.setUser_id(BookingDto.getUser_id());
        Booking.setTotal_fare(BookingDto.getTotal_fare());
        Booking.setTrip_destination(BookingDto.getTrip_destination());
        Booking.setTrip_source(BookingDto.getTrip_source());
        Booking.setJourney_date(BookingDto.getJourney_date());
        com.demo.myapp.entity.Booking updatedbookings=this.BookingsRepo.save(Booking);
        com.demo.myapp.payloads.BookingDto bookingDto1=this.bookingsToDto(updatedbookings);
        return bookingDto1;
    }

    @Override
    public BookingDto getbookingById(Integer booking_id) {
        Booking Booking=this.BookingsRepo.findById(booking_id).orElseThrow(() -> new ResourceNotFoundException("booking","booking_id",booking_id));
        return this.bookingsToDto(Booking);
    }




    public List<Integer>  getByBusAndDate(int bus_id, Date date) {
//        List<Object[]> seats=BookingsRepo.findByBusAndDate(bus_id, date);
        List<Integer> seats=BookingsRepo.findByBusAndDate(bus_id, date);
        List<Integer> seatdtos= new ArrayList<>();
//        for(Object[] obj:seats){
//            Seatdto seatdto = new Seatdto();
//            seatdto.setSeat_no(Integer.parseInt( obj[0].toString()));
//
//            System.out.println(obj[0]);
//
//            seatdtos.add(seatdto);
//
//        }
        return seats;


    }

    private boolean checkOverLapping(List<String> stops, String src1, String dest1, String src2, String dest2 ){
        int srcRank1, destRank1, srcRank2, destRank2;
        srcRank1 = stops.indexOf(src1);
        destRank1 = stops.indexOf(dest1);
        srcRank2 = stops.indexOf(src2);
        destRank2 = stops.indexOf(dest2);
        if((srcRank1 >= destRank1) || (srcRank2 >= destRank2))
            return true;
        if( (srcRank2 >= destRank1) || (srcRank1 >= destRank2) )
            return false;
        return true;
    }

    private List<SrcDestDto> convertToSrcDestDto(List<Booking> bookings){
        List<SrcDestDto> result = new ArrayList<>();
        for(Booking booking: bookings){
            SrcDestDto dto = new SrcDestDto();
            dto.setSource(booking.getTrip_source());
            dto.setDestination(booking.getTrip_destination());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Integer> getByBusIdAndDateAndSrcAndDest(int busId, Date date, String src, String dest) {
        List<Integer> seats=BookingsRepo.findByBusAndDate(busId, date);
        List<Integer> resultSeats = new ArrayList<>();

        int route_id = busrepo.getRouteIdByBusId(busId);
        List<String> stops = routeRepo.getStopsById(route_id);

        for( Integer seatNo : seats ){
            boolean isOverLapping = false;
            // overlapping logic
            // busId, date, seatNo
            List<SrcDestDto> srcDestDtos = convertToSrcDestDto(bookingRepo.findSrCAndDestComb(busId,date,seatNo));

            for(SrcDestDto srcDestDto : srcDestDtos){
                isOverLapping = checkOverLapping(stops, srcDestDto.getSource(), srcDestDto.getDestination(), src, dest);
                if(isOverLapping)
                    break;
            }
            if(isOverLapping)
                resultSeats.add(seatNo);
        }
        // select c.seat_no from concurrency c where c.bus_id = 23; //task Done

        // get seats from concurrencyRepo using above query and append the list to resultSeats
          List<Integer> concurrencySeats = concurrencyRepo.getConcurrentSeats(busId);
          resultSeats.addAll(concurrencySeats);

          return resultSeats;
    }

    @Override
    public List<BookingDto> getAllbooking() {
        List<Booking> Booking= this.BookingsRepo.findAll();
        List<BookingDto> bookingsDtos=Booking.stream().map(bookings ->this.bookingsToDto(bookings)).collect(Collectors.toList());
        return bookingsDtos;
    }

    @Override
    public void deletebooking(Integer booking_id) {
        Booking Booking=this.BookingsRepo.findById(booking_id).orElseThrow(()-> new ResourceNotFoundException("bookings","booking_id",booking_id));
        this.BookingsRepo.delete(Booking);
    }


    @Override
    public List<BookingDto> getBookingByUser(int user_id) {

        List<Booking> bookings = BookingsRepo.findBookingByUser(user_id);

        List<BookingDto> result = new ArrayList<>();

        for( Booking booking: bookings ){
            BookingDto bookingDto = bookingsToDto(booking);
            result.add(bookingDto);
        }
        return result;
    }

    public Booking dtoTobookings(BookingDto BookingsDto){
        Booking Bookings= new Booking();
        Bookings.setBooking_id(BookingsDto.getBooking_id());
        Bookings.setBooking_date(BookingsDto.getBooking_date());
        Bookings.setBooking_status(BookingsDto.getBooking_status());
        Bookings.setCount(BookingsDto.getCount());
        Bookings.setBus_id(BookingsDto.getBus_id());
        Bookings.setTotal_fare(BookingsDto.getTotal_fare());
        Bookings.setUser_id(BookingsDto.getUser_id());
        Bookings.setTotal_fare(BookingsDto.getTotal_fare());
        Bookings.setTrip_destination(BookingsDto.getTrip_destination());
        Bookings.setTrip_source(BookingsDto.getTrip_source());
        Bookings.setJourney_date(BookingsDto.getJourney_date());
        return  Bookings;
    }
    public BookingDto bookingsToDto(Booking Bookings){
        BookingDto BookingsDto= new BookingDto();
        BookingsDto.setBooking_id(Bookings.getBooking_id());
        BookingsDto.setBooking_date(Bookings.getBooking_date());
        BookingsDto.setBooking_status(Bookings.getBooking_status());
        BookingsDto.setCount(Bookings.getCount());
        BookingsDto.setBus_id(Bookings.getBus_id());
        BookingsDto.setTotal_fare(Bookings.getTotal_fare());
        //BookingsDto.setUser(userservice.getUserById(Bookings.getUser_id()));
        BookingsDto.setTotal_fare(Bookings.getTotal_fare());
        BookingsDto.setTrip_destination(Bookings.getTrip_destination());
        BookingsDto.setTrip_source(Bookings.getTrip_source());
        BookingsDto.setJourney_date(Bookings.getJourney_date());
        BookingsDto.setUser_id(Bookings.getUser_id());
        return BookingsDto;
    }
}