package be.ucll.CampusApplicatie.service;

import be.ucll.CampusApplicatie.entity.Campus;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusAlreadyExistsException;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusBadRequestException;
import be.ucll.CampusApplicatie.repository.Exception.CampusExceptions.CampusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.CampusApplicatie.repository.CampusRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;

    public Campus addCampus(Campus campus) {
        validateCampus(campus);

        campusRepository.findById(campus.getName())
                .ifPresent(existing -> {
                    throw new CampusAlreadyExistsException(campus.getName());
                });
        return campusRepository.save(campus);
    }

    public List<Campus> getAllCampuses() {
        return campusRepository.findAll();
    }

    public Campus getCampusByName(String name) {
        return campusRepository.findById(name)
                .orElseThrow(() -> new CampusNotFoundException(name));
    }

    public Campus updateCampus(Campus campus) {
        validateCampus(campus);

        Campus existingCampus = campusRepository.findById(campus.getName())
                .orElseThrow(() -> new CampusNotFoundException(campus.getName()));
        existingCampus.setAddress(campus.getAddress());
        existingCampus.setParkingSpaces(campus.getParkingSpaces());
        return campusRepository.save(existingCampus);
    }

    public void deleteCampusByName(String name) {
        Campus existingCampus = campusRepository.findById(name)
                .orElseThrow(() -> new CampusNotFoundException(name));
        campusRepository.delete(existingCampus);
    }

    private void validateCampus(Campus campus) {
        if (campus == null) {
            throw new CampusBadRequestException("Campus cannot be null");
        }
        if (campus.getName() == null || campus.getName().isBlank()) {
            throw new CampusBadRequestException("Campus name is mandatory");
        }
        if (campus.getAddress() == null || campus.getAddress().isBlank()) {
            throw new CampusBadRequestException("Campus address is mandatory");
        }
        if (campus.getParkingSpaces() < 0) {
            throw new CampusBadRequestException("Parking spaces cannot be negative");
        }
    }
}
