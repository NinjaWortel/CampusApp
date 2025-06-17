package be.ucll.CampusApplicatie;

import be.ucll.CampusApplicatie.entity.*;
import be.ucll.CampusApplicatie.repository.*;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.*;
import be.ucll.CampusApplicatie.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CampusServiceTesten {

    @Mock
    private CampusRepository campusRepository;

    @InjectMocks
    private CampusService campusService;

    @Test
    void addCampusWhenValidPassed() {
        Campus campus = new Campus("CAMPUS", "Campusstraat 1", 50, new ArrayList<>());

        when(campusRepository.findById("CAMPUS")).thenReturn(Optional.empty());
        when(campusRepository.save(campus)).thenReturn(campus);

        Campus result = campusService.addCampus(campus);

        assertEquals("CAMPUS", result.getName());
        verify(campusRepository).save(campus);
    }

    @Test
    void addCampusWhenAlreadySavedThrowException() {
        Campus campus = new Campus("CAMPUS", "Campusstraat 1", 50, new ArrayList<>());

        when(campusRepository.findById("CAMPUS")).thenReturn(Optional.of(campus));

        assertThrows(CampusAlreadyExistsException.class, () -> campusService.addCampus(campus));
    }

    @Test
    void getCampusWhenExistsPassed() {
        Campus campus = new Campus("CAMPUS", "Campusstraat 1", 50, new ArrayList<>());
        when(campusRepository.findById("CAMPUS")).thenReturn(Optional.of(campus));

        Campus resultTest = campusService.getCampusByName("CAMPUS");

        assertEquals("CAMPUS", resultTest.getName());
    }
    @Test
    void getCampusWhenNotFoundThrowException() {
        when(campusRepository.findById(" ")).thenReturn(Optional.empty());

        assertThrows(CampusNotFoundException.class, () -> campusService.getCampusByName(" "));
    }

    @Test
    void updateCampusWhenCampusExistsPassed() {
        Campus campusVersion1 = new Campus("CAMPUS", "Campusstraat 1", 50, new ArrayList<>());
        Campus campusVersion2 = new Campus("CAMPUS", "Campusstraat 2", 100, new ArrayList<>());

        when(campusRepository.findById("CAMPUS")).thenReturn(Optional.of(campusVersion1));
        when(campusRepository.save(campusVersion1)).thenReturn(campusVersion1);

        Campus result = campusService.updateCampus(campusVersion2);

        assertEquals("Campusstraat 2", result.getAddress());
        assertEquals(100, result.getParkingSpaces());
    }
}
