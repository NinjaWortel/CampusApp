package be.ucll.CampusApplicatie.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"campus_name", "name"}))
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private int capacity;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private int floor;

    @ManyToOne
    @JoinColumn(name="campus_name")
    private Campus campus;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReservationRoom> reservationRooms = new ArrayList<>();

    public Room() {

    }

    public Room(int capacity, String firstName, int floor, String lastName, String name, String type) {
        this.capacity = capacity;
        this.firstName = firstName;
        this.floor = floor;
        this.lastName = lastName;
        this.name = name;
        this.type = type;
    }

    public Room(int capacity, String firstName, int floor, Long id, String lastName, String name, String type) {
        this.capacity = capacity;
        this.firstName = firstName;
        this.floor = floor;
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.type = type;
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

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
