package ru.kalyghnii.pet.seller_company.item.dao;

import ru.kalyghnii.pet.seller_company.item.model.Item;

public interface ItemRepository {
    Item getById(Long id);
    void save(Item item);
    void update(Item item);
    void delete(Long id);
}
