package domainRelevance;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domainRelevance.Dataset.PageHistory.PageCycleProperties;

public class DatasetTest {

	@Test
	public void testDatasetMethods() throws Exception {
		// given
		List<PageCycleProperties> pageCycles = new ArrayList<>();
		Dataset dataset = new Dataset();
		
		// when
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(false, 0, 0));
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(false, 0, 0));
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(true, 0, 0));
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(true, 7, 0));
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(true, 10, 5));
		pageCycles.add(new Dataset.PageHistory.PageCycleProperties(true, 2, 1));
		Dataset.PageHistory pageHistory = new Dataset.PageHistory(pageCycles);
		dataset.add(pageHistory);
		
		// then
		assertThat(dataset.getPageHistory(0), is(pageHistory));
		assertThat(dataset.numOfCycles(), is(6));
		assertThat(dataset.numOfUrls(), is(1));
		assertThat(pageHistory.getNumOfCycles(), is(6));
		assertThat(pageHistory.getCycles(), is(pageCycles));
		assertThat(pageHistory.changedBetweenCycles(1,2), is(false));
		assertThat(pageHistory.changedBetweenCycles(1,3), is(true));
		assertThat(pageHistory.changedBetweenCycles(2,3), is(true));
		assertThat(pageHistory.changedBetweenCycles(4,5), is(true));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,2,false), is(0));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,4,false), is(7));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,6,false), is(19));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(4,5,false), is(10));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,2,true), is(0));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,4,true), is(0));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,5,true), is(5));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(1,6,true), is(6));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(4,5,true), is(5));
		assertThat(pageHistory.numOfNewLinksBetweenCycles(4,6,true), is(6));
	}

}
