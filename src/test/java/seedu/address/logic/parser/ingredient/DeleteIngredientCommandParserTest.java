package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;

import org.junit.Test;

import seedu.address.logic.commands.DeleteIngredientCommand;
import seedu.address.logic.parser.DeleteIngredientCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteIngredientCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteIngredientCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */

public class DeleteIngredientCommandParserTest {
    private DeleteIngredientCommandParser parser = new DeleteIngredientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIngredientCommand.MESSAGE_USAGE));
    }

}

