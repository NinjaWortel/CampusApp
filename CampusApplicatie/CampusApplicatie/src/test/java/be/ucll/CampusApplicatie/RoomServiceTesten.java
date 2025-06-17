package be.ucll.CampusApplicatie;

import be.ucll.CampusApplicatie.entity.Campus;
import be.ucll.CampusApplicatie.entity.Room;
import be.ucll.CampusApplicatie.repository.CampusRepository;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusNotFoundException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomNotFoundException;
import be.ucll.CampusApplicatie.repository.RoomRepository;
import be.ucll.CampusApplicatie.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomServiceTesten {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private CampusRepository campusRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void addRoomWithEmptyNameShouldThrowBadRequest() {
        Room invalidRoom = new Room(20, "Jan", 2, "Jansen", "", "leslokaal");

        assertThrows(RoomBadRequestException.class, () -> roomService.addRoomToCampus("CAMPUS1", invalidRoom));

        verifyNoInteractions(campusRepository, roomRepository);
    }

    @Test
    void addRoomToNonExistingCampusShouldThrowException() {
        Room validRoom = new Room(20, "Jan", 2, "Jansen", "Room-A", "leslokaal");

        when(campusRepository.findByName("UNKNOWN")).thenReturn(Optional.empty());

        assertThrows(CampusNotFoundException.class, () -> roomService.addRoomToCampus("UNKNOWN", validRoom));
    }

    @Test
    void addRoomWithDuplicateNameShouldThrowException() {
        Campus campus = new Campus("CAMPUS", "Campusstraat 1", 10, new ArrayList<>());

        Room room = new Room(20, "Jan", 2, "Jansen", "Room-A", "leslokaal");

        when(campusRepository.findByName("CAMPUS")).thenReturn(Optional.of(campus));
        when(roomRepository.findByNameAndCampusName("Room-A", "CAMPUS")).thenReturn(Optional.of(room));

        assertThrows(RoomAlreadyExistsException.class, () -> roomService.addRoomToCampus("CAMPUS", room));
    }

    @Test
    void deleteNonExistingRoomShouldThrowException() {
        when(roomRepository.existsById(999L)).thenReturn(false);

        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoom(999L));
    }
}

