package com.demo.myapp.repository;
import com.demo.myapp.entity.Booking;
import com.demo.myapp.payloads.SrcDestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

//    @Query(value = "SELECT new com.demo.myapp.payloads.busdto(b.bus_id, b.capacity, b.source, b.destination, b.fare, b.type, o.passenger_name, o.seat_no) " +
//            "FROM bus_details b JOIN occupancy o " +
//            "WHERE b.source = :source " +
//            "AND b.destination = :destination")
//    List<busdto> findByBusId(String source, String destination);

    @Query(value = "SELECT distinct(o.seat_no) as seatNo\n" +
            "FROM booking b\n" +
            "join occupancy o on o.booking_id = b.booking_id\n" +
            "where b.bus_id =?1 and b.journey_date = ?2\n", nativeQuery = true)
//    public List<Object[]> findByBusAndDate(int bus_id, Date date);
    public List<Integer> findByBusAndDate(int bus_id, Date date);


    @Query(value="select * from booking b \n" +
            "join occupancy o on o.booking_id = b.booking_id \n" +
            "where b.bus_id = (?1) and b.journey_date = (?2) and o.seat_no =(?3)", nativeQuery = true)

    List<Booking> findSrCAndDestComb(int bus_id,Date journey_date,int seat_no);

    @Query(value = "SELECT * FROM booking where user_id=(?1)",nativeQuery = true)

    List<Booking> findBookingByUser(int user_id);
}