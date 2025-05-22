package ru.kalyghnii.pet.seller_company.person.service;

import ru.kalyghnii.pet.seller_company.person.dao.PersonRepository;
import ru.kalyghnii.pet.seller_company.person.model.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getById(long id) {
        return personRepository.findById(id);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getByRole(String role) {
        return personRepository.getByRole(role);
    }

    @Override
    public List<Person> getByOrganization(Long organizationId) {
        return List.of();
    }

    @Override
    public List<Person> getByIds(List<Long> ids) {
        return personRepository.getByIds(ids);
    }
}
