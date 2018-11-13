package com.hunkey.Fitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntkey.rx.commons.utils.redis.RedisClusterUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class HTTPBearerAuthorizeAttribute implements Filter{

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
        HttpServletRequest httpRequest = (HttpServletRequest)request;
//        String auth = httpRequest.getHeader("Authorization");
//        String auth ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDc4NzI4MDcsInN1YiI6eyJlcGVvVGVsIjoiMTg3NTY1NjQzMjIiLCJlcGVvTmFtZUVuIjoiQnJ1Y2UiLCJlcGVvTGFzdE5hbWUiOiLlhq8iLCJlcGVvTmFtZUNuIjoi5biD6bKB5pavIiwiZXBlb05hbWVOaSI6IuWwj-W4gyIsImVwZW9DYXJkTm8iOiIyMTIxMzEzMSIsImVwZW9Db2RlIjoiNmEzOGY0YWIxMTAyNDNhMmI0ZmFiN2MyYjBkMmQzZDciLCJlcGVvTWFpbCI6IjE4MzAxMjMxM0BxcS5jb20iLCJlcGVvR2VuZGVyIjoiIiwiZXBlb0Zpc3ROYW1lIjoi5biDIn0sImV4cCI6MTUwODQ3NzYwN30.aYrmxdpkRG9bVL0Zu4U0T61CKjnqbpeobLWr50ht9eU";
//        if (auth != null){
//            if (EcoSystemUtil.parseJWT(auth) !=null){
//                Map<String,String> usermap = EcoSystemUtil.ifAuthUsable(auth);
//                String userId = usermap.get("epeoCode");
//                HttpSession session = httpRequest.getSession();
//                session.setAttribute("userId","cjqtest");
//                chain.doFilter(request, response);
//                return;
//            }
//        }
        String uri = ((HttpServletRequest) request).getRequestURI();
        System.out.println(uri+"------------------------");
        if(uri.indexOf("/jobPosition/loadJobPosition")!=-1 || uri.indexOf("/jobPosition/creatSession")!=-1){
            System.out.println(uri+"11111111111111111111");
            chain.doFilter(request, response);
            return;
        }

        //拦截处理逻辑:session中的值和redis中比较
        //获取redis中的session值
        String cid = request.getParameter("complanyId");
        String jod = request.getParameter("jobId");
        String emd = request.getParameter("employId");
        RedisClusterUtils rcu = new RedisClusterUtils(" 10.3.98.153:7000,10.3.98.153:7001,10.3.98.154:7002,10.3.98.154:7003,10.3.98.155:7004,10.3.98.155:7005");
        String redisSess = rcu.getValue(cid+"_"+jod+"_"+emd);

        //获取request中的session值
        HttpSession session = httpRequest.getSession();
        //如果session不一致则调到登录页
        if(null == redisSess || !session.getAttribute("userId").equals(redisSess)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("请重新登录！");
            httpResponse.getWriter().write(mapper.writeValueAsString(result));
            return;
        }else{
                chain.doFilter(request, response);
                return;
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}