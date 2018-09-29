import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ImgFileListTest {

	private	ExecParams	ep;
	private	ImgFileList	ifl;
	
	@Before
	public void setUp() throws Exception {
		ep  = new ExecParams  ( "d:\\test", "d:\\test", SubdirGen.SubdirPattern.YMD.toString(), "5DSR" );
		ifl = new ImgFileList ( "d:\\test", "d:\\test", ep.getSubdirNameGenerator() );
	}

	@Test
	public void testGetIterator() {
		assertTrue ( "Lista plik�w zawiera tylko plik a.txt", 
				ifl.getIterator().hasNext() && ifl.getIterator().next().getFileName().equals("a.txt"));
	}

}
