package bus.artemrogov.etl;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouteData extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://scheldule?fixedRate=true&delay=0&period=10000")
                .log(LoggingLevel.INFO,this.log,"test test message")
                .to("http://localhost:5000/blog")
                .to("file:output");

    }
}
