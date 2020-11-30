package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import stereotype.Command;
import util.BotResponseFactoryUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arthur Kupriyanov on 24.04.2020
 */

public class CommandLoader {
    private final static Logger log = LoggerFactory.getLogger(CommandLoader.class);
    private final Map<List<String>, Executable> commandMap = new HashMap<>();

    public Executable getCommand(String name) {
        return findByName(name);
    }

    private Executable findByName(String name) {
        for (List<String> keys : commandMap.keySet()) {
            if (keys.contains(name)) return commandMap.get(keys);
        }

        return (msg) -> BotResponseFactoryUtil.createResponse("not_found", msg.peerId);
    }

    public void init(String commandsPackage) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(true);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Command.class));

        for (BeanDefinition bd : scanner.findCandidateComponents(commandsPackage)) {
            try {
                log.info("Found bean with class name : " + bd.getBeanClassName());
                processBean(bd);
            } catch (ClassNotFoundException e) {
                log.warn("Command class not found");
            }
        }

    }

    private void processBean(BeanDefinition bd) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(bd.getBeanClassName());

        if (!isImplementedInterface(clazz)) {
            log.warn(String.format("Command %s not implemented %s", clazz.getSimpleName(), Executable.class.getSimpleName()));
            return;
        }

        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            Constructor<Executable> castedConstructor = (Constructor<Executable>) c;
            Executable commandInstance;
            castedConstructor.setAccessible(true);
            if (castedConstructor.getParameterTypes().length == 0) {
                try {
                    commandInstance = castedConstructor.newInstance();
                    commandMap.put(getCommandNames(clazz), commandInstance);
                    log.info("Added command: " + bd.getBeanClassName());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> getCommandNames(Class<?> clazz) {

        Command command = clazz.getAnnotation(Command.class);
        if (command == null) {
            log.info(Executable.class.getSimpleName() + " annotation not found for " + clazz.getSimpleName());
            return new ArrayList<>();
        }
        List<String> names = new ArrayList<>(List.of(command.aliases()));
        names.add(command.value());
        return names;

    }

    private boolean isImplementedInterface(Class<?> clazz) {
        boolean implemented = false;
        for (Class<?> i : clazz.getInterfaces()) {
            log.info(i.getSimpleName());
            if (i == Executable.class) {
                implemented = true;
                break;
            }
        }
        return implemented;
    }
}
