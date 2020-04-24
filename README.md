# java-vk-bot

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
