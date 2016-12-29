
package com.hg.sb_helloworld.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hg.sb_helloworld.entity.User;

public interface UserRepository extends MongoRepository<User, Long>
{

    User findByUserName( String userName );

}
