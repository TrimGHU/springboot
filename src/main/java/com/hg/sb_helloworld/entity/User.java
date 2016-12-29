
package com.hg.sb_helloworld.entity;

import java.io.Serializable;

import javax.persistence.Id;

public class User implements Serializable
{
    @Id
    private Long id;

    private String userName;

    private Integer age;

    private Boolean gender;

    private static final long serialVersionUID = 1L;

    public User( Long id , String userName,Integer age,Boolean gender )
    {
        super();
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }

    public User()
    {
        super();
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge( Integer age )
    {
        this.age = age;
    }

    public Boolean getGender()
    {
        return gender;
    }

    public void setGender( Boolean gender )
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( getClass().getSimpleName() );
        sb.append( " [" );
        sb.append( "Hash = " ).append( hashCode() );
        sb.append( ", id=" ).append( id );
        sb.append( ", userName=" ).append( userName );
        sb.append( ", age=" ).append( age );
        sb.append( ", gender=" ).append( gender );
        sb.append( ", serialVersionUID=" ).append( serialVersionUID );
        sb.append( "]" );
        return sb.toString();
    }
}