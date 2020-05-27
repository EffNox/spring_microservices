package nr.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PreTiempoTranscurrido extends ZuulFilter {

    private static Logger lg = LoggerFactory.getLogger(PreTiempoTranscurrido.class);

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest rq = ctx.getRequest();
        lg.info(String.format("%s request enrutado a %s", rq.getMethod(), rq.getRequestURI().toString()));
        Long tiempoInicio = System.currentTimeMillis();
        rq.setAttribute("tiempoInicio", tiempoInicio);
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
        return "pre";
    }

}
