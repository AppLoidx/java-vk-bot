package vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arthur Kupriyanov
 */

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {
    private final Logger log = LoggerFactory.getLogger(CallbackApiLongPollHandler.class);
    private final MessageHandler handler;

    public CallbackApiLongPollHandler(VkApiClient client, GroupActor actor, MessageHandler handler) {
        super(client, actor);
        this.handler = handler;
    }

//    @Override
//    public void messageNew(Integer groupId, Message message) {
//        if (message != null && !message.isOut()) {
//            try {
//                log.info("Received message. Without secret" + message.toPrettyString());
//                handler.handle(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        if (message != null && !message.isOut()) {
            try {
                log.info("Received message " + message.toPrettyString());
                handler.handle(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
