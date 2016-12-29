
package com.hg.sb_helloworld.aspect;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mongodb.BasicDBObject;

@Aspect
@Order( 5 )
@Component
public class LogAspect
{

    private Logger logger = Logger.getLogger( "mongodb" );

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut( "execution(public * com.hg.sb_helloworld.web..*.*(..))" )
    public void webLog()
    {
    }

    @Before( "webLog()" )
    public void doBefore( JoinPoint joinPoint ) throws Throwable
    {
        startTime.set( System.currentTimeMillis() );

        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取要记录的日志内容
        BasicDBObject logInfo = getBasicDBObject( request, joinPoint );
        logger.info( logInfo );
    }

    @AfterReturning( returning = "ret", pointcut = "webLog()" )
    public void doAfterReturning( Object ret ) throws Throwable
    {
        // 处理完请求，返回内容
        logger.info( "RESPONSE : " + ret );
        logger.info( "SPEND TIME : " + (System.currentTimeMillis() - startTime.get()) );
    }

    private BasicDBObject getBasicDBObject( HttpServletRequest request, JoinPoint joinPoint )
    {
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append( "requestURL", request.getRequestURL().toString() );
        r.append( "requestURI", request.getRequestURI() );
        r.append( "queryString", request.getQueryString() );
        r.append( "remoteAddr", request.getRemoteAddr() );
        r.append( "remoteHost", request.getRemoteHost() );
        r.append( "remotePort", request.getRemotePort() );
        r.append( "localAddr", request.getLocalAddr() );
        r.append( "localName", request.getLocalName() );
        r.append( "method", request.getMethod() );
        r.append( "headers", getHeadersInfo( request ) );
        r.append( "parameters", request.getParameterMap() );
        r.append( "classMethod",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() );
        r.append( "args", Arrays.toString( joinPoint.getArgs() ) );
        return r;
    }

    @SuppressWarnings( {"unchecked", "rawtypes"} )
    private Map<String, String> getHeadersInfo( HttpServletRequest request )
    {
        Map<String, String> map = new HashMap();
        Enumeration headerNames = request.getHeaderNames();
        while ( headerNames.hasMoreElements() )
        {
            String key = (String)headerNames.nextElement();
            String value = request.getHeader( key );
            map.put( key, value );
        }
        return map;
    }
}
