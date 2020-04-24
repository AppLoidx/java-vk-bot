package vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ClientException;
import model.BotResponse;


/**
 * @author Arthur Kupriyanov
 */

public class VkMessenger {
    private GroupActor groupActor;
    private VkApiClient vkApiClient;
    public VkMessenger(GroupActor groupActor, VkApiClient vkApiClient){
        this.groupActor = groupActor;
        this.vkApiClient = vkApiClient;
    }

    public void sendMessage(BotResponse response) throws ClientException {
        if (response == null) return;
        vkApiClient.messages()
                .send(groupActor)
                .peerId(response.getPeerId())
                .message(response.getMessage())
                .randomId((int) (Math.random() * 2048))
                .executeAsRaw();
    }
}
