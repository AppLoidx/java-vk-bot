package model;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */

public class BotResponse {
    private String message;
    private int peerId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }
}
