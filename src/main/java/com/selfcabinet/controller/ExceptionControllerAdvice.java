package com.selfcabinet.controller;

import com.selfcabinet.SelfCabinetApplication;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.model.Status;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private void setupErrorResponse(HttpServletResponse response, int status) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setStatus(status);
    }

    @ExceptionHandler(SelfCabinetException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, SelfCabinetException e) {
        setupErrorResponse(response, e.getHttpStatus());
        Status result = new Status();
        result.setMessage(e.getMessage());
        result.setUserMessage(e.getUserMessage());
        return result;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, ServletRequestBindingException e) {
        setupErrorResponse(response, 400);
        Status result = new Status();
        result.setMessage(e.getMessage());
        result.setUserMessage(null);
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Status exception(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException e) {
        setupErrorResponse(response, 400);
        Status result = new Status();
        result.setUserMessage(null);
        result.setMessage(e.getMessage());
        return result;
    }

}
