package com.ttn.linkSharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        String errorNumber="";
        int httpErrorCode = getErrorCode(httpRequest);
        switch (httpErrorCode) {
            case 400: {
                errorNumber = "400";
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorNumber = "401";
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorNumber = "404";
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorNumber = "500";
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            default: {
//                errorPage.addObject("errorNumber", "404");
                errorMsg = "Http Error Code: 404.  Server Error";
            }
        }
        errorPage.addObject("errorNumber", errorNumber);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
