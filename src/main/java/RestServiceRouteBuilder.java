import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;

public class RestServiceRouteBuilder extends RouteBuilder {

    private final int portNumber;

    RestServiceRouteBuilder(int portNumber){
        this.portNumber = portNumber;
    }

    public void configure() throws Exception {

        from("restlet:http://localhost:" + portNumber + "/service")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {

                        //Request request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST, Request.class);

                        // use Restlet API to create the response
                        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);

                        response.setStatus(Status.SUCCESS_OK);
                        response.setEntity("<response>ru.testproj.CamelRest</response>", MediaType.TEXT_XML);
                        exchange.getOut().setBody(response);
                    }
                });
    }
}
