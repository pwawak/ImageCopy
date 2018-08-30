package ImageCopy;

import static org.junit.Assert.*;
import ImageCopy.SubdirGen.SubdirPattern;

import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExecParamsTest {
	private	ExecParams	ep, ep1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ep  = new ExecParams ( "d:\\test", "d:\\test", SubdirPattern.YMD.toString(), "5DSR");
		ep1 = new ExecParams ( "d:\\test", "d:\\test", "Y2", "5DSR");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecParams() {
		assertFalse("Src path should be OK", ep.isSrcPathInvalid());
		assertFalse("Dest path should be OK", ep.isDestPathInvalid());
	}
	
	@Test
	public	void testBuildDestPathForFile() {
		assertTrue("Œcie¿ka do pliku OK", "d:\\test\\2016-10-16".equals( ep.buildDestPathForFile("", LocalDate.parse("2016-10-16") ) ) );
	}
	
	@Test
	public	void testGetErrorMessages() {
		assertTrue("Lista komunikatów powinna byæ pusta", ep.getErrorMessages().isEmpty() );

		assertFalse("Lista komunikatów nie powinna byæ pusta", ep1.getErrorMessages().isEmpty() );
	}
	
	@Test
	public	void testIsValidState() {
		assertTrue("Obiekt powinien byæ spójny", ep.isValidState() );
		assertFalse("obiekt ep1 powinien byæ niespójny", ep1.isValidState());
	}
}
