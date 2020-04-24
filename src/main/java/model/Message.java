package model;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public class Message {
    public final int peerId;
    public final String text;

    public Message(int peerId, String text) {
        this.peerId = peerId;
        this.text = text;
    }
}
