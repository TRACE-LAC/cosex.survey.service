package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Preconditions;
import com.monkeypox.survey.service.business.facade.IPersonService;
import com.monkeypox.survey.service.model.person.Person;
import com.monkeypox.survey.service.model.person.PersonRepository;
import com.monkeypox.survey.service.util.JsonUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService implements IPersonService{
    
    @Autowired
    private PersonRepository personRepository;

    /**
      * This method gets the persons
      * @return persons
      */
    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        try {
            persons = personRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all persons", e);
        }
        return persons;
    }

    /**
     * This method gets a specific person by id
     * @param id: person id
     * @return person
     */
    @Override
    public Person findById(Long id) {
        Person person = null;
        try {
            person = personRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query person with id: " + id, e);
        }
        return person;
    }

    /**
     * This method creates a new person
     * @param person
     * @return person
     */
    @Override
    public Person create(Person person) {
        Person newPerson = null;
        try {
            Preconditions.checkNotNull(person);
            newPerson = personRepository.save(person);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error person creating: " + JsonUtility.objectToJson(person), e);
        }
        return newPerson;
    }
    
    /**
     * This method updates a person
     * @param person
     */
    @Override
    public void update(Person person) {
        try {
            Preconditions.checkNotNull(person);

            Person pUpdate = personRepository.findById(person.getId()).orElse(null);

            Preconditions.checkNotNull(pUpdate);
           
            pUpdate.setAge(person.getAge());
            pUpdate.setCity(person.getCity());
            pUpdate.setDepartment(person.getDepartment());
            pUpdate.setGender(person.getGender());
            pUpdate.setGenderIdentity(person.getGenderIdentity());
            pUpdate.setSexGender(person.getSexGender());
            pUpdate.setSexualOrientation(person.getSexualOrientation());
            pUpdate.setSocialSecurity(person.getSocialSecurity());

            personRepository.save(pUpdate);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error person update: " + JsonUtility.objectToJson(person), e);
        }
    }
    
    /**
     * This method deletes a person by id
     * @param id: person id
     */
    @Override
    public void delete(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error person deletion with id: " + id, e);
        } 
    }
}
