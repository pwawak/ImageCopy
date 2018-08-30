package ImageCopy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class JavaTest {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Path	pth = Paths.get( "d:\\test\\2016\\5DSR\\2016-01-17\\a.txt" ).getParent();
		Path	root = pth.getRoot();
		Path	curPath = root;
		
		if( ! Files.exists( pth ) ) {
			Files.createDirectories( pth );
		}
		
		System.out.println( "Root .... " + root.toString() );
		
		Iterator<Path>	pit = pth.iterator();
		
		while( pit.hasNext() ) {
			curPath = Paths.get( curPath.toString(), pit.next().toString() );
			
			System.out.println( curPath.toString() + " ....... is directory ? " + Files.isDirectory( curPath ) );
		}
		
		
/*		BasicFileAttributes fileAttribs = Files.readAttributes(Paths.get("d:\\test\\a.txt"), BasicFileAttributes.class);
		Instant	ft = fileAttribs.creationTime().toInstant();
		ZonedDateTime	zd = ZonedDateTime.ofInstant(ft, ZoneOffset.UTC); 
		System.out.println("a.txt => " + DateTimeFormatter.ISO_LOCAL_DATE.format( zd ) );*/
	}
}
