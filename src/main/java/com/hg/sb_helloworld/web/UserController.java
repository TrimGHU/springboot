
package com.hg.sb_helloworld.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hg.sb_helloworld.entity.User;
import com.hg.sb_helloworld.exception.GeneralException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/users" )
public class UserController
{
    static Map<Long, User> users = Collections.synchronizedMap( new HashMap<Long, User>() );

    @ApiOperation( value = "获取用户列表", notes = "" )
    @RequestMapping( value = {""}, method = RequestMethod.GET )
    public List<User> getUserList()
    {
        List<User> r = new ArrayList<User>( users.values() );
        return r;
    }

    @ApiOperation( value = "创建用户", notes = "根据User对象创建用户" )
    @ApiImplicitParam( name = "user", value = "用户详细实体user", required = true, dataType = "User" )
    @RequestMapping( value = "", method = RequestMethod.POST )
    public String postUser( User user )
    {
        users.put( user.getId(), user );
        return "success";
    }

    @ApiOperation( value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息" )
    @ApiImplicitParam( name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long" )
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public User getUser( @PathVariable Long id ) throws GeneralException
    {
        if ( !users.containsKey( id ) )
        {
            throw new GeneralException( "Cannot find the id" );
        }
        return users.get( id );
    }

    @ApiOperation( value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long" ),
            @ApiImplicitParam( name = "user", value = "用户详细实体user", required = true, dataType = "User" )} )
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public String putUser( @PathVariable Long id, User user )
    {
        User u = users.get( id );
        if ( !StringUtils.isEmpty( user.getUserName() ) )
        {
            u.setUserName( user.getUserName() );
        }
        if ( !StringUtils.isEmpty( user.getAge() ) )
        {
            u.setAge( user.getAge() );
        }
        if ( !StringUtils.isEmpty( user.getGender() ) )
        {
            u.setGender( user.getGender() );
        }
        users.put( id, u );
        return "success";
    }

    @ApiOperation( value = "删除用户", notes = "根据url的id来指定删除对象" )
    @ApiImplicitParam( name = "id", value = "用户ID", paramType = "path", required = true, dataType = "String" )
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public String deleteUser( @PathVariable( value = "id" ) Long id )
    {
        users.remove( id );
        return "success";
    }
}
