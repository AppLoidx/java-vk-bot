package command;

import model.BotResponse;
import model.Message;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public interface Executable {

    BotResponse execute(Message message) throws Exception;
}
