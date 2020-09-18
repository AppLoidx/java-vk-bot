# java-vk-bot

## Clone
```
git clone https://github.com/AppLoidx/java-vk-bot.git
```

## Adding access token from vk
```java
public class VkApp {
    public static void launch(String accessToken, int groupId){
        vk.VkApp app = new vk.VkApp(accessToken, groupId);
        app.launch();
    }

    public static void launch() {
        launch("accessToken", 1111);    // access_token, group_id
    }
}
```

## Adding new Command
In package `package command.impl`
```java
@Command(value = "hello", aliases = {"привет", "йоу"})
public class Hello implements Executable {

    public BotResponse execute(Message message) throws Exception {
        return BotResponseFactoryUtil.createResponse("hello-hello", message.peerId);
    }
}
```

## Добавление новой команды
Чтобы добавить новую команду нужно создать класс с аннотацией `Command` в пакете `command.impl`
```java
@Command(value = "hello", aliases = {"привет", "йоу"})
public class Hello implements Executable {

    public BotResponse execute(Message message) throws Exception {
        return BotResponseFactoryUtil.createResponse("hello-hello", message.peerId);
    }
}
```

Команда должна вернуть объект [BotResponse](https://github.com/AppLoidx/java-vk-bot/blob/master/src/main/java/model/BotResponse.java), который содержит id адрессата и тело сообщения
