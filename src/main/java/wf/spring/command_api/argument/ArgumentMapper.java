package wf.spring.command_api.argument;


import wf.utils.command.subcommand.executor.Argument;

public class ArgumentMapper {

    public static Argument convert(wf.spring.command_api.argument.annotation.Argument argument) {
        return new Argument(argument.name(), ArgumentTypeContainer.get(argument.type()), argument.obligatorily());
    }

    public static Argument[] convert(wf.spring.command_api.argument.annotation.Argument[] arguments) {
        Argument[] result = new Argument[arguments.length];
        for (int i = 0; i < arguments.length; i++)
            result[i] = convert(arguments[i]);
        return result;
    }

}
