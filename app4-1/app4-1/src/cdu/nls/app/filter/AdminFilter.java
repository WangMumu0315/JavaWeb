package cdu.nls.app.filter;

import cdu.nls.app.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "adminFilter", urlPatterns = "/reply")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && user.getIsAdmin()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("login.do");
        }

    }
}
