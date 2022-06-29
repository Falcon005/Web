package by.ashurmatov.anime.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "PreIndexFilter", urlPatterns = "/index.jsp")
public class PreIndexFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(PreIndexFilter.class);
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        logger.info("-------> Session in PreIndexFilter " + (session != null ? session.getId() : "no session created"));
        chain.doFilter(request, response);
    }
}
