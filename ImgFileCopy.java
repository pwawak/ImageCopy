import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

class ImgFileCopy {

	ImgFileList	ifl;
	
	public	ImgFileCopy( ImgFileList fileList) {
		ifl = fileList;
	}
	
	public	int	process() {
		Iterator<ImgFileEntry>	it = ifl.getIterator();
		int	retCode = 0;
		
		while( it.hasNext() ) {
			ImgFileEntry	ife = it.next();			// Kolejny plik do skopiowania.
			
			// Ignore existing files - no need to copy
			// TODO eliminate unnecesary call, use imageFileEntry property instead
			if ( Files.exists( ife.getDestFilePath() )) {
				System.out.println( "Ignoring existing file..." + ife.getDestFilePath() );
				continue;
			}
			
			// Create nonexisting directories on the destination path
			if ( ! Files.exists( ife.getDestFilePath().getParent() ) )
				try {
					Files.createDirectories( ife.getDestFilePath().getParent() );
				} catch (IOException e1) {
					System.out.println( "Unable to create directory ... " + ife.getDestFilePath().getParent() );
					continue;
				}
				
			// Copy file
			try {
				// TODO convert printouts to logging
				System.out.print( "Copying file... " + ife.getSrcFilePath() );
				Files.copy( ife.getSrcFilePath(), ife.getDestFilePath() );
				System.out.println( "   ---> copied successfully to .... " + ife.getDestFilePath() );
			} catch (IOException e) {
				System.out.println( "   ---> not copied due to error: " + e.getMessage() );
				retCode = -1;
			}
		}
		
		return retCode;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
