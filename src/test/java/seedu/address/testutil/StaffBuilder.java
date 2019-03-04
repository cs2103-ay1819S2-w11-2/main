package seedu.address.testutil;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Staff;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_NAME = "Jack Smith";
    public static final String DEFAULT_PHONE = "91234567";
    public static final String DEFAULT_EMAIL = "jacksmith@example.com";
    public static final String DEFAILT_APPOINTMENT = "Chef";

    private Name name;
    private Phone phone;
    private Email email;
    private Appointment appointment;

    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        appointment = new Appointment(DEFAILT_APPOINTMENT);
    }

    /**
     * Initializes the MemberBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        name = staffToCopy.getName();
        phone = staffToCopy.getPhone();
        email = staffToCopy.getEmail();
        appointment = staffToCopy.getAppointment();
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Staff} that we are building.
     */
    public StaffBuilder withAppointment(String appointment) {
        this.email = new Email(appointment);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, appointment);
    }

}
