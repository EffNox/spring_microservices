package nr.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PostTiempoTranscurrido extends ZuulFilter {

    private static Logger lg = LoggerFactory.getLogger(PostTiempoTranscurrido.class);

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest rq = ctx.getRequest();
        lg.info("Entrando a post filter");
        Long tiempoInicio = (Long) rq.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
        lg.info(String.format("Tiempo transcurrido en segundos %s seg.", tiempoTranscurrido.doubleValue() / 1000.00));
        lg.info(String.format("Tiempo transcurrido en mileSegundos %s ms.", tiempoTranscurrido));
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "post";
    }

}
