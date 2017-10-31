package br.com.ga.client.security;

import br.com.ga.util.Consts;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.equals("/GAClient/"))
                resp.sendRedirect("/GAClient/index.xhtml");
            else if (reqURI.contains("/index.xhtml")
                    || reqURI.contains("/login.xhtml")
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource")
                    || (ses != null && ses.getAttribute(Consts.PARAM_USER_SESSION) != null))
                chain.doFilter(request, response);
            else
                resp.sendRedirect(reqt.getContextPath() + "/index.xhtml#login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}