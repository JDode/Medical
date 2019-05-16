package com.ryan.sms.medical.interceptor;

import com.ryan.sms.medical.utils.JsonData;
import com.ryan.sms.medical.utils.JsonUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Integer user_type = (Integer) request.getSession().getAttribute("USER_TYPE");
        Object login_user = request.getSession().getAttribute("LOGIN_USER");
        if (login_user == null || user_type == null) {
            response.setStatus(403);
            JsonUtil.outJson(response,new JsonData().build(-110,"你当前身份不是管理员无法继续操作！！！"));
            return false;
        } else {
            if (user_type == 2 && login_user != null) {
                return true;
            }
        }
        return false;
        //return  true;
    }
}