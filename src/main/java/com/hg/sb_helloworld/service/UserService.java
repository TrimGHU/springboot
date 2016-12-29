
package com.hg.sb_helloworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.sb_helloworld.dao.UserRepository;
import com.hg.sb_helloworld.entity.User;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    public void goToSchool()
    {
        User u1 = userRepository.findByUserName( "U1" );
        User u2 = userRepository.findByUserName( "U2" );

        if ( u1 == null )
        {
            userRepository.save( new User(1L, "U1", 20, true ) );
        }
        
        if ( u2 == null )
        {
            userRepository.save( new User(2L,  "U2", 24, true ) );
        }
    }

}
