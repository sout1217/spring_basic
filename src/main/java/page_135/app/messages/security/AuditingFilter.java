package page_135.app.messages.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class AuditingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        long start = new Date().getTime();

        chain.doFilter(req, res);

        long elapsed = new Date().getTime()- start;

        HttpServletRequest request = (HttpServletRequest) req;

        logger.debug(String.format("요청[uri= %s, method= %s] completed in %d ms", request.getRequestURI(), request.getMethod(), elapsed));

    }
}
