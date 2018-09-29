import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ImgFileEntryTest {

	private	ImgFileEntry	ife;
	
	@Before
	public void setUp() throws Exception {
		ife = new ImgFileEntry( "a.txt", "d:\\test", "d:\\test" );
	}

	@Test
	public void testImgFileEntry() {
		assertTrue ( "Plik powinien istnie�", ife.isFileExistsInDestination() );
	}

}
