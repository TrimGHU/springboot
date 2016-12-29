
package com.hg.sb_helloworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping( "/" )
public class IndexController
{

    @ApiOperation( value = "首页", notes = "" )
    @RequestMapping( value = "/index", method = RequestMethod.GET )
    public String index( ModelMap map )
    {
        map.put( "key", "hugui spring boot test" );
        return "index";
    }

    @ApiOperation( value = "错误首页", notes = "" )
    @RequestMapping( value = "/indexError", method = RequestMethod.GET )
    public String indexError( ModelMap map ) throws Exception
    {
        throw new Exception( "The is a test error page" );
    }
}
