
package com.hg.sb_helloworld.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hg.sb_helloworld.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long>
{

    School findByName( String name );

    School findByNameAndTotalStudents( String name, Integer totalCount );

    @Query( "from School s where s.name=:name" )
    School findSchool( @Param( "name" ) String name );
}
