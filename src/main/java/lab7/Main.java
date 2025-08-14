package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting quick recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    // TODO: implement the searchRecipes method

    /**
     * This searches for recipes that have the given search term in the name or in the description
     * The search is case-insensitive in the code
     * This method uses dependency injection, by accepting a DataService object
     * This allows easy testing with mock objects
     *
     * @param searchTerm is the term used to search for recipes
     * @param dataService is the data service dependency
     * @return returns a list of recipes that match the search term
     * displays empty list if no matches or if an error occurs in the data
     */


    public static List<Recipe> searchRecipes(String searchTerm, DataService dataService) {
        try {
            String lowerTerm = searchTerm.toLowerCase();

            return dataService.getRecipes().stream()
                    .filter(r -> r.name().toLowerCase().contains(lowerTerm)
                            || r.description().toLowerCase().contains(lowerTerm))
                    .toList();

        } catch (Exception e) {
            logger.error("Error while searching recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static void main(String[] args) {
        // Here, we INJECT a concrete implementation of the DataService interface
        // that allows us to get data from an SQLite database
        var quickRecipes = getQuickRecipes(new SqliteDataService());
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);

        // TODO: use your searchRecipes method with a SqliteDataService object

            var sqliteService = new SqliteDataService();
            var searchResults = searchRecipes("chicken", sqliteService);
            System.out.println("\nSearch Results for 'chicken':");
            searchResults.forEach(System.out::println);
        }
    }
