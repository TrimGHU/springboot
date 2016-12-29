
package com.hg.sb_helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hg.sb_helloworld.dao.UserMapper;
import com.hg.sb_helloworld.entity.User;

import junit.framework.Assert;

@SuppressWarnings( "deprecation" )
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringApplicationConfiguration( classes = Application.class )
public class UserMyBatisTest
{

    @Autowired
    private UserMapper userMapper;
    
    @Test
    @Transactional
    @Rollback(true)
    public void userTest(){
        User u1 = userMapper.findByUserName( "U1" );
        Assert.assertEquals( 10 , u1.getAge().intValue() );

        userMapper.insert( "U3", 30 );
        User u3 = userMapper.findByUserName( "U3" );
        Assert.assertEquals( 30 , u3.getAge().intValue() );
        
        u1.setAge( 25 );
        userMapper.update( u1 );
    }
    
}
