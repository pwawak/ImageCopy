
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

	private	ArrayList<ImgFileEntry>	ifel = new ArrayList<ImgFileEntry> ();
	
	private	LocalDate	getFileCreationDate ( Path p ) throws IOException {
		BasicFileAttributes fileAttribs = Files.readAttributes(p, BasicFileAttributes.class);
		Instant	ft = fileAttribs.lastModifiedTime().toInstant();
		ZonedDateTime	zd = ZonedDateTime.ofInstant(ft, ZoneOffset.UTC);
		
		return zd.toLocalDate();
	}
	
	public	ImgFileList ( String srcPathToDirectory, String destPathToDirectory, SubdirGen sdir ) throws IOException {
		// Fill table with list of file entries based on the contents of source directory

		try ( Stream<Path> entries = Files.list( Paths.get( srcPathToDirectory ) )){
			Iterator<Path>	iter = entries.iterator();
			
			while( iter.hasNext() ) {
				Path	filePath = iter.next();								// path to next file in the source directory

				// TODO add list of ignored subdirectories to be shown to the user
				if ( Files.isDirectory( filePath ))
					continue;												// omit directories
				
				String	fileName = filePath.getFileName().toString();		// next file name from the source directory
				
				// Combine destination path with subdirectory generated from file date.
				Path	destFilePath = Paths.get( destPathToDirectory, sdir.subdirBasedOnPattern( getFileCreationDate ( filePath ))); 

				ifel.add( ( new ImgFileEntry ( fileName, srcPathToDirectory, destFilePath.toString() ) ));
			}
		}
		
/*		for( ImgFileEntry e : ifel )
			System.out.println( e.toString() );*/
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
