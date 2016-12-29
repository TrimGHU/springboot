
package com.hg.sb_helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hg.sb_helloworld.dao.UserRepository;
import com.hg.sb_helloworld.service.UserService;

import junit.framework.Assert;

@SuppressWarnings( "deprecation" )
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringApplicationConfiguration( classes = Application.class )
public class UserMongoTest
{

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void userTest(){
        userService.goToSchool();
        Assert.assertEquals( 2, userRepository.findAll().size() );
        Assert.assertEquals( 20, userRepository.findByUserName( "U1" ).getAge().intValue() );
    }
    
}
