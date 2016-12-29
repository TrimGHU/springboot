
package com.hg.sb_helloworld;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.hg.sb_helloworld.web.UserController;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = MockServletContext.class )
@WebAppConfiguration
public class UserControllerTest
{

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception
    {
        mvc = MockMvcBuilders.standaloneSetup( new UserController() ).build();
    }

    @Test
    @Transactional
    @Rollback( true )
    public void userTest() throws Exception
    {
        RequestBuilder request = MockMvcRequestBuilders.get( "/users/userProfile/hugui" );

        mvc.perform( ((MockHttpServletRequestBuilder)request).accept( MediaType.APPLICATION_JSON ) )
                .andExpect( status().is4xxClientError() );

        request = MockMvcRequestBuilders.get( "/users/" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect( content().string( equalTo( "[]" ) ) );

        request = MockMvcRequestBuilders.post( "/users/" ).param( "id", "1" ).param( "userName", "hugui" )
                .param( "age", "1" ).param( "gender", "1" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect( content().string( equalTo( "success" ) ) );

        request = MockMvcRequestBuilders.get( "/users/1" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect(
                content().string( equalTo( "{\"id\":1,\"userName\":\"hugui\",\"age\":1,\"gender\":true}" ) ) );

        request = MockMvcRequestBuilders.put( "/users/1" ).param( "userName", "trim.ghu" ).param( "age", "2" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect( content().string( equalTo( "success" ) ) );

        request = MockMvcRequestBuilders.get( "/users/1" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect(
                content().string( equalTo( "{\"id\":1,\"userName\":\"trim.ghu\",\"age\":2,\"gender\":true}" ) ) );

        request = MockMvcRequestBuilders.delete( "/users/1" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect( content().string( equalTo( "success" ) ) );

        request = MockMvcRequestBuilders.get( "/users/" );
        mvc.perform( request ).andExpect( status().isOk() ).andExpect( content().string( equalTo( "[]" ) ) );
    }
}
