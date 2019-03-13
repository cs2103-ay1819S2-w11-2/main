package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.booking.Booking;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Member;
import seedu.address.model.person.Staff;

/**
 * Wraps all data at the restaurant-book level
 * Duplicates are not allowed (by .isSameItem() comparison)
 */
public class RestaurantBook implements ReadOnlyRestaurantBook {

    private static final Capacity DEFAULT_CAPACITY = new Capacity(200);

    private final UniqueItemList<Member> members;
    private final UniqueItemList<Booking> bookings;
    private final UniqueItemList<Ingredient> ingredients;
    private final UniqueItemList<Staff> staff;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    private Capacity capacity = DEFAULT_CAPACITY;

        /*
        * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
        * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
        *
        * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
        *   among constructors.
        */ {
        members = new UniqueItemList<>();
        bookings = new UniqueItemList<>();
        ingredients = new UniqueItemList<>();
        staff = new UniqueItemList<>();
    }

    public RestaurantBook() {
    }

    /**
     * Creates an RestaurantBook using the Members in the {@code toBeCopied}
     */
    public RestaurantBook(ReadOnlyRestaurantBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the member list with {@code members}.
     * {@code members} must not contain duplicate members.
     */
    public void setMembers(List<Member> members) {
        this.members.setItems(members);
        indicateModified();
    }

    /**
     * Replaces the contents of the booking list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBooking(List<Booking> bookings) {
        this.bookings.setItems(bookings);
        indicateModified();
    }

    /**
     * Replaces the contents of the booking list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setItems(ingredients);
        indicateModified();
    }

    /**
     * Replaces the contents of the booking list with {@code staff}.
     * {@code staff} must not contain duplicate staff.
     */
    public void setStaff(List<Staff> staff) {
        this.staff.setItems(staff);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code RestaurantBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRestaurantBook newData) {
        requireNonNull(newData);

        setMembers(newData.getItemList(Member.class));
        setBooking(newData.getItemList(Booking.class));
        setIngredients(newData.getItemList(Ingredient.class));
        setStaff(newData.getItemList(Staff.class));
        capacity = newData.getCapacity();
    }

    //// item-level operations

    /**
     * Returns true if an item with the same identity as {@code item} exists in the restaurant book.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        if (item instanceof Member) {
            return members.contains(item);
        } else if (item instanceof Booking) {
            return bookings.contains(item);
        } else if (item instanceof Ingredient) {
            return ingredients.contains(item);
        } else if (item instanceof Staff) {
            return staff.contains(item);
        } else {
            return false;
        }
    }


    /**
     * Adds an item to the restaurant book.
     * The item must not already exist in the restaurant book.
     */
    public void addItem(Item i) {
        if (i instanceof Member) {
            members.add((Member) i);
        } else if (i instanceof Booking) {
            bookings.add((Booking) i);
            bookings.sort(Comparator.naturalOrder());
        } else if (i instanceof Ingredient) {
            ingredients.add((Ingredient) i);
        } else if (i instanceof Staff) {
            staff.add((Staff) i);
        } else {
            throw new IllegalArgumentException("Item type not recognised.");
        }
        indicateModified();
    }

    /**
     * Replaces the given member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the restaurant book.
     * The member identity of {@code editedMember} must not be the
     * same as another existing member in the restaurant book.
     */
    public <T extends Item> void setItem(T target, T editedItem) {
        requireNonNull(editedItem);
        if (target instanceof Member && editedItem instanceof Member) {
            members.setItem((Member) target, (Member) editedItem);
            // when a member is edited, update all the associated bookings too
            ObservableList<Booking> bookingObservableList = bookings.asUnmodifiableObservableList();
            Function<Booking, Booking>
                    updateBooking = b -> (b.getCustomer().equals(target) ? b.editContacts((Member) editedItem) : b);
            setBooking(bookingObservableList.stream().map(updateBooking).collect(Collectors.toList()));
        } else if (target instanceof Booking && editedItem instanceof Booking) {
            bookings.setItem((Booking) target, (Booking) editedItem);
            bookings.sort(Comparator.naturalOrder());
        } else if (target instanceof Ingredient && editedItem instanceof Ingredient) {
            ingredients.setItem((Ingredient) target, (Ingredient) editedItem);
        } else if (target instanceof Staff && editedItem instanceof Staff) {
            staff.setItem((Staff) target, (Staff) editedItem);
        } else {
            throw new IllegalArgumentException("Item type not recognised.");
        }
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code RestaurantBook}.
     * {@code key} must exist in the restaurant book.
     */
    public void removeItem(Item key) {
        if (key instanceof Member) {
            members.remove(key);
            // When a member is deleted, all associated bookings are also deleted.
            Predicate<Booking> isValidBooking = b -> !b.getCustomer().equals(key);
            ObservableList<Booking> bookingObservableList = bookings.asUnmodifiableObservableList();
            setBooking(bookingObservableList.stream().filter(isValidBooking).collect(Collectors.toList()));
        } else if (key instanceof Booking) {
            bookings.remove(key);
        } else if (key instanceof Ingredient) {
            ingredients.remove(key);
        } else if (key instanceof Staff) {
            staff.remove(key);
        } else {
            throw new IllegalArgumentException("Item type not recognised.");
        }
        indicateModified();
    }

    /**
     * Returns the capacity of the restaurant.
     */
    @Override
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the restaurant.
     */
    public void setCapacity(Capacity newCapacity) {
        capacity = newCapacity; // TODO : check that this does not cause size to be too small
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the restaurant book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return members.asUnmodifiableObservableList().size() + " members ";
        // TODO: refine later
    }

    @Override
    public <T extends Item> ObservableList<T> getItemList(Class<T> clazz) {
        if (clazz == Member.class) {
            return (ObservableList<T>) members.asUnmodifiableObservableList();
        } else if (clazz == Booking.class) {
            return (ObservableList<T>) bookings.asUnmodifiableObservableList();
        } else if (clazz == Ingredient.class) {
            return (ObservableList<T>) ingredients.asUnmodifiableObservableList();
        } else if (clazz == Staff.class) {
            return (ObservableList<T>) staff.asUnmodifiableObservableList();
        } else {
            throw new IllegalArgumentException("Item type not recognised.");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RestaurantBook // instanceof handles nulls
                && members.equals(((RestaurantBook) other).members)
                && bookings.equals(((RestaurantBook) other).bookings)
                && ingredients.equals(((RestaurantBook) other).ingredients)
                && staff.equals(((RestaurantBook) other).staff));
    }

    @Override
    public int hashCode() {
        return members.hashCode();
    }
}
