package be.ucll.CampusApplicatie.repository;

import be.ucll.CampusApplicatie.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus, String> {
    Optional<Campus> findByName(String campusName);
}
