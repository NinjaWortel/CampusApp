package be.ucll.CampusApplicatie.DTO;

import java.util.ArrayList;
import java.util.List;

public class CampusDTO {

    private String name;
    private String address;
    private int parkingSpaces;

    private List<RoomDTO> rooms = new ArrayList<>();

    public CampusDTO() {
    }

    public CampusDTO(String name, String address, int parkingSpaces, List<RoomDTO> rooms) {
        this.name = name;
        this.address = address;
        this.parkingSpaces = parkingSpaces;
        this.rooms = rooms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

}
