
package com.hg.sb_helloworld.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hg.sb_helloworld.entity.User;

@Mapper
public interface UserMapper
{
    int deleteByPrimaryKey( Long id );

    int insert( User record );

    int insertSelective( User record );

    User selectByPrimaryKey( Long id );

    int updateByPrimaryKeySelective( User record );

    int updateByPrimaryKey( User record );

    @Select( "SELECT * FROM USERS WHERE USER_NAME = #{name}" )
    User findByUserName( @Param( "name" ) String name );

    @Insert( "INSERT INTO USERS(USER_NAME, AGE) VALUES(#{name}, #{age})" )
    int insert( @Param( "name" ) String name, @Param( "age" ) Integer age );
    
    @Update( "UPDATE USERS SET USER_NAME = #{userName} , AGE = #{age} , gender = #{gender} WHERE ID = #{id}")
    int update(User record );
}