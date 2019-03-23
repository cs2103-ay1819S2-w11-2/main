package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Capacity;

/**
 * Updates the capacity of the restaurant.
 */
public class UpdateCapacityCommand extends Command {

    public static final String COMMAND_WORD = "updatecapacity";
    public static final String MESSAGE_SUCCESS = "Capacity successfully set to %1$s";
    public static final String MESSAGE_FAILURE = "Unable to resize capacity- restaurant will be overbooked.";

    private Capacity capacity;
    public UpdateCapacityCommand(Capacity capacity) {
        this.capacity = capacity;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (!model.canUpdateCapacity(capacity)) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.setCapacity(capacity);
        model.commitRestaurantBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, capacity));
    }
}
