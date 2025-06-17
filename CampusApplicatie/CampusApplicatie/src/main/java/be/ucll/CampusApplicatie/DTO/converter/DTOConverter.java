package be.ucll.CampusApplicatie.DTO.converter;

import be.ucll.CampusApplicatie.DTO.*;
import be.ucll.CampusApplicatie.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DTOConverter {

    // CAMPUS
    public static CampusDTO convertToCampusDTO(Campus campus) {
        if (campus == null) {
            return null;
        }

        CampusDTO campusDTO = new CampusDTO();
        campusDTO.setName(campus.getName());
        campusDTO.setAddress(campus.getAddress());
        campusDTO.setParkingSpaces(campus.getParkingSpaces());

        if (campus.getRooms() != null) {
            List<RoomDTO> roomDTOs = campus.getRooms().stream()
                    .map(DTOConverter::convertToRoomDTO)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            campusDTO.setRooms(roomDTOs);
        }
        return campusDTO;
    }
    public static Campus convertDTOToCampus(CampusDTO campusDTO) {
        if (campusDTO == null) {
            return null;
        }

        Campus campus = new Campus();
        campus.setName(campusDTO.getName());
        campus.setAddress(campusDTO.getAddress());
        campus.setParkingSpaces(campusDTO.getParkingSpaces());

        if (campusDTO.getRooms() != null) {
            List<Room> rooms = campusDTO.getRooms().stream()
                    .map(DTOConverter::convertDTOToRoom)
                    .filter(Objects::nonNull)
                    .peek(room -> room.setCampus(campus))
                    .collect(Collectors.toList());
            campus.setRooms(rooms);
        }

        return campus;
    }

    // ROOM
    public static RoomDTO convertToRoomDTO(Room room) {
        if (room == null) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setType(room.getType());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setFirstName(room.getFirstName());
        roomDTO.setLastName(room.getLastName());
        roomDTO.setFloor(room.getFloor());

        if (room.getCampus() != null) {
            roomDTO.setCampusName(room.getCampus().getName());
        }
        return roomDTO;
    }
    public static Room convertDTOToRoom(RoomDTO roomDTO) {
        if (roomDTO == null) {
            return null;
        }

        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setName(roomDTO.getName());
        room.setType(roomDTO.getType());
        room.setCapacity(roomDTO.getCapacity());
        room.setFirstName(roomDTO.getFirstName());
        room.setLastName(roomDTO.getLastName());
        room.setFloor(roomDTO.getFloor());
        return room;
    }

    // USER
    public static UserDTO convertToUserDTO (User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;
    }
    public static User convertDTOToUser (UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setBirthdate(userDTO.getBirthdate());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    // RESERVATION

    public static ReservationDTO convertToReservationDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setComments(reservation.getComments());

        if (reservation.getUser() != null) {
            reservationDTO.setUserId(reservation.getUser().getId());
        }

        if (reservation.getReservationRooms() != null) {
            List<ReservationRoomDTO> roomDTOs = reservation.getReservationRooms().stream()
                    .map(DTOConverter::convertToReservationRoomDTO)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            reservationDTO.setRooms(roomDTOs);
        }

        return reservationDTO;
    }

    public static ReservationRoomDTO convertToReservationRoomDTO(ReservationRoom reservationRoom) {
        if (reservationRoom == null) {
            return null;
        }


        ReservationRoomDTO reservationRoomDTO = new ReservationRoomDTO();
        reservationRoomDTO.setId(reservationRoom.getId());

        if (reservationRoom.getReservation() != null) {
            reservationRoomDTO.setReservationId(reservationRoom.getReservation().getId());
        }

        if (reservationRoom.getRoom() != null) {
            reservationRoomDTO.setRoomId(reservationRoom.getRoom().getId());
        }

        return reservationRoomDTO;
    }

    public static Reservation convertDTOToReservation(ReservationDTO reservationDTO, User user, List<Room> rooms) {
        if (reservationDTO == null) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setComments(reservationDTO.getComments());
        reservation.setUser(user);

        List<ReservationRoom> reservationRooms = rooms.stream()
                .map(room -> {
                    ReservationRoom reservationRoom = new ReservationRoom();
                    reservationRoom.setReservation(reservation);
                    reservationRoom.setRoom(room);
                    return reservationRoom;
                })
                .collect(Collectors.toList());

        reservation.setReservationRooms(reservationRooms);

        return reservation;
    }

    public static ReservationRoom convertDTOToReservationRoom(ReservationRoomDTO reservationRoomDTO, Reservation reservation, Room room) {
        if (reservationRoomDTO == null || reservation == null || room == null) {
            return null;
        }

        ReservationRoom reservationRoom = new ReservationRoom();
        reservationRoom.setId(reservationRoomDTO.getId());
        reservationRoom.setReservation(reservation);
        reservationRoom.setRoom(room);

        return reservationRoom;
    }

}
