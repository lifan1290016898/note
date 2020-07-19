package com.demo.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 令牌桶
 */
@Component
public class MyZuulFilter extends ZuulFilter {

    // 每秒生成2个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    // 过滤的时机
    @Override
    public String filterType() {
//        return "pre";
        return FilterConstants.PRE_TYPE;
    }

    // 过滤器的顺序，越小越先执行，可以是负值，FilterConstants提供的最小值为-3
    @Override
    public int filterOrder() {
        return -4;
    }

    /**
     * 如果这个方法返回false则调用run方法，否则不会调用
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (!RATE_LIMITER.tryAcquire()) {
            currentContext.setSendZuulResponse(Boolean.FALSE);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
