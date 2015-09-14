package com.ax.demo.health;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;
import com.ax.demo.HipsterStore;
import com.codahale.metrics.health.HealthCheck.Result;

public class HipsterServiceHealthCheckTest {

	HipsterStore store = mock(HipsterStore.class);

	@Test
	public void testCheckHealthy() throws Exception {
		
		//given store is running
		when(store.isRunning()).thenReturn(true);
		
		//then healthcheck is healthy
		assertEquals(Result.healthy().isHealthy(), getHealthFor(store));
	}

	@Test
	public void testCheckUnHealthy() throws Exception {
		
		//given store is not running
		when(store.isRunning()).thenReturn(false);
		
		//then healthcheck is healthy
		assertEquals(Result.unhealthy("foo").isHealthy(), getHealthFor(store));
	}

	private static boolean getHealthFor(HipsterStore store) throws Exception {
		return new HipsterServiceHealthCheck(store).check().isHealthy();
	}
	
}
