package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.RestaurantBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRestaurantBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Member;
import seedu.address.model.person.Staff;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final RestaurantBookParser restaurantBookParser;
    private boolean restaurantBookModified;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        history = new CommandHistory();
        restaurantBookParser = new RestaurantBookParser();

        // Set restaurantBookModified to true whenever the models' address book is modified.
        model.getRestaurantBook().addListener(observable -> restaurantBookModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        restaurantBookModified = false;

        CommandResult commandResult;
        try {
            Command command = restaurantBookParser.parseCommand(commandText);
            commandResult = command.execute(model, history);
        } finally {
            history.add(commandText);
        }

        if (restaurantBookModified) {
            logger.info("Address book modified, saving to file.");
            try {
                storage.saveRestaurantBook(model.getRestaurantBook());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyRestaurantBook getRestaurantBook() {
        return model.getRestaurantBook();
    }

    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return model.getFilteredMemberList();
    }

    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return model.getFilteredBookingList();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return model.getFilteredIngredientList();
    }

    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return model.getFilteredStaffList();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public Path getRestaurantBookFilePath() {
        return model.getRestaurantBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<Member> selectedMemberProperty() {
        return model.selectedMemberProperty();
    }

    @Override
    public ReadOnlyProperty<Booking> selectedBookingProperty() {
        return model.selectedBookingProperty();
    }

    @Override
    public ReadOnlyProperty<Ingredient> selectedIngredientProperty() {
        return model.selectedIngredientProperty();
    }

    @Override
    public ReadOnlyProperty<Staff> selectedStaffProperty() {
        return model.selectedStaffProperty();
    }

    @Override
    public void setSelectedMember(Member member) {
        model.setSelectedItem(member, Member.class);
    }

    @Override
    public void setSelectedBooking(Booking booking) {
        model.setSelectedItem(booking, Booking.class);
    }

    @Override
    public void setSelectedIngredient(Ingredient ingredient) {
        model.setSelectedItem(ingredient, Ingredient.class);
    }

    @Override
    public void setSelectedStaff(Staff staff) {
        model.setSelectedItem(staff, Staff.class);
    }
}
