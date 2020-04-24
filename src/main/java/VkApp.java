/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public class VkApp {
    public static void launch(String accessToken, int groupId){
        vk.VkApp app = new vk.VkApp(accessToken, groupId);
        app.launch();
    }

    public static void launch() {
        launch("accessToken", 1111);    // access_token, group_id
    }
}
