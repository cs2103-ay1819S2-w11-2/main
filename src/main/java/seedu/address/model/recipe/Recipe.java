package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Item;

/**
 *  A class to represent a recipe.
 *  The recipe has a recipeName, as well as a set of ingredients in the recipe.
 *  The set of ingredients must not be empty.
 */

public class Recipe implements Item {

    // Identity fields
    private RecipeName recipeName;
    private RecipeIngredientSet ingredientsInRecipe;

    /**
     * Every field must be present and not null.
     */
    public Recipe(RecipeName name, RecipeIngredientSet set) {
        requireAllNonNull(name, set);
        recipeName = name;
        ingredientsInRecipe = set;
    }


    public RecipeName getRecipeName() {
        return recipeName;
    }

    public RecipeIngredientSet getRecipeIngredientSet() {
        return ingredientsInRecipe;
    }

    /**
     * Returns true if both recipes have same name.
     */
    @Override
    public boolean isSameItem(Object other) {
        if (other instanceof Recipe) {
            return recipeName.equals(((Recipe) other).getRecipeName());
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Recipe)) {
            return false;
        }
        return recipeName.equals(((Recipe) other).getRecipeName())
                && ingredientsInRecipe.equals((RecipeIngredientSet) other);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(recipeName, ingredientsInRecipe);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Recipe: ")
                .append(getRecipeName());
        return builder.toString();
    }
}
