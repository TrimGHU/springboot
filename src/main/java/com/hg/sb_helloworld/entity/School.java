
package com.hg.sb_helloworld.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "schools" )
public class School implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column( nullable = false )
    private String name;

    @Column( nullable = false )
    private Integer totalStudents;

    public School(){
        super();
    }
            
    public School( String name,Integer totalStudents )
    {
        super();
        this.name = name;
        this.totalStudents = totalStudents;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getTotalStudents()
    {
        return totalStudents;
    }

    public void setTotalStudents( Integer totalStudents )
    {
        this.totalStudents = totalStudents;
    }

}
