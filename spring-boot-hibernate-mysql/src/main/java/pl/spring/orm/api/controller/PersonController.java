package pl.spring.orm.api.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;
import pl.spring.orm.api.dao.PersonDao;
import pl.spring.orm.api.model.Person;

import java.util.List;

@RestController
@RequestMapping("/spring-boot-orm")
public class PersonController {

    @Autowired
    private PersonDao dao;

    @PostMapping("/savePerson")
    public String save(@RequestBody Person person) {
        dao.savePerson(person);
        return "success";
    }

    @GetMapping("/getAll")
    public List<Person> getAllPersons() {
        return dao.getPersons();
    }

    @DeleteMapping("/deletePerson/{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        dao.deletePerson(id);
        return "success";
    }
    @PutMapping("/updatePerson/{id}")
    public String update(@PathVariable(value = "id", required = false) Integer id, @RequestBody Person person) {
        try {
            dao.updatePerson(id, person);
            return "success";
        }
        catch (Exception e) {
            System.out.println(e);
            return "bad";
        }
    }


}
