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
        String id = ((String[]) getServletContext().getAttribute(Constant.REQUEST_PARAM_VALUE_ID))[0];
        Person person = service.getById(Long.parseLong(id));
        if (person == null) {
            throw new EmptyResultException(String.format("Пользователя с id %s нет в базе", id));
        }
        getServletContext().setAttribute(Constant.RESPONSE_ELEMENT, person);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Person person = (Person) getServletContext().getAttribute(Constant.PERSON_ELEMENT);
        service.save(person);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Person newPerson = (Person) getServletContext().getAttribute(Constant.PERSON_ELEMENT);
        service.update(newPerson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = (Long) getServletContext().getAttribute(Constant.REQUEST_PARAM_KEY_ID);
        service.delete(id);
    }
}
