package com.workintech.s17d2.rest;

import jakarta.annotation.PostConstruct;
import com.workintech.model.Developer;
import com.workintech.model.JuniorDeveloper;
import com.workintech.model.MidDeveloper;
import com.workintech.model.SeniorDeveloper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.workintech.tax.Taxable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    @Autowired
    public DeveloperController(Taxable taxable) {
    }

    public Map<Integer, Developer> developers;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
        System.out.println("Developers map has been initialized: " + developers);
    }


    @GetMapping
    public List<Developer> getDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id) {
        Developer developer = developers.get(id);
        return developer != null ? developer : null;
    }

    @PostMapping
    public ResponseEntity<String> addDeveloper(@RequestBody Developer developer) {
        if (developer.getExperience() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Deneyim seviyesi gereklidir!");
        }

        switch (developer.getExperience()) {
            case JUNIOR:
                developer = new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            case MID:
                developer = new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            case SENIOR:
                developer = new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            default:
                return ResponseEntity.badRequest().body("Geçersiz deneyim seviyesi!");
        }

        developers.put(developer.getId(), developer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Developer added successfully!");
    }




    @PutMapping("/{id}")
    public ResponseEntity<String> updateDeveloper(@PathVariable int id, @RequestBody Developer updatedDeveloper) {
        Developer existingDeveloper = developers.get(id);

        if (existingDeveloper != null) {
            existingDeveloper.setName(updatedDeveloper.getName());
            existingDeveloper.setSalary(updatedDeveloper.getSalary());
            existingDeveloper.setExperience(updatedDeveloper.getExperience());
            return ResponseEntity.ok("Developer updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper(@PathVariable int id) {
        Developer removedDeveloper = developers.remove(id);

        if (removedDeveloper != null) {
            return ResponseEntity.ok("Developer with id " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer with id " + id + " not found!");
        }
    }
}
