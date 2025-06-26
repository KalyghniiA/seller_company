package ru.kalyghnii.pet.seller_company.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kalyghnii.pet.seller_company.exception.EmptyBodyException;
import ru.kalyghnii.pet.seller_company.exception.EmptyParameterException;
import ru.kalyghnii.pet.seller_company.exception.EmptyResultException;
import ru.kalyghnii.pet.seller_company.exception.ValidationBodyException;
import ru.kalyghnii.pet.seller_company.person.model.Person;
import ru.kalyghnii.pet.seller_company.util.Constant;
import ru.kalyghnii.pet.seller_company.util.StatusCode;

import java.io.*;
import java.util.Map;

public class PersonFilter extends HttpFilter {
    ObjectMapper mapper;

    @Override
    public void init() {
        mapper = (ObjectMapper) getServletContext().getAttribute(Constant.MAPPER);
    }

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String method = servletRequest.getMethod();
        switch (method) {
            case "POST":
                try {
                    processingPost(servletRequest);
                    filterChain.doFilter(servletRequest, servletResponse);
                    processingResponse(servletResponse, StatusCode.CREATED.getTitle(), "");
                } catch (EmptyBodyException e) {
                    String json = String.format("{\"error\": \"%s\"}", e.getMessage());
                    processingResponse(servletResponse, StatusCode.BAD_REQUEST.getTitle(), json);
                }
                break;
            case "GET":
                try {
                    processingGet(servletRequest);
                    filterChain.doFilter(servletRequest, servletResponse);
                    String json = mapper.writeValueAsString(getServletContext().getAttribute(Constant.PERSON_ELEMENT));
                    processingResponse(servletResponse, StatusCode.OK.getTitle(), json);
                } catch (EmptyParameterException | EmptyResultException e) {
                    String json = String.format("{\"error\": \"%s\"}", e.getMessage());
                    processingResponse(servletResponse, StatusCode.BAD_REQUEST.getTitle(), json);
                    return;
                }
                break;
            case "DELETE":
                try {
                    processingDelete(servletRequest);
                    filterChain.doFilter(servletRequest, servletResponse);
                    processingResponse(servletResponse, StatusCode.OK.getTitle(), "");
                } catch (EmptyParameterException | EmptyResultException e) {
                    processingResponse(servletResponse, StatusCode.NOT_ACCEPTABLE.getTitle(), e.getMessage());
                    return;
                }
                break;
            case "UPDATE":
                try {
                    processingUpdate(servletRequest);
                    filterChain.doFilter(servletRequest, servletResponse);
                    processingResponse(servletResponse, StatusCode.OK.getTitle(), "");
                } catch (EmptyBodyException | EmptyResultException e) {
                    String json = String.format("{\"error\": \"%s\"}", e.getMessage());
                    processingResponse(servletResponse, StatusCode.NOT_ACCEPTABLE.getTitle(), json);
                } catch (ValidationBodyException e) {
                    processingResponse(servletResponse, StatusCode.BAD_REQUEST.getTitle(), e.getMessage());
                }
        }
    }

    private void processingPost(HttpServletRequest servletRequest) throws IOException {
        try (BufferedReader reader = servletRequest.getReader()) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            if (json != null && !json.trim().isEmpty()) {
                Person person = mapper.readValue(json, Person.class);
                getServletContext().setAttribute(Constant.PERSON_ELEMENT, person);
            } else {
                throw new EmptyBodyException("Тело запроса отсутствует");
            }
        }
    }

    private void processingGet(HttpServletRequest servletRequest) {
        Map<String, String[]> params = servletRequest.getParameterMap();
        if (!params.containsKey(Constant.REQUEST_PARAM_KEY_ID)) {
            throw new EmptyParameterException("Параметр id отсутствует");
        }

        getServletContext().setAttribute(Constant.REQUEST_PARAM_VALUE_ID, params.get(Constant.REQUEST_PARAM_KEY_ID));
    }

    private void processingDelete(HttpServletRequest servletRequest) {
        Map<String, String[]> params = servletRequest.getParameterMap();
        if (!params.containsKey(Constant.REQUEST_PARAM_KEY_ID)
                || params.get(Constant.REQUEST_PARAM_KEY_ID).length < 1) {
            throw new EmptyParameterException("Параметр id отсутствует, либо не передан ни один id");
        }

        getServletContext().setAttribute(Constant.REQUEST_PARAM_VALUE_ID, params.get(Constant.REQUEST_PARAM_KEY_ID)[0]);
    }

    private void processingUpdate(HttpServletRequest servletRequest) throws IOException {
        try (BufferedReader reader = servletRequest.getReader()) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            if (json != null && !json.trim().isEmpty()) {
                Person person = mapper.readValue(json, Person.class);
                if (person.getPersonId() == null) {
                    throw new ValidationBodyException("Поле id не может быть пустым");
                }
                getServletContext().setAttribute(Constant.PERSON_ELEMENT, person);
            } else {
                throw new EmptyBodyException("Тело запроса отсутствует");
            }
        }
    }

    private void processingResponse(HttpServletResponse servletResponse, int statusCode, String body) throws IOException {
        servletResponse.setStatus(statusCode);
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json");
        try (PrintWriter writer = servletResponse.getWriter()) {
            writer.write(body);
        }
    }
}
