package be.ucll.CampusApplicatie.controller;

import be.ucll.CampusApplicatie.DTO.RoomDTO;
import be.ucll.CampusApplicatie.DTO.converter.DTOConverter;
import be.ucll.CampusApplicatie.entity.Campus;
import be.ucll.CampusApplicatie.entity.Room;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomCampusMismatchException;
import be.ucll.CampusApplicatie.repository.Exception.RoomExceptions.RoomNotFoundException;
import be.ucll.CampusApplicatie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/campus/{campusName}/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public List<RoomDTO> getRoomsByCampus(
            @PathVariable String campusName,
            @RequestParam(required = false) String availableFrom,
            @RequestParam(required = false) String availableUntil,
            @RequestParam(required = false) Integer minNumberOfSeats) {

        List<Room> rooms = roomService.getRoomsByCampus(campusName);

        Stream<Room> filteredRooms = rooms.stream();

        if (minNumberOfSeats != null) {
            filteredRooms = filteredRooms.filter(r -> r.getCapacity() >= minNumberOfSeats);
        }

        return filteredRooms
                .map(DTOConverter::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{roomId}")
    public RoomDTO getRoomById(@PathVariable String campusName, @PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);

        verifyRoomExistsInCampus(room, campusName);

        return DTOConverter.convertToRoomDTO(room);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO addRoomToCampus(@PathVariable String campusName, @RequestBody RoomDTO roomDTO) {
        Room room = DTOConverter.convertDTOToRoom(roomDTO);
        Room saved = roomService.addRoomToCampus(campusName, room);
        return DTOConverter.convertToRoomDTO(saved);
    }

    @PutMapping("/{roomId}")
    public RoomDTO updateRoom(@PathVariable String campusName, @PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        if (roomDTO.getId() == null || !roomDTO.getId().equals(roomId)) {
            throw new RoomNotFoundException("Room Id is not correct");
        }

        Room room = DTOConverter.convertDTOToRoom(roomDTO);
        room.setCampus(new Campus());
        room.getCampus().setName(campusName);

        Room updated = roomService.updateRoom(roomId, room);
        return DTOConverter.convertToRoomDTO(updated);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable String campusName, @PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        verifyRoomExistsInCampus(room, campusName);
        roomService.deleteRoom(roomId);
    }

    private void verifyRoomExistsInCampus(Room room, String campusName) {
        if (!room.getCampus().getName().equals(campusName)) {
            throw new RoomCampusMismatchException("Room does not belong to campus '" + campusName + "'");
        }
    }
}

