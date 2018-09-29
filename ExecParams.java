/**
 * 
 */

import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Piotr
 * Obs�uga parametr�w wykonania ImageCopy.
 */
class ExecParams {
	
	private	String	srcPathName;
	private	String	destPathName;
	private	Path	srcPath;
	private	Path	destPath;
	private	Logger	lg;
	
	private	boolean	srcPathInvalid, destPathInvalid, timePatternInvalid;

	private	SubdirGen	sdirGenerator;
	
	public	ExecParams (String srcFolder, String destFolder, @NotNull String subpathFormat, String model)
	{
		SubdirGen.SubdirPattern	patternForSubdir;

		lg = Logger.getLogger("imgcopy");

		setSrcFldName(srcFolder);
		setDestPathName(destFolder);
		
		try {
			patternForSubdir = Enum.valueOf(SubdirGen.SubdirPattern.class, subpathFormat.toUpperCase());
		}
		catch( IllegalArgumentException e) {
			patternForSubdir = SubdirGen.SubdirPattern.EMPTY;

			lg.log( Level.SEVERE, "unknown pattern for subdirectory generation");
		}
		
		timePatternInvalid = false;
		
		// Choose subdirectory name generator based on pattern
		
		switch (patternForSubdir) {
			case YMD :
				sdirGenerator = ( LocalDate fileDate ) -> Paths.get( DateTimeFormatter.ofPattern( "yyyy" ).format( fileDate ),
						model,
						DateTimeFormatter.ISO_LOCAL_DATE.format( fileDate )).toString();
				break;
	
			default:
				timePatternInvalid = true;
				break;
		}
	}
	
	public SubdirGen getSubdirNameGenerator () {
		return sdirGenerator;
	}
	
	public	String	buildDestPathForFile (String fileName, LocalDate fileCreationDate)
	{
		// generate path element from date
		String	s = DateTimeFormatter.ISO_LOCAL_DATE.format( fileCreationDate );
		
		return Paths.get(destPath.toString(), s).toString();
	}
	
	public	ArrayList<String>	getErrorMessages() {
		ArrayList<String>	sal = new ArrayList<String>();
		
		if ( srcPathInvalid ) {
			sal.add( "Invalid source path for files or destination does not point to directory");
		}
		if ( destPathInvalid ) {
			sal.add( "Invalid destination path for files or destination does not point to directory");
		}
		if ( timePatternInvalid ) {
			sal.add( "Unknown pattern for destination path subdirectory");
		}
		
		return sal;	
	}

	public	boolean	isValidState() {
		return !( isSrcPathInvalid() || isDestPathInvalid() || isTimePatternInvalid() );
	}
	
	public	boolean isSrcPathInvalid() {
		return srcPathInvalid;
	}

	public	boolean isDestPathInvalid() {
		return destPathInvalid;
	}

	public	boolean isTimePatternInvalid() {
		return timePatternInvalid;	
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	}

	private String getSrcPathName() {
		return srcPathName;
	}

	private void setSrcFldName(String srcFldName) {
		this.srcPathName = srcFldName;
		this.srcPath     = Paths.get(this.srcPathName);
		
		this.srcPathInvalid = !Files.isDirectory(srcPath);

		if ( srcPathInvalid )
			lg.log( Level.SEVERE, "Invalid source path for files or destination does not point to directory");
	}

	private String getDestPathName() {
		return destPathName;
	}

	private void setDestPathName(String destFldName) {
		this.destPathName = destFldName;
		this.destPath     = Paths.get(this.destPathName);
		
		this.destPathInvalid = !Files.isDirectory(destPath);

		if ( destPathInvalid )
			lg.log( Level.SEVERE, "Invalid destination path for files or destination does not point to directory");
	}
}
