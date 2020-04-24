package command.impl;

import command.Executable;
import model.BotResponse;
import model.Message;
import stereotype.Command;
import util.BotResponseFactoryUtil;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
@Command(value = "hello", aliases = {"привет", "йоу"})
public class Hello implements Executable {

    public BotResponse execute(Message message) throws Exception {
        return BotResponseFactoryUtil.createResponse("hello-hello", message.peerId);
    }
}
