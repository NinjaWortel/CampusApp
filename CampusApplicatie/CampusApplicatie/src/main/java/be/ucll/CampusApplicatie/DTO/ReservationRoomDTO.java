package be.ucll.CampusApplicatie.DTO;

public class ReservationRoomDTO {

    private Long id;
    private Long reservationId;
    private Long roomId;

    public ReservationRoomDTO() {
    }

    public ReservationRoomDTO(Long id, Long reservationId, Long roomId) {
        this.id = id;
        this.reservationId = reservationId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
