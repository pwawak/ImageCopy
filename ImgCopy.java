import java.io.IOException;
import java.util.logging.*;

/*
 * Copy images between directories.
 * Args:
 * 		[0] - source folder (to copy)
 * 		[1] - dest folder
 * 		[2] - method to generate subdirs for copied files
 * 		[3]	- camera model
 */
public class ImgCopy {

	public static void main(String[] args) throws IOException {
		// Set up log
		Handler fh = new FileHandler("imgcopy.log");
		Logger.getLogger("imgcopy").addHandler(fh);
		Logger.getLogger("imgcopy").setLevel(Level.FINEST);

		Logger lg = Logger.getLogger("imgcopy");

		MainForm	frm = new MainForm( lg );

		// TODO if no params given open up UI for the user to choose
		if ( args.length != 4 ) {
			lg.log( Level.SEVERE, "Incorrect number of arguments" );
			System.exit(-1);			
		}
			
		// Analyse parameters
		ExecParams ep = new ExecParams( args[0], args[1], args[2].toUpperCase(), args[3].toUpperCase() );

		// Check parameters
		if ( ! ep.isValidState() ) {
			System.out.println( ep.getErrorMessages() );
			System.exit( -1 );
		}
		
		// Build file list for copying
		ImgFileList	ifl = new ImgFileList( args[0], args[1], ep.getSubdirNameGenerator() );

		frm.setSrcFileList( ifl );
		frm.show();

		//copy files
		//ImgFileCopy	ifc = new ImgFileCopy( ifl );
		
		//int	exitCode = ifc.process();
		
		//System.exit( 0 );
	}

}
