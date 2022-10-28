package com.demo.myapp.repository;
import com.demo.myapp.entity.Route;
import com.demo.myapp.payloads.CodeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route,Integer> {
  @Query(value="select r.loc_name from route_details r where r.route_id = (?1) order by r.rank_no", nativeQuery = true)
  List<String> getStopsById(Integer route_id);
  @Query(value="SELECT route_id, GROUP_CONCAT(loc_name ORDER BY loc_name ASC SEPARATOR ' -> ') AS loc_code \n" +
          "FROM route_details \n" +
          "GROUP BY route_id", nativeQuery = true)
   public List<CodeDto> findCodeById();
}