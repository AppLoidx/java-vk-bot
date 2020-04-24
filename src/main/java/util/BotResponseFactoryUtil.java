package util;

import model.BotResponse;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public final class BotResponseFactoryUtil {
    private BotResponseFactoryUtil(){}
    public static BotResponse createResponse(String text, int peerId) {
        BotResponse response = new BotResponse();
        response.setPeerId(peerId);
        response.setMessage(text);
        return response;
    }
}
