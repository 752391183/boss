package com.example.bosstrain.intercreptor;

import com.example.bosstrain.component.RequestComponent;
import com.example.bosstrain.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
  卖家可以执行买家的操作，而买家不能执行卖家的操作
 */
@Component
public class BuyerIntercreptor implements HandlerInterceptor {
    @Autowired
    private RequestComponent requestComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (requestComponent.getRole() != Role.BUYER || requestComponent.getRole() != Role.SELLER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限访问");
        }
        return true;
    }
}
