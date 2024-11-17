package category;

import java.util.ArrayList;

/**
 * This enum contains the categories of the books.
 */
public enum Category {
    CAT1,
    CAT2,
    CAT3;

    /**
     * Creates a menu list containing the string "CATEGORIE" followed by the names of all categories.
     * The names of the categories are retrieved from the {@link Category} enum values.
     *
     * @return an {@link ArrayList} of strings representing the menu, starting with "CATEGORIE"
     *         and followed by the names of all categories in the {@link Category} enum.
     */
    public static ArrayList<String> toMenu() {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("CATEGORIE");

        for (Category category : Category.values()) {
            menu.add(category.name());
        }

        return menu;
    }
}
