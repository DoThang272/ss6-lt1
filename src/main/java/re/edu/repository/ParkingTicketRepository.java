package re.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.entity.ParkingTicket;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    @Query("""
        select pt
        from ParkingTicket pt
        where pt.vehicle.id = :vehicleId
          and pt.checkOutTime is null
        order by pt.checkInTime desc
        """)
    Page<ParkingTicket> findActiveTicketsByVehicleId(
            @Param("vehicleId") Long vehicleId,
            Pageable pageable
    );
}