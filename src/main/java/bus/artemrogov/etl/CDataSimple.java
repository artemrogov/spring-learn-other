package bus.artemrogov.etl;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;
@Component
public class CDataSimple {

    private static final String OUTPUT_TEXT_LOG = "HELLO WORLD CAMEL! IN SPRING BOOT";
    private static final int TIMER_SECONDS = 10_000;

    /**
     * @return
     * @throws Exception
     */
    public static CDataSimple runnerData() throws Exception{
        try(CamelContext camelContext = new DefaultCamelContext()){
            camelContext.addRoutes(basicRoute());
            camelContext.start();
            Thread.sleep(TIMER_SECONDS); // 10 seconds
        }
        return null;
    }


    private static RouteBuilder basicRoute(){
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:foo").log(OUTPUT_TEXT_LOG);
            }
        };
    }

}
