# WF-SpringCommandApi:
## Maven:
`Java(min): 17`
```xml
  <dependency>
    <groupId>io.github.wf4java</groupId>
    <artifactId>WF-SpringCommandApi</artifactId>
    <version>1.0.1</version>
  </dependency>
```

## Example:
```java
@Configuration
public class CommandConfig {

    @PostConstruct
    public void init() {
        CommandHandlerManager.setDefault(new StringCommandHandler());
    }

    @Bean
    public CommandHandlerBeanPostProcessor commandHandlerBeanPostProcessor() {
        return new CommandHandlerBeanPostProcessor();
    }

}


// In any component
@CommandHandle(
        command = "hello",
        arguments = {
                @Argument(name = "Limit", type = ArgumentTypeContainer.INTEGER)
        }
)
public void onHelloCommand(CommandSender commandSender, String command, Integer limit) { //Limit or other args
    System.out.println(limit);
}



// And call
CommandHandlerManager.onCommand("hello 20", System.out::println, null);
```

