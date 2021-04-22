package pl.mimuw.jnp2.cameltest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

    public void configure() throws Exception {
        restConfiguration().host("{{bank.host}}").port("{{bank.port}}");
        from("timer:helloBank?period={{timer.period}}&repeatCount={{repeat.count}}")
                .setHeader("id", simple("${random(1,5)}"))
                .to("rest:get:customers/{id}/")
                .log("${body}");
    }
}
