package cn.tcu.librarymanagement.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String uri = request.getRequestURI();
        // 放行登录、注册、静态资源
        if (uri.contains("/login") || uri.contains("/register") || uri.contains("/static/")) {
            return true;
        }
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

