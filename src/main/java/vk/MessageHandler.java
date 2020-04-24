package vk;

import com.vk.api.sdk.objects.messages.Message;
import model.BotResponse;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public interface MessageHandler {
    BotResponse handle(Message message) throws Exception;
}
