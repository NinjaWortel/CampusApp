package be.ucll.CampusApplicatie.repository;

import be.ucll.CampusApplicatie.entity.ReservationRoom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

}
