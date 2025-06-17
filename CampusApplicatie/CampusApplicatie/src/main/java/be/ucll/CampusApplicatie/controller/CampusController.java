package be.ucll.CampusApplicatie.controller;

import be.ucll.CampusApplicatie.DTO.CampusDTO;
import be.ucll.CampusApplicatie.DTO.converter.DTOConverter;
import be.ucll.CampusApplicatie.entity.Campus;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusBadRequestException;
import be.ucll.CampusApplicatie.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @GetMapping
    public List<CampusDTO> getAllCampuses() {
        return campusService.getAllCampuses().stream()
                .map(DTOConverter::convertToCampusDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{campusName}")
    public CampusDTO getCampusByName(@PathVariable String campusName) {
        Campus campus = campusService.getCampusByName(campusName);
        return DTOConverter.convertToCampusDTO(campus);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampusDTO addCampus(@RequestBody CampusDTO campusDTO) {
        Campus campus = DTOConverter.convertDTOToCampus(campusDTO);
        Campus saved = campusService.addCampus(campus);
        return DTOConverter.convertToCampusDTO(saved);
    }

    @PutMapping("/{campusName}")
    public CampusDTO updateCampus(@PathVariable String campusName, @RequestBody CampusDTO campusDTO) {
        if (!campusName.equals(campusDTO.getName())) {
            throw new CampusBadRequestException("Campus name not known");
        }
        Campus campus = DTOConverter.convertDTOToCampus(campusDTO);
        Campus updated = campusService.updateCampus(campus);
        return DTOConverter.convertToCampusDTO(updated);
    }

    @DeleteMapping("/{campusName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampus(@PathVariable String campusName) {
        campusService.deleteCampusByName(campusName);
    }
}
