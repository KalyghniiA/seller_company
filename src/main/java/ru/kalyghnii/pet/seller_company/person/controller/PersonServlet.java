package ru.kalyghnii.pet.seller_company.person.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kalyghnii.pet.seller_company.exception.EmptyResultException;
import ru.kalyghnii.pet.seller_company.person.model.Person;
import ru.kalyghnii.pet.seller_company.person.service.PersonService;
import ru.kalyghnii.pet.seller_company.util.Constant;

import java.util.Arrays;
import java.util.List;

public class PersonServlet extends HttpServlet {
    private PersonService service;

    @Override
    public void init() {
        service = (PersonService) getServletContext().getAttribute(Constant.PERSON_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String[] idArr = (String[]) getServletContext().getAttribute(Constant.REQUEST_PARAM_VALUE_ID);

        if (idArr.length == 1) {
            Person person = service.getById(Long.parseLong(idArr[0]));
            if (person == null) {
                throw new EmptyResultException(String.format("Пользователя с id %s нет в базе", idArr[0]));
            }
            getServletContext().setAttribute(Constant.RESPONSE_ELEMENT, person);
        } else {
            List<Long> personsId = Arrays.stream(idArr).map(Long::parseLong).toList();
            List<Person> persons = service.getByIds(personsId);
            getServletContext().setAttribute(Constant.RESPONSE_ELEMENT, persons);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) getServletContext().getAttribute(Constant.PERSON_ELEMENT);
        service.save(person);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Person newPerson = (Person) getServletContext().getAttribute(Constant.PERSON_ELEMENT);
        if (newPerson.getPersonId() == null || service.getById(newPerson.getPersonId()) == null) {
            throw new EmptyResultException(String.format("Пользователя с id %s нет в базе", newPerson.getPersonId()));
        }
        service.update(newPerson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = (Long) getServletContext().getAttribute(Constant.REQUEST_PARAM_KEY_ID);
        if (service.getById(id) == null) {
            throw new EmptyResultException(String.format("Пользователя с id %s нет в базе", id));
        }
        service.delete(id);
    }
}
