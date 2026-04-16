package re.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.dto.response.VehicleResponse;
import re.edu.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("""
        select new re.edu.dto.response.VehicleResponse(
            v.id,
            v.licensePlate,
            v.color,
            v.vehicleType
        )
        from Vehicle v
        where (:keyword is null
               or lower(v.licensePlate) like lower(concat('%', :keyword, '%'))
               or lower(v.color) like lower(concat('%', :keyword, '%'))
               or lower(cast(v.vehicleType as string)) like lower(concat('%', :keyword, '%')))
        """)
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
