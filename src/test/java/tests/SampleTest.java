package tests;

import org.testng.annotations.Test;
import qa.base.BaseTest;

public class SampleTest extends BaseTest {
	@Test
	public void sampleTest1() {
		System.out.println(Thread.currentThread().getId());
		System.out.println("Running sampleTest1");
	}
	
	@Test
	public void sampleTest2() {
		System.out.println(Thread.currentThread().getId());
		System.out.println("Running sampleTest2");
	}
	
	@Test
	public void sampleTest3() {
		System.out.println(Thread.currentThread().getId());
		System.out.println("Running sampleTest3");
	}
	
	@Test
	public void sampleTest4() {
		System.out.println(Thread.currentThread().getId());
		System.out.println("Running sampleTest4");
	}
}
