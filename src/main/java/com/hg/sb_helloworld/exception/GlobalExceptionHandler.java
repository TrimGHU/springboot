
package com.hg.sb_helloworld.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hg.sb_helloworld.entity.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler
{

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler( value = Exception.class )
    public ModelAndView defaultErrorHandler( HttpServletRequest req, Exception e ) throws Exception
    {
        ModelAndView mav = new ModelAndView();
        mav.addObject( "exception", e );
        mav.addObject( "url", req.getRequestURL() );
        mav.setViewName( DEFAULT_ERROR_VIEW );
        return mav;
    }

    @ExceptionHandler( value = GeneralException.class )
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler( HttpServletRequest req, GeneralException e ) throws Exception
    {
        ErrorInfo<String> r = new ErrorInfo<String>();
        r.setMessage( e.getMessage() );
        r.setCode( ErrorInfo.ERROR );
        r.setData( "Some Data" );
        r.setUrl( req.getRequestURL().toString() );
        return r;
    }

}
