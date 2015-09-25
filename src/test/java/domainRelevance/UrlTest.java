package domainRelevance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UrlTest {
	
	@Test
	public void testUpdate() throws Exception {
		// given
		Url url = new Url(1);
		
		// when
		url.update(1, true);
		url.update(2, false);
		url.update(3, false);
		url.update(4, true);
		
		// then
		assertThat(url.getNumOfVisits(), is(4));
		assertThat(url.getTimesChanged(), is(2));
		assertThat(url.getLastVisitCycle(), is(4));
	}
	
	@Test
	public void testGetChoChangeRate() throws Exception {
		// given
		Url url = new Url(1);
		
		// when
		url.update(1, true);
		url.update(2, false);
		url.update(3, false);
		url.update(4, true);
		
		// then
		assertThat(url.getChoChangeRate(), is(closeTo(0.587786665, 0.01)));
	}

	@Test
	public void testName() throws Exception {
		
	}

}
