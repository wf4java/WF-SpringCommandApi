package wf.spring.command_api.argument;

import wf.utils.command.subcommand.executor.types.ArgumentType;

import java.util.HashMap;
import java.util.Map;

public class ArgumentTypeContainer {

    public final static String STRING = "STRING";
    public final static String INTEGER = "INTEGER";
    public final static String DOUBLE = "DOUBLE";
    public final static String LONG = "LONG";
    public final static String BOOLEAN = "BOOLEAN";
    public final static String MULTI_STRING = "MULTI_STRING";


    private final static Map<String, ArgumentType> argumentTypes = new HashMap<>();

    static {
        argumentTypes.put(STRING, ArgumentType.STRING);
        argumentTypes.put(INTEGER, ArgumentType.INTEGER);
        argumentTypes.put(DOUBLE, ArgumentType.DOUBLE);
        argumentTypes.put(LONG, ArgumentType.LONG);
        argumentTypes.put(BOOLEAN, ArgumentType.BOOLEAN);
        argumentTypes.put(MULTI_STRING, ArgumentType.MULTI_STRING);
    }

    public static void add(String name, ArgumentType type) {
        argumentTypes.put(name, type);
    }

    public static ArgumentType get(String name) {
        return argumentTypes.get(name);
    }


}
