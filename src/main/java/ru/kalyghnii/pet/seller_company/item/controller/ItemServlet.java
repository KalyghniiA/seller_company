package ru.kalyghnii.pet.seller_company.item.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kalyghnii.pet.seller_company.exception.EmptyResultException;
import ru.kalyghnii.pet.seller_company.item.model.Item;
import ru.kalyghnii.pet.seller_company.item.service.ItemService;
import ru.kalyghnii.pet.seller_company.util.Constant;


public class ItemServlet extends HttpServlet {
    ItemService itemService;

    @Override
    public void init() {
        itemService = (ItemService) getServletContext().getAttribute(Constant.ITEM_SERVICE);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Item item = (Item) request.getSession().getAttribute(Constant.ITEM_ELEMENT);
        itemService.save(item);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Long id = (Long) getServletContext().getAttribute(Constant.REQUEST_PARAM_VALUE_ID);
        Item item = itemService.getById(id);
        getServletContext().setAttribute(Constant.RESPONSE_ELEMENT, item);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        Item newItem = (Item) getServletContext().getAttribute(Constant.ITEM_ELEMENT);
        itemService.update(newItem);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = (Long) getServletContext().getAttribute(Constant.REQUEST_PARAM_VALUE_ID);
        itemService.delete(id);
    }
}

