package com.demo.myapp.repository;

import com.demo.myapp.entity.Occupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OccupancyRepo extends JpaRepository<Occupancy,Integer> {

    @Query(value = "Select * from occupancy where booking_id = ?1", nativeQuery = true)
    List<Occupancy> findOccByBookId(int bookingId);
}
