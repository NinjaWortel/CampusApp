package be.ucll.CampusApplicatie;

import be.ucll.CampusApplicatie.DTO.*;
import be.ucll.CampusApplicatie.entity.*;
import be.ucll.CampusApplicatie.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CampusControllerTesten {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addCampusSuccess() throws Exception {
        Campus campus = new Campus("CAMPUS", "Campusstraat 1", 100, new ArrayList<>());

        mockMvc.perform(post("/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campus)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name")
                        .value("CAMPUS"))
                .andExpect(jsonPath("$.address")
                        .value("Campusstraat 1"))
                .andExpect(jsonPath("$.parkingSpaces")
                        .value(100));

        assertThat(campusRepository.findByName("CAMPUS")).isPresent();
    }

    @Test
    void addCampusAlreadyExistsReturnConflict() throws Exception {
        CampusDTO campusDTO = new CampusDTO("TestCampus", "Campusstraat 1", 100, new ArrayList<>());
        mockMvc.perform(post("/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campusDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campusDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void testDeleteCampus_Success() throws Exception {
        CampusDTO campusDTO = new CampusDTO("DELETE_Campus", "Campusstraat", 100, new ArrayList<>());
        mockMvc.perform(post("/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campusDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/campus/DELETE_Campus"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateCampusShouldReturnUpdatedCampus() throws Exception {
        CampusDTO campusDTO = new CampusDTO("Campus", "Campusstraat", 50, new ArrayList<>());
        mockMvc.perform(post("/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campusDTO)))
                .andExpect(status().isCreated());

        CampusDTO updatedDTO = new CampusDTO("Campus", "Nieuwe Campusstraat", 100, new ArrayList<>());
        mockMvc.perform(put("/campus/Campus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address")
                        .value("Nieuwe Campusstraat"))
                .andExpect(jsonPath("$.parkingSpaces")
                        .value(100));
    }
}
