package be.ucll.CampusApplicatie.service;

import be.ucll.CampusApplicatie.entity.Campus;
import be.ucll.CampusApplicatie.entity.Room;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusNotFoundException;
import be.ucll.CampusApplicatie.repository.CampusRepository;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.CampusApplicatie.repository.RoomRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CampusRepository campusRepository;

    public Room addRoomToCampus(String campusName, Room room) {
        validateRoom(room);

        Campus campus = campusRepository.findByName(campusName)
                .orElseThrow(() -> new CampusNotFoundException(campusName));

        Optional<Room> existingRoom = roomRepository.findByNameAndCampusName(room.getName(), campusName);
        if (existingRoom.isPresent()) {
            throw new RoomAlreadyExistsException(String.format("Room '%s' already exists in campus '%s'", room.getName() , campus.getName()));
        }

        room.setCampus(campus);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        validateRoom(room);

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));

        if (!existingRoom.getName().equals(room.getName())) {
            Optional<Room> other = roomRepository.findByNameAndCampusName(
                    room.getName(), existingRoom.getCampus().getName());
            if (other.isPresent()) {
                throw new RoomAlreadyExistsException(
                        String.format("Room '%s' already exists in campus '%s'", room.getName(), existingRoom.getCampus().getName())
                );
            }
        }

        existingRoom.setName(room.getName());
        existingRoom.setType(room.getType());
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setFirstName(room.getFirstName());
        existingRoom.setLastName(room.getLastName());
        existingRoom.setFloor(room.getFloor());
        return roomRepository.save(existingRoom);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
        roomRepository.delete(room);
    }

    public List<Room> getRoomsByCampus(String campusName) {
        campusRepository.findByName(campusName)
                .orElseThrow(() -> new CampusNotFoundException(campusName));

        return roomRepository.findByCampusName(campusName);
    }

    private void validateRoom(Room room) {
        if (room == null || room.getName() == null || room.getName().isBlank()) {
            throw new RoomBadRequestException("Room name is mandatory");
        }
        if (room.getCapacity() <= 0) {
            throw new RoomBadRequestException("Room capacity must be > 0");
        }
    }
}
