package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.TestAllureListener;

@Listeners(value = TestAllureListener.class)
public class SampleTest extends BaseTest {

	@Test(groups = { "Sanity", "Regression" })
	public void sampleTest1() {
		System.out.println("Running sampleTest1");
		assertTrue(false, "Failed SampleTest1");
	}

	@Test(groups = { "Sanity" })
	public void sampleTest2() {
		System.out.println("Running sampleTest2");
	}

	@Test(groups = { "Regression" })
	public void sampleTest3() {
		System.out.println("Running sampleTest3");
	}

	@Test(groups = { "Functional" })
	public void sampleTest4() {
		System.out.println("Running sampleTest4");
	}
}
