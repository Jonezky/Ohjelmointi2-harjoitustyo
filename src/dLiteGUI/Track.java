package dLiteGUI;

import java.util.Locale;

/**
 * Class for track objects.
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 5.4.2014
 */
public class Track {
	
	private String name;
	private int ID;
	private static int nextID = 0;
	private int artistID;
	private double bpm;
	private int genreID;
	private int moodID;			
	
	
	
	/**
	 * Creates a new track object with the given name. ID will be generated automatically.
	 * @param name Name of the track
	 * @param artistID artist ID
	 * @param bpm bpm
	 * @param genreID genre ID 
	 * @param moodID moodID
	 * @example
	 * <pre name="test">
	 * Track t1 = new Track("Nimi", 1, 123, 3, 4);
	 * t1.getName().equals("Nimi") === true;
	 * t1.getArtist() === 1;
	 * t1.getBpm() ~~~ 123;
	 * t1.getGenre() === 3;
	 * t1.getMood() === 4;
	 * t1.getID() === 0;
	 * 
	 * Track t2 = new Track("Toinen", 2, 111, 4, 5);	 
	 * t2.getName().equals("Toinen") === true;
	 * t2.getArtist() === 2;
	 * t2.getBpm() ~~~ 111;
	 * t2.getGenre() === 4;
	 * t2.getMood() === 5;
	 * t2.getID() === 1
	 * 
	 * t1.edit(2, "Nimi2", 50.0, 5, 6);
	 * t1.getName() === "Nimi2";
	 * t1.getArtist() === 2;
	 * t1.getBpm() ~~~ 50;
	 * t1.getGenre() === 5;
	 * t1.getMood() === 6;
	 * t1.getID() === 0;
	 * </pre>
	 */
	public Track(String name, int artistID, double bpm, int genreID, int moodID){
		this.name = name;
		this.artistID = artistID;
		this.bpm = bpm;
		this.genreID = genreID;
		this.moodID = moodID;
		this.ID = nextID;
		nextID++;
	}
	
	
	/**
	 * Creates a new track object from a single string.
	 * @param info All track info in a string
	 * @example
	 * <pre name="test"> 
	 * 
	 * // testataan samalla myös parse ja init metodeja.
	 *
	 * String testi = "14|2|Hopeinen Kuu|60|3|4";
	 * Track t1 = new Track(testi);
	 * t1.getName().equals("Hopeinen Kuu") === true;
	 * t1.getArtist() === 2;
	 * t1.getID() === 14;
	 * t1.getBpm() ~~~ 60;
	 * t1.getGenre() === 3;
	 * t1.getMood() === 4;
	 * 
	 * Track t2 = new Track("13|5|Dirlandaa|130|1|5");
	 * t2.getName().equals("Dirlandaa") === true;
	 * t2.getArtist() === 5;
	 * t2.getID() === 13;
	 * t2.getBpm() ~~~ 130;
	 * t2.getGenre() === 1;
	 * t2.getMood() === 5;
	 * </pre>
	 */
	public Track(String info){
		parse(info);
	}
	
	
	/**
	 * Finds track info in a single formatted line and puts it as track parameters.
	 * @param info all track info in one String variable
	 */
	public void parse(String info) {
		String[] infoArray = info.split("\\|");
		int ID1 = Integer.parseInt(infoArray[0].trim());
		int artist = Integer.parseInt(infoArray[1].trim());
		String title = infoArray[2].trim();
		double bpm1 = Double.parseDouble(infoArray[3].trim());
		int genre = Integer.parseInt(infoArray[4].trim());
		int mood = Integer.parseInt(infoArray[5].trim());
		init(ID1, artist, title, bpm1, genre, mood);
	}
	
	
	/**
	 * Initializes a new track object with the given attributes
	 * @param ID1 track ID
	 * @param artist Artist ID of the track
	 * @param title	Track title
	 * @param bpm1 Beats per minute of the track
	 * @param genre Genre ID of the track
	 * @param mood Mood ID of the track
	 */
	public void init(int ID1, int artist, String title, double bpm1, int genre, int mood){
		this.ID = ID1;
		this.name = title;
		this.artistID = artist;
		this.bpm = bpm1;
		this.genreID = genre;
		this.moodID = mood;
		nextID = this.ID + 1;
	}
	
	
	/**
	 * Edits the track data with given parameters
	 * @param artist new artistID
	 * @param title new track title
	 * @param bpm1 new track bpm
	 * @param genre new genreID
	 * @param mood new moodID
	 */
	public void edit(int artist, String title, double bpm1, int genre, int mood) {
		this.name = title;
		this.artistID = artist;
		this.bpm = bpm1;
		this.genreID = genre;
		this.moodID = mood;
	}
	
	
	
	/**
	 * Returns the ID of the track
	 * @return Track ID
	 */
	public int getID(){
		return this.ID;
	}
	
	
	/**
	 * Returns the name of the track
	 * @return Name of the track
	 */
	public String getName(){
		return this.name;
	}
	
	
	/**
	 * Returns the artist ID of the track
	 * @return artist ID of the track
	 */
	public int getArtist(){
		return this.artistID;
	}
	
	
	/**
	 * Returns the BPM of the track
	 * @return Bpm of the track
	 */
	public double getBpm(){
		return this.bpm;
	}
	
	
	/**
	 * Returns the ID of the genre of the track
	 * @return genreID of the track
	 */
	public int getGenre(){
		return this.genreID;
	}
	
	
	/**
	 * Returns the ID of the mood of the track
	 * @return moodID of the track
	 */
	public int getMood(){
		return this.moodID;
	}
	
	
	/**
	 * Checks if there are tracks matching the given parameters
	 * @param name name
	 * @param artistID ID of the artis
	 * @param bpmMin Min bpm
	 * @param bpmMax Max bpm
	 * @param genreID ID of the genre
	 * @param moodID ID of the mood
	 * @return true, if track matches parameters, false if not
	 * @example
	 * <pre name="test">
	 * 	Track t1 = new Track("Nimi", 1, 123, 3, 4);
	 * 	t1.matches("Nimim", 1, 100.0, 150.0, 3, 4) === false
	 * 	t1.matches("nimi", 1, 100.0, 150.0, 3, 4) === true
	 * 	t1.matches("N", 1, 100.0, 150.0, 3, 4) === true
	 * 	t1.matches("Nimi", 2, 100.0, 150.0, 3, 4) === false
	 * 	t1.matches("Nimi", 1, 100.0, 150.0, 6, 4) === false
	 * 	t1.matches("Nimi", 1, 100.0, 150.0, 3, 8) === false
	 * </pre>
	 */
	public boolean matches(String name, int artistID, double bpmMin, double bpmMax, int genreID, int moodID){
		
		if(!this.getName().toLowerCase().matches(".*" + name.toLowerCase() + ".*"))
			return false;
		if(this.getArtist() != artistID && artistID != -1)
			return false;
		if(this.getBpm() < bpmMin || this.getBpm() > bpmMax)
			return false;
		if(this.getGenre() != genreID && genreID != -1)
			return false;
		if(this.getMood() != moodID && moodID != -1)
			return false;
		return true;
	}
	
	@Override
	/**
	 * returns the information of the track object in a string format
	 * @return track info in the format "Track ID|ArtistID|Track name|BPM|GenreID|MoodID"
	 */
	public String toString(){
		return String.format(Locale.ENGLISH, "%d|%d|%s|%.2f|%d|%d", this.getID(), this.getArtist(), this.getName(),this.getBpm(), this.getGenre(), this.getMood());
	}
	
	
	
}
