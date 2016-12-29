
package com.hg.sb_helloworld.job;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SuppressWarnings( "deprecation" )
@Component
public class ScheduledTasks
{
    /**
     * rate rule link : http://spring.io/guides/gs/scheduling-tasks/
     */
    @Scheduled( fixedRate = 5000 )
    public void printTimeOnConsole()
    {
        System.out.println( Calendar.getInstance().getTime().toLocaleString() );
    }

}
