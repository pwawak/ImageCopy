import java.time.LocalDate;

// Generate subdirectory name based on pattern and given date

interface SubdirGen {
	public	enum	SubdirPattern	{ 
		YMD("yyyy-MM-dd"),
		EMPTY("_");
		
		private	String	pattern;
		
		private	SubdirPattern( String pattern ) {
			this.pattern = pattern;
		}
		
		public	String	getPattern () {
			return pattern;
		}
	}
	
	// Create subdirectory name based on pattern
	String	subdirBasedOnPattern ( LocalDate fileDate );
}
