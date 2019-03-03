import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.component.restlet.RestletComponent;

public class MainCamel
{
    private static final int SERVICE_PORT_NUMBER = 8080;

    public static void main(String[] args) {
        CamelContext ctx = new DefaultCamelContext();
        ctx.addComponent("restlet", new RestletComponent());

        RestServiceRouteBuilder routeBuilder = new RestServiceRouteBuilder(SERVICE_PORT_NUMBER);

        try {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
