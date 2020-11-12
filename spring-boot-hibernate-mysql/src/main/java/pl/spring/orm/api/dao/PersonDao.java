package pl.spring.orm.api.dao;

import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.orm.api.model.Person;

import java.util.List;
import java.util.Optional;

import static java.lang.reflect.Array.get;

@Repository
@Transactional
public class PersonDao {

    @Autowired
    private SessionFactory factory;

    public void savePerson(Person person){
        getSession().save(person);
    }

    public List<Person> getPersons() {
        return getSession().createCriteria(Person.class).list();
    }

    public void deletePerson(Integer id) {
        try {
            for (Person p : getPersons()) {
                if (p.getId() == id)
                {
                    getSession().delete(p);
                }
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    public void updatePerson(Integer id, Person person) {
        try {
            for (Person p : getPersons()) {
                if (p.getId() == id)
                {
                    p.setName(person.getName());
                    p.setDob(person.getDob());
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }


    private Session getSession(){
        Session session = factory.getCurrentSession();
        if (session==null){
            session=factory.openSession();
        }
        return session;
    }

}
