package com.demo.myapp.repository;


import com.demo.myapp.entity.Concurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcurrencyRepo extends JpaRepository<Concurrency,Integer> {

    @Query(value = "select c.seat_no from concurrency c where bus_id=?1",nativeQuery = true)
    List<Integer> getConcurrentSeats(int bus_id);
}
