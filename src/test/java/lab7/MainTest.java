package lab7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                        new Recipe(0, "", "", "", 4, 10, 10, 10),
                        new Recipe(1, "", "", "", 4, 10, 10, 15),
                        new Recipe(2, "", "", "", 4, 10, 10, 16),
                        new Recipe(3, "", "", "", 4, 10, 10, 20),
                        new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    // TODO: test the searchRecipes method

    @Test
    void testSearchRecipesFindsByName() {
        var recipes = Main.searchRecipes("chicken", () -> List.of(
                new Recipe(0, "Chicken Soup", "Warm and hearty", "", 4, 10, 10, 20),
                new Recipe(1, "Tomato Pasta", "Fresh and tasty", "", 4, 10, 10, 15)
        ));

        assertEquals(1, recipes.size());
        assertEquals(0, recipes.get(0).id());
    }

    @Test
    void testSearchRecipesFindsByDescription() {
        var recipes = Main.searchRecipes("tomato", () -> List.of(
                new Recipe(0, "Veggie Salad", "Fresh tomato and cucumber", "", 4, 10, 10, 5),
                new Recipe(1, "Fruit Smoothie", "Sweet and refreshing", "", 4, 10, 10, 7)
        ));

        assertEquals(1, recipes.size());
        assertEquals(0, recipes.get(0).id());
    }

    @Test
    void testSearchRecipesIsCaseInsensitive() {
        var recipes = Main.searchRecipes("ChOcOlAtE", () -> List.of(
                new Recipe(0, "Chocolate Cake", "Rich and delicious", "", 4, 10, 10, 50),
                new Recipe(1, "chocolate mousse", "Creamy dessert", "", 4, 10, 10, 30)
        ));

        assertEquals(2, recipes.size());
    }

    /**
     * The below given test verifies that searchRecipes returns an empty list when no recipes match.
     */
    @Test
    void testSearchRecipesReturnsEmptyListWhenNoMatches() {
        var recipes = Main.searchRecipes("pizza", () -> List.of(
                new Recipe(0, "Pancakes", "Fluffy breakfast treat", "", 4, 10, 10, 15),
                new Recipe(1, "Omelette", "Eggs and veggies", "", 4, 10, 10, 10)
        ));

        assertEquals(0, recipes.size());
    }

    @Test
    void testSearchRecipesReturnsEmptyListOnError() {
        var recipes = Main.searchRecipes("anything", () -> {
            throw new RuntimeException("Database error");
        });

        assertEquals(0, recipes.size());
    }

}