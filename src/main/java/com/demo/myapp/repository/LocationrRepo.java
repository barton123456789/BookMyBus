package com.demo.myapp.repository;
import com.demo.myapp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface LocationrRepo extends JpaRepository<Location,Integer> {

//    @Query("select l.id from Location l where l.name =:locationName")
//    private String findLocationIdByName(@Param String locationName)
//}

//    @Query(value = "select loc.id from location as loc  where location.name =:locationname" , nativeQuery = true)
//    List<Integer> findByRouteLocationName(String locationname);
    @Query(value="select ld.loc_name, ld.latitude, ld.longitude,ld.loc_id from bus_details bd\n" +
            "join route_details rd on rd.route_id = bd.route_id\n" +
            "join location_details ld on rd.loc_name = ld.loc_name\n" +
            "where bd.bus_id =(?1)  \n" +
            "and rd.route_id = bd.route_id\n" +
            "and rd.`duration` < (2*60 + 1)  \n" +
            "order by rd.`duration` desc limit 1", nativeQuery = true)
   public List<Location> findLocationByBus(int busId);
}