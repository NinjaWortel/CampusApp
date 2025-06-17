package be.ucll.CampusApplicatie.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO {

    private Long id;
    private String name;
    private String type;
    private int capacity;
    private String firstName;
    private String lastName;
    private int floor;
    private String campusName;

    private List<ReservationRoomDTO> reservations = new ArrayList<>();

    public RoomDTO() {
    }

    public RoomDTO(Long id, String name, String type, int capacity, String firstName, String lastName, int floor, String campusName, List<ReservationRoomDTO> reservations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.floor = floor;
        this.campusName = campusName;
        this.reservations = reservations;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public List<ReservationRoomDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationRoomDTO> reservations) {
        this.reservations = reservations;
    }

}
