package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.model.booking.BookingSize.MAX_BOOKING_SIZE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.booking.BookingSize;
import seedu.address.model.booking.BookingWindow;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.IngredientUnit;
import seedu.address.model.ingredient.IngredientWarningAmount;
import seedu.address.model.person.Email;
import seedu.address.model.person.LoyaltyPoints;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.Assert;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_LOYALTY_POINTS = "-123";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_LOYALTY_POINTS = "1234";
    private static final int VALID_LOYALTY_POINTS_INT = 1234;

    private static final String INVALID_FEB_29 = "2019-02-29T12:00";
    private static final String WRONG_DATE_FORMAT = "2019-02-28T1200";
    private static final String VALID_FEB_29 = "2020-02-29T12:00";

    private static final String INVALID_INGREDIENTNAME_INTEGER = "10";
    private static final String INVALID_INGREDIENTNAME_SYMBOLS = "cheese@4";
    private static final String INVALID_INGREDIENTUNIT_INTEGER = "1";
    private static final String INVALID_INGREDIENTUNIT_SYMBOLS = "sac3`k";
    private static final int INVALID_INGREDIENTQUANTITY_NEGATIVE = -1;
    private static final int INVALID_INGREDIENTWARNINGAMT_NEGATIVE = -1;

    private static final String VALID_INGREDIENTNAME = "cheese";
    private static final int VALID_INGREDIENTQUANTITY_POSITIVEINT = 1;
    private static final int VALID_INGREDIENTQUANTITY_ZERO = 0;
    private static final String VALID_INGREDIENTUNIT = "sacks";
    private static final int VALID_INGREDIENTWARNINGAMT_POSITIVEINT = 1;
    private static final int VALID_INGREDIENTWARNINGAMT_ZERO = 0;

    private static final String WHITESPACE = " \t\r\n";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseIndex_invalidInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIndex("10 a");
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_INDEX);
        ParserUtil.parseIndex(Long.toString((long)Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseLoyaltyPoints_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseLoyaltyPoints(INVALID_LOYALTY_POINTS));
    }

    @Test
    public void parseLoyaltyPoints_validValueWithoutWhitespace_returnsLoyaltyPoints() throws Exception {
        LoyaltyPoints expectedLoyaltyPoints = new LoyaltyPoints(VALID_LOYALTY_POINTS_INT);
        assertEquals(expectedLoyaltyPoints, ParserUtil.parseLoyaltyPoints(VALID_LOYALTY_POINTS));
    }

    @Test
    public void parseLoyaltyPoints_validValueWithWhitespace_returnsLoyaltyPoints() throws Exception {
        String loyaltyPointsWithWhitespace = WHITESPACE + VALID_LOYALTY_POINTS + WHITESPACE;
        LoyaltyPoints expectedLoyaltyPoints = new LoyaltyPoints(VALID_LOYALTY_POINTS_INT);
        assertEquals(expectedLoyaltyPoints, ParserUtil.parseLoyaltyPoints(loyaltyPointsWithWhitespace));
    }

    @Test
    public void parseIngredientName_invalidNonAlphabetValue_throwsException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientName(INVALID_INGREDIENTNAME_INTEGER);
    }

    @Test
    public void parseIngredientName_invalidSymbolsValue_throwsExcetpion() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientName(INVALID_INGREDIENTNAME_SYMBOLS);
    }

    @Test
    public void parseIngredientName_null_throwsException() throws Exception {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredientName((String) null));
    }


    @Test
    public void parseIngredientName_validValue_returnsIngredientName() throws Exception {
        String ingredientNameWithWhiteSpace = WHITESPACE + VALID_INGREDIENTNAME + WHITESPACE;
        IngredientName expectedIngredientName = new IngredientName(VALID_INGREDIENTNAME);

        //with white space
        assertEquals(expectedIngredientName, ParserUtil.parseIngredientName((ingredientNameWithWhiteSpace)));

        //without white space
        assertEquals(expectedIngredientName, ParserUtil.parseIngredientName((VALID_INGREDIENTNAME)));
    }

    @Test
    public void parseIngredientQuantity_invalidNegativeValue_throwsExcepion() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientQuantity(Integer.toString(INVALID_INGREDIENTQUANTITY_NEGATIVE));
    }

    @Test
    public void parseIngredientQuantity_invalidOutOfRange_throwsExcepion() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientQuantity(Long.toString((long)Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIngredientQuantity_null_throwsException() throws Exception {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredientQuantity((String) null));
    }
    @Test
    public void parseIngredientQuantity_validValue_returnsIngredientQuantity() throws Exception {
        String posIngredientQuantityWithWhiteSpace = WHITESPACE + VALID_INGREDIENTQUANTITY_POSITIVEINT + WHITESPACE;
        IngredientQuantity posExpectedIngredientQuantity = new IngredientQuantity(VALID_INGREDIENTQUANTITY_POSITIVEINT);

        //positive integer with white space
        assertEquals(posExpectedIngredientQuantity,
                ParserUtil.parseIngredientQuantity((posIngredientQuantityWithWhiteSpace)));

        //positive integer without white space
        assertEquals(posExpectedIngredientQuantity,
                ParserUtil.parseIngredientQuantity((Integer.toString(VALID_INGREDIENTQUANTITY_POSITIVEINT))));

        String zeroIngredientQuantityWithWhiteSpace = WHITESPACE + VALID_INGREDIENTQUANTITY_ZERO + WHITESPACE;
        IngredientQuantity zeroExpectedIngredientQuantity = new IngredientQuantity(VALID_INGREDIENTQUANTITY_ZERO);

        //zero with white space
        assertEquals(zeroExpectedIngredientQuantity,
                ParserUtil.parseIngredientQuantity((zeroIngredientQuantityWithWhiteSpace)));

        //zero without white space
        assertEquals(zeroExpectedIngredientQuantity,
                ParserUtil.parseIngredientQuantity((Integer.toString(VALID_INGREDIENTQUANTITY_ZERO))));
    }

    @Test
    public void parseIngredientUnit_invalidNonAlphabetValue_throwsException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientUnit(INVALID_INGREDIENTUNIT_INTEGER);
    }

    @Test
    public void parseIngredientUnit_invalidSymbolsValue_throwsException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientUnit(INVALID_INGREDIENTUNIT_SYMBOLS);
    }

    @Test
    public void parseIngredientUnit_null_throwsException() throws Exception {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredientUnit((String) null));
    }


    @Test
    public void parseIngredientUnit_validValue_returnsIngredientUnit() throws Exception {
        String ingredientUnitWithWhiteSpace = WHITESPACE + VALID_INGREDIENTUNIT + WHITESPACE;
        IngredientUnit expectedIngredientUnit = new IngredientUnit(VALID_INGREDIENTUNIT);

        //with white space
        assertEquals(expectedIngredientUnit, ParserUtil.parseIngredientUnit((ingredientUnitWithWhiteSpace)));

        //without white space
        assertEquals(expectedIngredientUnit, ParserUtil.parseIngredientUnit((VALID_INGREDIENTUNIT)));
    }


    @Test
    public void parseIngredientWarningAmount_invalidNegativeValue_throwsExcepion() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientWarningAmount(Integer.toString(INVALID_INGREDIENTWARNINGAMT_NEGATIVE));
    }

    @Test
    public void parseIngredientWarningAmount_invalidOutOfRange_throwsExcepion() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIngredientWarningAmount(Long.toString((long)Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIngredientWarningAmount_validValue_returnsIngredientWarning() throws Exception {
        String posIngredientWarningAmtWithWhiteSpace = WHITESPACE + VALID_INGREDIENTWARNINGAMT_POSITIVEINT + WHITESPACE;
        IngredientWarningAmount posExpectedIngredientWarningAmount =
                new IngredientWarningAmount(VALID_INGREDIENTWARNINGAMT_POSITIVEINT);

        //positive integer with white space
        assertEquals(posExpectedIngredientWarningAmount,
                ParserUtil.parseIngredientWarningAmount((posIngredientWarningAmtWithWhiteSpace)));

        //positive integer without white space
        assertEquals(posExpectedIngredientWarningAmount,
                ParserUtil.parseIngredientWarningAmount(Integer.toString(VALID_INGREDIENTWARNINGAMT_POSITIVEINT)));

        String zeroIngredientWarningAmtWithWhiteSpace = WHITESPACE + VALID_INGREDIENTWARNINGAMT_ZERO + WHITESPACE;
        IngredientWarningAmount zeroExpectedIngredientWarningAmt =
                new IngredientWarningAmount(VALID_INGREDIENTWARNINGAMT_ZERO);

        //zero with white space
        assertEquals(zeroExpectedIngredientWarningAmt,
                ParserUtil.parseIngredientWarningAmount((zeroIngredientWarningAmtWithWhiteSpace)));

        //zero without white space
        assertEquals(zeroExpectedIngredientWarningAmt,
                ParserUtil.parseIngredientWarningAmount((Integer.toString(VALID_INGREDIENTWARNINGAMT_ZERO))));
    }

    @Test
    public void parseBookingWindow_invalidDate_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseBookingWindow(INVALID_FEB_29));
    }

    @Test
    public void parseBookingWindow_wrongDateFormat_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseBookingWindow(WRONG_DATE_FORMAT));
    }

    @Test
    public void parseBookingWindow_validDate_returnsBookingWindow() throws Exception {
        BookingWindow expectedBookingWindow = new BookingWindow(LocalDateTime.of(2020, Month.FEBRUARY, 29, 12, 0));
        assertEquals(expectedBookingWindow, ParserUtil.parseBookingWindow(VALID_FEB_29));
    }

    @Test
    public void parseBookingSize_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseBookingSize("0"));
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseBookingSize("-1"));
        Assert.assertThrows(
                ParseException.class, () -> ParserUtil.parseBookingSize(Integer.toString(MAX_BOOKING_SIZE + 1)));
    }

    @Test
    public void parseBookingSize_validBookingSize_returnsBookingSize() throws Exception {
        // test boundary values
        BookingSize expectedBookingSize = new BookingSize(1);
        assertEquals(expectedBookingSize, ParserUtil.parseBookingSize("1"));

        expectedBookingSize = new BookingSize(MAX_BOOKING_SIZE);
        assertEquals(expectedBookingSize, ParserUtil.parseBookingSize(Integer.toString(MAX_BOOKING_SIZE)));
    }

}
