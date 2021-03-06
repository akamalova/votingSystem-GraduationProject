package ru.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.voting.model.Menu;
import ru.voting.repository.menu.MenuRepository;
import ru.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.voting.util.ValidationUtil.checkNotFound;
import static ru.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository repository;

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        Assert.notNull(menu, "menu must not be null");
        return checkNotFoundWithId(repository.save(menu, restaurantId), menu.getId());
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Cacheable("menu")
    @Override
    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public List<Menu> getByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return checkNotFound(repository.getByDate(date), "date=" + date);
    }
}
