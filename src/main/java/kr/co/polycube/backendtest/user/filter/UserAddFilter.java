package kr.co.polycube.backendtest.user.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserAddFilter implements Filter {
    private static final Pattern CHARACTERS = Pattern.compile("[^a-zA-Z0-9?&=:/]");

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String uri = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && CHARACTERS.matcher(queryString).find()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "특수 문자는 허용되지 않습니다.");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
