package ru.kalyghnii.pet.seller_company.person.service;

import ru.kalyghnii.pet.seller_company.person.model.Person;


import java.util.List;

public interface PersonServce {
    Person getById(long id);

    void save(Person person);

    void update(Person person);

    void delete(long id);

    List<Person> getByRole(String role);

    List<Person> getByOrganization(Long organizationId);

    List<Person> getByIds(List<Long> ids);
}
