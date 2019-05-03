package tedtalk.executer;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import tedtalk.emailer.ScheduledEmailExecutor;;


public class ScheduledExecuterTest {

	private ScheduledEmailExecutor exe;
	@Before
	public void setup() {
	//add in setup	
		exe = new ScheduledEmailExecutor();
	}
	
	@Test
	public void testStartScheduleTask() {
		
	}
}
