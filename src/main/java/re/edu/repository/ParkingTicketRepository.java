package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.ParkingTicket;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

}
