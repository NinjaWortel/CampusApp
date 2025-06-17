package be.ucll.CampusApplicatie.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationRoom> reservationRooms = new ArrayList<>();

    public Reservation() {
    }

    public Reservation(String comments, LocalDateTime endTime, Long id, List<ReservationRoom> reservationRooms, LocalDateTime startTime, User user) {
        this.comments = comments;
        this.endTime = endTime;
        this.id = id;
        this.reservationRooms = reservationRooms;
        this.startTime = startTime;
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReservationRoom> getReservationRooms() {
        return reservationRooms;
    }

    public void setReservationRooms(List<ReservationRoom> reservationRooms) {
        this.reservationRooms = reservationRooms;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
