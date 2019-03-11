package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_INGREDIENT_NAME = new Prefix("in/");
    public static final Prefix PREFIX_INGREDIENT_UNIT = new Prefix("u/");
    public static final Prefix PREFIX_INGREDIENT_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_START_TIME = new Prefix("ts/");
    public static final Prefix PREFIX_CUSTOMER = new Prefix("c/");
    public static final Prefix PREFIX_NUMBER_PERSONS = new Prefix("n/");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("a/");
    public static final Prefix PREFIX_LOYALTY_POINTS = new Prefix("l/");
}
