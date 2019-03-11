package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.RestaurantBook;
import seedu.address.model.person.Member;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditMemberDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String MEMBER_VALID_NAME_AMY = "Amy Bee";
    public static final String MEMBER_VALID_NAME_BOB = "Bob Choo";
    public static final String MEMBER_VALID_PHONE_AMY = "11111111";
    public static final String MEMBER_VALID_PHONE_BOB = "22222222";
    public static final String MEMBER_VALID_EMAIL_AMY = "amy@example.com";
    public static final String MEMBER_VALID_EMAIL_BOB = "bob@example.com";

    public static final String MEMBER_NAME_DESC_AMY = " " + PREFIX_NAME + MEMBER_VALID_NAME_AMY;
    public static final String MEMBER_NAME_DESC_BOB = " " + PREFIX_NAME + MEMBER_VALID_NAME_BOB;
    public static final String MEMBER_PHONE_DESC_AMY = " " + PREFIX_PHONE + MEMBER_VALID_PHONE_AMY;
    public static final String MEMBER_PHONE_DESC_BOB = " " + PREFIX_PHONE + MEMBER_VALID_PHONE_BOB;
    public static final String MEMBER_EMAIL_DESC_AMY = " " + PREFIX_EMAIL + MEMBER_VALID_EMAIL_AMY;
    public static final String MEMBER_EMAIL_DESC_BOB = " " + PREFIX_EMAIL + MEMBER_VALID_EMAIL_BOB;

    public static final String MEMBER_INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String MEMBER_INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String MEMBER_INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String INGREDIENT_VALID_NAME_CHEESE = "cheese";
    public static final String INGREDIENT_VALID_NAME_TOMATO = "tomato";
    public static final String INGREDIENT_VALID_UNIT_CHEESE = "4";
    public static final String INGREDIENT_VALID_UNIT_TOMATO = "5";

    public static final String INGREDIENT_NAME_DESC_CHEESE =
            " " + PREFIX_INGREDIENT_NAME + INGREDIENT_VALID_NAME_CHEESE;
    public static final String INGREDIENT_UNIT_DESC_CHEESE =
            " " + PREFIX_INGREDIENT_UNIT + INGREDIENT_VALID_UNIT_CHEESE;
    public static final String INGREDIENT_NAME_DESC_TOMATO =
            " " + PREFIX_INGREDIENT_NAME + INGREDIENT_VALID_NAME_TOMATO;
    public static final String INGREDIENT_UNIT_DESC_TOMATO =
            " " + PREFIX_INGREDIENT_UNIT + INGREDIENT_VALID_UNIT_TOMATO;

    public static final String INGREDIENT_INVALID_NAME_DESC = " " + PREFIX_INGREDIENT_NAME
            + "1"; // integers not allowed in ingredientName
    public static final String INGREDIENT_INVALID_UNIT_DESC = " " + PREFIX_INGREDIENT_UNIT
            + "potato"; // characters not allowed in names



    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditMemberDescriptor MEMBER_DESC_AMY;
    public static final EditCommand.EditMemberDescriptor MEMBER_DESC_BOB;

    static {
        MEMBER_DESC_AMY = new EditMemberDescriptorBuilder().withName(MEMBER_VALID_NAME_AMY)
                .withPhone(MEMBER_VALID_PHONE_AMY).withEmail(MEMBER_VALID_EMAIL_AMY).build();
        MEMBER_DESC_BOB = new EditMemberDescriptorBuilder().withName(MEMBER_VALID_NAME_BOB)
                .withPhone(MEMBER_VALID_PHONE_BOB).withEmail(MEMBER_VALID_EMAIL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered member list and selected member in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RestaurantBook expectedRestaurantBook = new RestaurantBook(actualModel.getRestaurantBook());
        List<Member> expectedFilteredList = new ArrayList<>(actualModel.getFilteredItemList(Member.class));
        Member expectedSelectedMember = actualModel.getSelectedItem(Member.class);

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedRestaurantBook, actualModel.getRestaurantBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredItemList(Member.class));
            assertEquals(expectedSelectedMember, actualModel.getSelectedItem(Member.class));
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the member at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showMemberAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList(Member.class).size());

        Member member = model.getFilteredItemList(Member.class).get(targetIndex.getZeroBased());
        final String[] splitName = member.getName().fullName.split("\\s+");
        model.updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])), Member.class);

        assertEquals(1, model.getFilteredItemList(Member.class).size());
    }

    /**
     * Deletes the first member in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstMember(Model model) {
        Member firstMember = model.getFilteredItemList(Member.class).get(0);
        model.deleteItem(firstMember);
        model.commitRestaurantBook();
    }

}
