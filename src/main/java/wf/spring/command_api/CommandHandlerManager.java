package wf.spring.command_api;

import wf.utils.command.listener.CommandHandler;
import wf.utils.command.model.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandHandlerManager {

    private final static Map<String, CommandHandler<?>> handlers = new HashMap<>();


    public static void onCommand(String command, CommandSender commandSender, Object t) {
        onCommand("default", command, commandSender, t);
    }

    public static void onCommand(String group, String command, CommandSender commandSender, Object t) {
        //noinspection rawtypes
        CommandHandler commandHandler = handlers.get(group);
        if(commandHandler == null)
            return;

        //noinspection unchecked
        commandHandler.onCommand(command, commandSender, t);
    }

    public static void setDefault(CommandHandler<?> commandHandler) {
        handlers.put("default", commandHandler);
    }

    public static void add(String group, CommandHandler<?> commandHandler) {
        handlers.put(group, commandHandler);
    }

    public static CommandHandler<?> remove(String group) {
        return handlers.remove(group);
    }

    public static CommandHandler<?> get(String group) {
        return handlers.get(group);
    }


}
