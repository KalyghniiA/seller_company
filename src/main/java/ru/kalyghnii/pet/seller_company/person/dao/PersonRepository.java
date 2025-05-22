package ru.kalyghnii.pet.seller_company.person.dao;

import ru.kalyghnii.pet.seller_company.person.model.Person;

import java.util.List;

public interface PersonRepository {
    Person findById(long id);

    List<Person> findAll();

    void save(Person person);

    void update(Person person);

    void deleteById(long id);

    List<Person> getByRole(String role);

    List<Person> getByOrganization(Long organizationId);

    List<Person> getByIds(List<Long> ids);
}
