import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

final class ImgFileEntry {

	private	String	fileName;
	private	long	fileSize;
	private	Path	srcFilePath, destFilePath;
	private	boolean	fileExistsInDestination;
	
	public	ImgFileEntry ( String fileName, String srcFilePathName, String destFilePathName ) throws IOException {
		this.setFileName(fileName);
		this.setSrcFilePath(Paths.get( srcFilePathName, fileName ));
		this.setDestFilePath(Paths.get( destFilePathName, fileName ));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public long getFileSize() {
		return fileSize;
	}

	private	void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isFileExistsInDestination() {
		return fileExistsInDestination;
	}

	private void setFileExistsInDestination(boolean fileExistsInDestination) {
		this.fileExistsInDestination = fileExistsInDestination;
	}

	public	Path getSrcFilePath() {
		return srcFilePath;
	}

	private void setSrcFilePath(Path srcFilePath) throws IOException {
		this.srcFilePath = srcFilePath;
		setFileSize( Files.size( this.srcFilePath ) );
	}

	public	Path getDestFilePath() {
		return destFilePath;
	}

	private void setDestFilePath(Path destFilePath) {
		this.destFilePath = destFilePath;
		setFileExistsInDestination( Files.exists( this.destFilePath ) );
	}

	public	String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString() {
		return	fileName;
/*		return 	"file name: " + fileName + ", fileSize = " + fileSize +
				", source path: " + srcFilePath + ", destPath: " + destFilePath +
				", file exists? " + fileExistsInDestination;*/
	}
}
