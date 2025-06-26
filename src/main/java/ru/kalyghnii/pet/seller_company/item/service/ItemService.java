package ru.kalyghnii.pet.seller_company.item.service;

import ru.kalyghnii.pet.seller_company.item.model.Item;

public interface ItemService {
    Item getById(Long id);
    void save(Item item);
    void update(Item item);
    void delete(Long id);
}
