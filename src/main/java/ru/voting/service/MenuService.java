package ru.voting.service;

import ru.voting.model.Menu;
import ru.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu update(Menu menu, int restaurantId) throws NotFoundException;

    Menu create(Menu menu, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Menu get(int id, int restaurantId) throws NotFoundException;

    List<Menu> getAll(int restaurantId);

    List<Menu> getByDate(LocalDate date);
}
