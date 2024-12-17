package rest;

import com.workintech.s17d2.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.workintech.s17d2.tax.Taxable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {

    private final Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    public Map<Integer, Developer> developers;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    @GetMapping("/developers")
    public List getDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/developers/{id}")
    public Developer getDeveloperbyId(@PathVariable int id) {

        Developer developer = developers.get(id);

        if (developer != null) {
            return developer;
        } else {
            return null;
        }

    }

    @PostMapping("/developers")
    public String addDeveloper(@RequestParam int id, @RequestParam String name, @RequestParam double salary, @RequestParam Experience experience) {
        Developer developer;

        switch (experience) {
            case JUNIOR:
                developer = new JuniorDeveloper(id, name, salary);
                break;
            case MID:
                developer = new MidDeveloper(id, name, salary);
                break;
            case SENIOR:
                developer = new SeniorDeveloper(id, name, salary);
                break;
            default:
                return "Invalid experience level!";
        }
        developers.put(id, developer);
        return "Developer added successfully!";
    }

    @PutMapping("/{id}")
    public String updateDeveloper(@PathVariable int id, @RequestBody Developer updatedDeveloper) {

        Developer existingDeveloper = developers.get(id);

        if (existingDeveloper != null) {

            existingDeveloper.setName(updatedDeveloper.getName());
            existingDeveloper.setSalary(updatedDeveloper.getSalary());
            existingDeveloper.setExperience(updatedDeveloper.getExperience());

            return "Developer updated successfully!";
        } else {
            return "Developer not found!";
        }
    }

    @DeleteMapping("/developers/{id}")
    public String deleteDeveloper(@PathVariable int id) {

        Developer removedDeveloper = developers.remove(id);

        if (removedDeveloper != null) {
            return "Developer with id " + id + " deleted successfully!";
        } else {
            return "Developer with id " + id + " not found!";
        }
    }
}
