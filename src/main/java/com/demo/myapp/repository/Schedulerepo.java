package com.demo.myapp.repository;

import com.demo.myapp.entity.Bus_schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Schedulerepo extends JpaRepository<Bus_schedule, Integer> {

//public List<Integer> findByRouteId(int id);
}
