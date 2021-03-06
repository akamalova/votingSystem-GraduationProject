package ru.voting;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.voting.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.voting.MenuTestData.MENU_ID;
import static ru.voting.model.AbstractBaseEntity.START_SEQ;
import static ru.voting.web.json.JsonUtil.writeIgnoreProps;

public class DishTestData {

    public static final int DISH_ID = START_SEQ + 14;

    public static final Dish DISH1 = new Dish(DISH_ID, "Soup", 25.3);
    public static final Dish DISH2 = new Dish(DISH_ID + 1, "Meat", 60.5);

    public static Dish getCreatedDish() {
        return new Dish(null, "newDish", 20.00);
    }

    public static Dish getUpdatedDish() {
        Dish dish = new Dish(DISH_ID, "Обновленное блюдо", 15.35);
        dish.setMenuId(MENU_ID);
        return dish;
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menuId");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menuId").isEqualTo(expected);
    }

    public static ResultMatcher contentJsonDish(Dish... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "menuId"));
    }

    public static ResultMatcher contentJsonDish(Dish expected) {
        return content().json(writeIgnoreProps(expected, "menuId"));
    }
}
