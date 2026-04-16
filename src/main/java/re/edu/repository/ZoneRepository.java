package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    boolean existsByName(String name);
}
