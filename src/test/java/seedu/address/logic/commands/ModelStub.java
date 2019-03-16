package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Capacity;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRestaurantBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Member;
import seedu.address.model.person.Staff;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getRestaurantBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRestaurantBookFilePath(Path restaurantBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addMember(Member member) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addBooking(Booking booking) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStaff(Staff staff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRestaurantBook(ReadOnlyRestaurantBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyRestaurantBook getRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasMember(Member member) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasBooking(Booking booking) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStaff(Staff staff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMember(Member target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteBooking(Booking target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStaff(Staff target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedMember) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Member> getFilteredMemberList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canUndoRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canRedoRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitRestaurantBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProperty<Member> selectedMemberProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProperty<Booking> selectedBookingProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProperty<Ingredient> selectedIngredientProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProperty<Staff> selectedStaffProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Member getSelectedMember() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Booking getSelectedBooking() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Ingredient getSelectedIngredient() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Staff getSelectedStaff() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedMember(Member member) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedBooking(Booking booking) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedIngredient(Ingredient ingredient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedStaff(Staff staff) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Capacity getCapacity() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCapacity(Capacity newCapacity) {
        throw new AssertionError("This method should not be called.");
    }
}
