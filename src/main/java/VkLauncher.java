import vk.VkApp;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */
public class VkLauncher {
    public static void launch(String accessToken, int groupId) {
        VkApp app = new VkApp(accessToken, groupId);
        app.launch();
    }

    public static void launch() {
        launch("a803195a95f7fe0b9078d16646a26086a10b147f4753f16db041d5333089ea2d807ca7154d0d78dad4e7a", 172998024);
    }
}
