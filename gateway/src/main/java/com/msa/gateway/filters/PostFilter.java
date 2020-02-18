package com.msa.gateway.filters;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class PostFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();

        try(final InputStream inputStream = context.getResponseDataStream()){
            if(StringUtils.isEmpty(inputStream)){
                log.info("BODY:{}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
            log.info("BODY : {}", responseData);

            context.setResponseBody(responseData);
        }catch (Exception e){
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return null;
    }
}
