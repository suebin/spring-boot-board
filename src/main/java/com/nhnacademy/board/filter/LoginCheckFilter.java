package com.nhnacademy.board.filter;

import com.nhnacademy.board.config.CommonPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        CommonPropertiesConfig config = (CommonPropertiesConfig) wac.getBean("commonPropertiesConfig");
        excludeUrls.addAll(config.getExcludeUrls());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        request.getRequestURI();

        if(!isExcludeUrl(request.getRequestURI())){
            HttpSession session = request.getSession(false);
            if(Objects.isNull(session)){
                response.sendRedirect("/login");
                return;
            }
        }
        log.info("login-check-filter:{}", request.getRequestURI() );
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isExcludeUrl(String path){
        long count = excludeUrls.stream().filter(path::contains).count();
        return count > 0;
    }
}
