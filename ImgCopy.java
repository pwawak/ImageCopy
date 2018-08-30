package ImageCopy;

import java.io.IOException;

public class ImgCopy {

	public static void main(String[] args) throws IOException {
		if ( args.length != 4 ) {
			System.err.println("Incorrect number of arguments");
			System.exit(-1);			
		}
			
		// Analyse parameters
		ExecParams	ep = new ExecParams( args[0], args[1], args[2].toUpperCase(), args[3].toUpperCase() );
		
		// Check parameters
		if ( ! ep.isValidState() ) {
			System.out.println( ep.getErrorMessages() );
			System.exit( -1 );
		}
		
		// Build file list for copying
		ImgFileList	ifl = new ImgFileList( args[0], args[1], ep.getSubdirNameGenerator() );
		
		//copy files
		ImgFileCopy	ipc = new ImgFileCopy( ifl );
		
		int	exitCode = ipc.process();
		
		System.exit( exitCode );
	}

}
