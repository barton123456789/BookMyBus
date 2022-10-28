package com.demo.myapp.repository;
import com.demo.myapp.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;





@Repository
public interface BusRepo extends JpaRepository<Bus,Integer> {
//bus findByRouteId(Integer route_id);

    @Query(value = "select b.* from bus_details b where b.bus_id in (SELECT distinct(bus_id) FROM bus_details b, route_details r " +
            "WHERE r.route_id = b.route_id AND r.route_id in " +
            "(SELECT r1.route_id from route_details r1 " +
            "where r1.loc_name=(?1) and r1.rank_no<(select distinct(r2.rank_no) from route_details r2 " +
            "where r2.loc_name=(?2) and r2.route_id=r1.route_id)))", nativeQuery = true)
    List<Bus> findBySrcAndDest(String src, String dest);

    @Query(value = "SELECT distinct(b.route_id) from bus_details b where b.bus_id=(?1)", nativeQuery = true)
    Integer getRouteIdByBusId(Integer busId);
}
