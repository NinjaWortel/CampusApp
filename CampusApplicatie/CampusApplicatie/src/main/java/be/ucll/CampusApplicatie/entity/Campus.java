package be.ucll.CampusApplicatie.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Campus {

    @Id
    private String name;
    private String address;
    @Column(name="parking_spaces")
    private int parkingSpaces;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();

    public Campus() {
    }

    public Campus(String name, String address, int parkingSpaces, List<Room> rooms) {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
