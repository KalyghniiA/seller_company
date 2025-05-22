package ru.kalyghnii.pet.seller_company;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ru.kalyghnii.pet.seller_company.util.Constant;

public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
        sc.setAttribute(Constant.ENTITY_MANAGER_FACTORY, emf);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        EntityManagerFactory emf = (EntityManagerFactory) sc.getAttribute(Constant.ENTITY_MANAGER_FACTORY);
        emf.close();
    }
}
