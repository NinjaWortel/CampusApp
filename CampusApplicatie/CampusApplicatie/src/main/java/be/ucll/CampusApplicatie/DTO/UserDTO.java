package be.ucll.CampusApplicatie.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;

    private List<ReservationDTO> reservations = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, LocalDate birthdate, String email, List<ReservationDTO> reservations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
}
