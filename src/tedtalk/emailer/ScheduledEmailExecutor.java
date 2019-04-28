package tedtalk.emailer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledEmailExecutor {
	private final ScheduledExecutorService scheduler = Executors
	        .newScheduledThreadPool(1);

	    public void startScheduleTask() {
	    /**
	    * not using the taskHandle returned here, but it can be used to cancel
	    * the task, or check if it's done (for recurring tasks, that's not
	    * going to be very useful)
	    */
	    final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
	        new Runnable() {
	            public void run() {
	                try {
	                    getDataFromDatabase();
	                }catch(Exception ex) {
	                    ex.printStackTrace(); //or loggger would be better
	                }
	            }
	        }, 0, 15, TimeUnit.MINUTES); //will execute the task with 0 delay, then wait for a duration of 15(it isn't 15 secs, it is just 15),
	    //TimeUnit needs to be changed if you want seconds/days. All together this will complete run the task every 15 minutes
	    }

	    private void getDataFromDatabase() {
	        System.out.println("getting data..."); // this is the task to ensure that it runs successfully
	        SendEmail email = new SendEmail();
	        email.sendEmail("jlandau2@ycp.edu");
	    }
/*
	    public static void main(String[] args) {
	        ScheduledEmailExecutor ste = new ScheduledEmailExecutor();	//this is an example of how to call the scheduler
	        ste.startScheduleTask();
	    } */
 
}
