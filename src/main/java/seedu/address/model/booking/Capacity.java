package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Used to ensure that all bookings stays within the capacity of the restaurant.
 * The restaurant will not be able to hold more bookings if the capacity is exceeded.
 */
public class Capacity {
    public static final int MAX_CAPACITY = 10000;
    public static final String MESSAGE_CONSTRAINTS = "Capacity should be an integer between 1 and "
            + MAX_CAPACITY + " inclusive.";
    private int value;

    public Capacity(int intCapacity) {
        checkArgument(isValidCapacity(intCapacity), MESSAGE_CONSTRAINTS);
        value = intCapacity;
    }

    public Capacity(String strCapacity) {
        checkArgument(isValidCapacity(strCapacity), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(strCapacity);
    }

    public int getValue() {
        return value;
    }

    /**
     * Checks if the size is within 1 and MAX_BOOKING_SIZE.
     */
    public static boolean isValidCapacity(int intCapacity) {
        return intCapacity > 0 && intCapacity <= MAX_CAPACITY;
    }

    /**
     * Checks if strCapacity is valid after converting to an integer.
     */
    public static boolean isValidCapacity(String strCapacity) {
        requireNonNull(strCapacity);
        try {
            return isValidCapacity(Integer.parseInt(strCapacity));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the capacity is sufficient to hold the bookings.
     */
    public boolean canAccommodate(List<Booking> bookings) {
        List<Event> events = new ArrayList<>();
        for (Booking booking: bookings) {
            Event arrival = new Event(booking.getNumMembers().getSize(), booking.getStartTime());
            Event departure = new Event(-booking.getNumMembers().getSize(), booking.getEndTime());
            events.add(arrival);
            events.add(departure);
        }

        events.sort(Comparator.naturalOrder());
        int currentOccupancy = 0;
        for (Event event: events) {
            currentOccupancy += event.changeInPersons;
            if (currentOccupancy > value) { // if this happens, the restaurant is full at this time
                return false;
            }
        }
        return true;
    }

    /**
     * Represents the departure or arrival of customers at a specific time.
     */
    private class Event implements Comparable<Event> {
        final int changeInPersons;
        final LocalDateTime eventTime;
        // For changeInPersons, positive values represent arrivals while negative values represent departures.
        Event(int changeInPersons, LocalDateTime eventTime) {
            this.changeInPersons = changeInPersons;
            this.eventTime = eventTime;
        }

        @Override
        public int compareTo(Event other) {
            if (eventTime.compareTo(other.eventTime) != 0) {
                return eventTime.compareTo(other.eventTime);
            } else {
                return changeInPersons - other.changeInPersons; // important: ensure that departures occur first
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Capacity // instanceof handles nulls
                && value == ((Capacity) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
