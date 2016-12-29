
package com.hg.sb_helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hg.sb_helloworld.dao.SchoolRepository;
import com.hg.sb_helloworld.entity.School;

import junit.framework.Assert;

@SuppressWarnings( "deprecation" )
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringApplicationConfiguration( classes = Application.class )
public class SchoolTest
{

    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    @Transactional
    @Rollback( true )
    public void schoolTest()
    {
        schoolRepository.save( new School( "AAA", 10 ) );
        schoolRepository.save( new School( "BBB", 20 ) );
        schoolRepository.save( new School( "CCC", 30 ) );
        schoolRepository.save( new School( "DDD", 40 ) );
        schoolRepository.save( new School( "EEE", 50 ) );
        schoolRepository.save( new School( "FFF", 60 ) );
        schoolRepository.save( new School( "GGG", 70 ) );
        schoolRepository.save( new School( "HHH", 80 ) );
        schoolRepository.save( new School( "III", 90 ) );
        schoolRepository.save( new School( "JJJ", 100 ) );
        // 测试findAll, 查询所有记录
        Assert.assertEquals( 10, schoolRepository.findAll().size() );
        // 测试findByName, 查询姓名为FFF的School
        Assert.assertEquals( 60, schoolRepository.findByName( "FFF" ).getTotalStudents().intValue() );
        // 测试findSchool, 查询姓名为FFF的School
        Assert.assertEquals( 60, schoolRepository.findSchool( "FFF" ).getTotalStudents().intValue() );
        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的School
        Assert.assertEquals( "FFF", schoolRepository.findByNameAndTotalStudents( "FFF", 60 ).getName() );
        // 测试删除姓名为AAA的School
        schoolRepository.delete( schoolRepository.findByName( "AAA" ) );
        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals( 9, schoolRepository.findAll().size() );

        schoolRepository.delete( schoolRepository.findAll() );

        Assert.assertEquals( 0, schoolRepository.findAll().size() );
    }

}
