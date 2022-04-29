package bus.artemrogov.etl;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;
@Component
public class CDataSimple {

    /**
     * @return
     * @throws Exception
     */
    public static CDataSimple runnerData() throws Exception{
        try(CamelContext camelContext = new DefaultCamelContext()){
            camelContext.addRoutes(basicRoute());
            camelContext.start();
            Thread.sleep(10_000); // 10 seconds
        }
        return null;
    }


    private static RouteBuilder basicRoute(){
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:foo").log("HELLO WORLD CAMEL! IN SPRING BOOT");
            }
        };
    }

}
