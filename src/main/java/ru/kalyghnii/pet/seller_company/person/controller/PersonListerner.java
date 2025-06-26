package ru.kalyghnii.pet.seller_company.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ru.kalyghnii.pet.seller_company.util.Constant;

public class PersonListerner implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper objectMapper = new ObjectMapper();
        ServletContext sc = sce.getServletContext();
        sc.setAttribute(Constant.MAPPER, objectMapper);

    }
}
