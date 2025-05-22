package ru.kalyghnii.pet.seller_company.person.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import ru.kalyghnii.pet.seller_company.person.model.Person;

import java.util.List;

public class PersonRepositoryJpa implements PersonRepository {
    EntityManagerFactory emf;

    public PersonRepositoryJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person findById(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            Person person =  em.find(Person.class, id);
            em.detach(person);
            et.commit();
            return person;
        }

    }

    @Override
    public List<Person> findAll() {
//        EntityTransaction et = entityManager.getTransaction();
//        List<Person> persons = entityManager.createNativeQuery("select * from persons", Person.class)
//                .getResultList();
        return List.of();
    }

    @Override
    public void save(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(person);
            et.commit();

        }
    }

    @Override
    public void update(Person newPerson) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            Person person = em.find(Person.class, newPerson.getPersonId());
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            person.setEmail(newPerson.getEmail());
            person.setPhone(newPerson.getPhone());
            person.setRoleId(newPerson.getRoleId());
            et.commit();
        }
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Person> getByRole(String role) {
        return List.of();
    }

    @Override
    public List<Person> getByOrganization(Long organizationId) {
        return List.of();
    }

    @Override
    public List<Person> getByIds(List<Long> ids) {
        return List.of();
    }
}
