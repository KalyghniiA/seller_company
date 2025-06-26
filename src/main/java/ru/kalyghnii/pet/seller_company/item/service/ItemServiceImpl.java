package ru.kalyghnii.pet.seller_company.item.service;

import ru.kalyghnii.pet.seller_company.exception.EmptyResultException;
import ru.kalyghnii.pet.seller_company.item.dao.ItemRepository;
import ru.kalyghnii.pet.seller_company.item.model.Item;

public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getById(Long id) {
        Item item = itemRepository.getById(id);
        if (item == null) {
            throw new EmptyResultException(String.format("Товара с id %s нет в базе", id));
        }

        return item;
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void update(Item item) {
        Item oldItem = getById(item.getItemId());
        itemRepository.save(oldItem);
    }

    @Override
    public void delete(Long id) {

    }
}
