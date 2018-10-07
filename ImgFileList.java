
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Piotr
 *
 */
final class ImgFileList {
	private String		srcPath, destPath;
	private	SubdirGen	sdir;
	private	ArrayList<ImgFileEntry>	ifel = new ArrayList<ImgFileEntry> ();
	
	private	LocalDate	getFileCreationDate ( Path p ) throws IOException {
		BasicFileAttributes fileAttribs = Files.readAttributes(p, BasicFileAttributes.class);
		Instant	ft = fileAttribs.lastModifiedTime().toInstant();
		ZonedDateTime	zd = ZonedDateTime.ofInstant(ft, ZoneOffset.UTC);
		
		return zd.toLocalDate();
	}
	
	public	ImgFileList ( String srcPathToDirectory, String destPathToDirectory, SubdirGen sdir ) throws IOException {
		// Fill table with list of file entries based on the contents of source directory

		this.srcPath = srcPathToDirectory;
		this.destPath = destPathToDirectory;
		this.sdir = sdir;

		setFileList();
	}

	private	void setFileList() throws IOException {
		ifel.clear();

		try ( Stream<Path> entries = Files.list( Paths.get( srcPath ) )){
			Iterator<Path>	iter = entries.iterator();
			
			while( iter.hasNext() ) {
				Path	filePath = iter.next();								// path to next file in the source directory

				// TODO add list of ignored subdirectories to be shown to the user
				if ( Files.isDirectory( filePath ))
					continue;												// omit directories
				
				String	fileName = filePath.getFileName().toString();		// next file name from the source directory
				
				// Combine destination path with subdirectory generated from file date.
				Path	destFilePath = Paths.get( destPath, sdir.subdirBasedOnPattern( getFileCreationDate ( filePath )));

				ifel.add( ( new ImgFileEntry ( fileName, srcPath, destFilePath.toString() ) ));
			}
		}
	}

	public String	getSrcPath(){
		return srcPath;
	}
	public String	getDestPath(){
		return destPath;
	}

	public void 	setSrcPath( String pth ) throws IOException {
		srcPath = pth;
		setFileList();
	}
	public void 	setDestPath( String pth ) throws IOException {
		destPath = pth;
		setFileList();
	}
	public	Iterator<ImgFileEntry>	getIterator () {
		return	ifel.iterator();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
