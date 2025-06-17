package be.ucll.CampusApplicatie.repository;

import be.ucll.CampusApplicatie.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByCampusName(String campusName);

    Optional<Room> findByNameAndCampusName(String name, String campusName);
}
