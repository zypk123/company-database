package com.huntkey.rx.commons.utils.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntkey.rx.commons.utils.eco.EcoSystemUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class HTTPBearerAuthorizeAttributeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

        Result result = new Result();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String auth = httpRequest.getHeader("Authorization");
//        String auth ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDc4NzI4MDcsInN1YiI6eyJlcGVvVGVsIjoiMTg3NTY1NjQzMjIiLCJlcGVvTmFtZUVuIjoiQnJ1Y2UiLCJlcGVvTGFzdE5hbWUiOiLlhq8iLCJlcGVvTmFtZUNuIjoi5biD6bKB5pavIiwiZXBlb05hbWVOaSI6IuWwj-W4gyIsImVwZW9DYXJkTm8iOiIyMTIxMzEzMSIsImVwZW9Db2RlIjoiNmEzOGY0YWIxMTAyNDNhMmI0ZmFiN2MyYjBkMmQzZDciLCJlcGVvTWFpbCI6IjE4MzAxMjMxM0BxcS5jb20iLCJlcGVvR2VuZGVyIjoiIiwiZXBlb0Zpc3ROYW1lIjoi5biDIn0sImV4cCI6MTUwODQ3NzYwN30.aYrmxdpkRG9bVL0Zu4U0T61CKjnqbpeobLWr50ht9eU";
        if (auth != null) {
            if (EcoSystemUtil.parseJWT(auth) != null) {
                Map<String, String> usermap = EcoSystemUtil.ifAuthUsable(auth);
                String userId = usermap.get("epeo_code");
                HttpSession session = httpRequest.getSession();
                session.setAttribute("epeo_code", userId);
                chain.doFilter(request, response);
                return;
            }
        }
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        result.setRetCode(Result.RECODE_UNLOGIN);
        result.setErrMsg("请重新登录！");
        httpResponse.getWriter().write(mapper.writeValueAsString(result));
        //httpResponse.sendRedirect("http:localhost:10001/user/getUserByEpeoCode");
        return;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}