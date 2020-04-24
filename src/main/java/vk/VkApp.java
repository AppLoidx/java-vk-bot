package vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import command.CommandLoader;
import model.BotResponse;
import util.BotResponseFactoryUtil;

/**
 * @author Arthur Kupriyanov
 */
public class VkApp  {
    private static VkApiClient vkApiClient;
    private static GroupActor groupActor;
    private final TransportClient transportClient = new HttpTransportClient();
    private final String accessToken;
    private final int groupID;
    private static VkMessenger vkMessenger;


    public VkApp(String accessToken, int groupId) {

        this.accessToken = accessToken;
        this.groupID = groupId;
    }

    public void launch() {

        try {
            groupActor = init();
            vkMessenger = new VkMessenger(groupActor, vkApiClient);

            if (!vkApiClient.groups().getLongPollSettings(groupActor, groupActor.getGroupId()).execute().getIsEnabled()) {

                vkApiClient.groups().setLongPollSettings(groupActor, groupActor.getGroupId()).enabled(true).messageNew(true).execute();
            }
            CommandLoader cl = new CommandLoader();
            cl.init("command.impl");
            VkMessenger vkMessenger = new VkMessenger(groupActor, vkApiClient);
            CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(vkApiClient, groupActor, new MessageHandler() {
                @Override
                public BotResponse handle(Message message) throws Exception {
                    vkMessenger.sendMessage(cl.getCommand(message.getText()).execute(new model.Message(message.getPeerId(), message.getText())));
                    return cl.getCommand(message.getText()).execute(new model.Message(message.getPeerId(), message.getText()));
                }
            });
            handler.run();

        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }

    }

    static VkApiClient getVkApiClient(){ return vkApiClient;}
    public static VkMessenger getVkMessenger(){
        return vkMessenger;
    }
    private GroupActor init()  {
        vkApiClient = new VkApiClient(this.transportClient);

        return new GroupActor(groupID, accessToken);
    }
}
