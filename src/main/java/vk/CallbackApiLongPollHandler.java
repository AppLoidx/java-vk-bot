package vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import lombok.SneakyThrows;

/**
 * @author Arthur Kupriyanov
 */
public class CallbackApiLongPollHandler extends CallbackApiLongPoll {

    private final MessageHandler handler;
    public CallbackApiLongPollHandler(VkApiClient client, GroupActor actor, MessageHandler handler) {
        super(client, actor);
        this.handler = handler;
    }


    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, Message message) {
        if (message != null && !message.isOut())
            handler.handle(message).getMessage();
        //handler.receive(new model.Message(message.getText(), "" + message.getFromId(), "" + message.getPeerId(), MessageSourceType.VK));
    }

    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        if (message != null && !message.isOut())
            handler.handle(message).getMessage();
    }
}
