package be.ucll.CampusApplicatie.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long userId;
    private String comments;

    private List<ReservationRoomDTO> reservationRooms;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Long userId, String comments, List<ReservationRoomDTO> reservationRooms) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.comments = comments;
        this.reservationRooms = reservationRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<ReservationRoomDTO> getRooms() {
        return reservationRooms;
    }

    public void setRooms(List<ReservationRoomDTO> reservationRooms) {
        this.reservationRooms = reservationRooms;
    }
}
