package wf.spring.command_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import wf.spring.command_api.annotation.CommandHandle;
import wf.spring.command_api.argument.ArgumentMapper;
import wf.utils.command.listener.CommandHandler;
import wf.utils.command.subcommand.SubCommand;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class CommandHandlerBeanPostProcessor implements BeanPostProcessor {






    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(CommandHandle.class))
                continue;

            CommandHandle commandHandle = method.getAnnotation(CommandHandle.class);

            //noinspection rawtypes
            CommandHandler commandHandler = CommandHandlerManager.get(commandHandle.group());
            if(commandHandler == null)
                throw new RuntimeException("CommandHandler group not found: " + commandHandle.group());

            Class<?> clazz = commandHandler.getClass();
            Type superclass = clazz.getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type typeArgument = parameterizedType.getActualTypeArguments()[0];
            Class<?> typeClass = (Class<?>) typeArgument;

            //noinspection unchecked
            commandHandler.addSubcommand(
                    SubCommand.builder(typeClass)
                            .setCommand(commandHandle.command())
                            .setArguments(
                                    ArgumentMapper.convert(commandHandle.arguments())
                            )
                            .setRunnable(
                                    (sender, t, args) -> {
                                        invokeMethodWithArgs(bean, method, sender, t, args);
                                    }
                            )
                            .build()
            );

        }
        return bean;
    }

    public static void invokeMethodWithArgs(Object instance, Method method, Object sender, Object t, Object[] args) {
        try {
            Object[] methodArgs = new Object[args.length + 2];
            methodArgs[0] = sender;
            methodArgs[1] = t;

            System.arraycopy(args, 0, methodArgs, 2, args.length);

            method.invoke(instance, methodArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
