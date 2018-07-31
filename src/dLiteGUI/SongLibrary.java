package dLiteGUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class for managing the song library
 * @author Joonas Kaski, Juuso Valkeejärvi & Juha-Pekka Hänninen
 * @version 24.4.2014
 */
public class SongLibrary {

	private final Artists artists = new Artists();
	private final Genres genres = new Genres();
	private final Moods moods = new Moods();
	private final Tracks tracks = new Tracks();

	/**
	 * Create new track with the given parameters
	 * @param artist Artists ID
	 * @param title title of the song
	 * @param bpm Songs bpm
	 * @param genre genre ID
	 * @param mood mood ID
	 */
	public void newTrack(String artist, String title, Double bpm, String genre,
			String mood) {
		int artistID = this.artists.check(artist).getID();
		int genreID = this.genres.check(genre).getID();
		int moodID = this.moods.check(mood).getID();
		this.tracks.newTrack(artistID, title, bpm, genreID, moodID);
	}

	/**
	 * Searches for matching tracks with given parameters
	 * @param artistName Name of the artist
	 * @param trackTitle Name of the track
	 * @param bpmMin Lower limit for bpm search
	 * @param bpmMax Upper limit for bpm search
	 * @param genre Genre of the track
	 * @param mood Mood of the track
	 * @return Array list of tracks matching the search criteria. Or null if none are found
	 * @example
	 * <pre name="test">
	 * 	#import java.util.ArrayList;
	 *	SongLibrary libr = new SongLibrary();
	 *	libr.newTrack("Bon Jovi", "Always", 80.0, "Rock", "Slow");
	 *	libr.newTrack("Elvis Presley", "Always on my Mind", 90.0, "Rock",
	 *			"Meininki");
	 *	libr.newTrack("Bon Jovi", "Living on a Prayer", 140.0, "Rock",
	 *			"Meininki");
	 *	libr.newTrack("TestiArtisti", "TestiBiisi", 140.0, "TestiGenre",
	 *			"TestiMood");
	 *	ArrayList<Track> haku = libr.search("", "Always", 50.0, 100.0, "", "");
	 *	haku.size() === 2
	 *	haku.get(0).getName() === "Always"
	 *	haku.get(1).getName() =R= "Always on.*"
	 *	ArrayList<Track> haku2 = libr.search("", "", 0.0, 200.0, "", "");
	 *	haku2.size() === 4
	 *	haku2.get(0).getName() === "Always"
	 *	haku2.get(1).getName() =R= "Always on.*"
	 *	haku2.get(2).getName() =R= "Living.*"
	 *	haku2.get(3).getName() === "TestiBiisi"
	 *	ArrayList<Track> haku3 = libr.search("Bon", "", 0.0, 200.0, "", "");
	 *	haku3.size() === 2
	 *	haku3.get(0).getName() === "Always"
	 *	haku3.get(1).getName() =R= "Living on.*"
	 *	ArrayList<Track> haku4 = libr.search("", "", 0.0, 200.0, "TestiGenre", "");
	 *	haku4.size() === 1
	 *	haku4.get(0).getName() === "TestiBiisi"
	 *	ArrayList<Track> haku5 = libr.search("", "", 0.0, 200.0, "", "Meininki");
	 *	haku5.size() === 2
	 *	haku5.get(0).getName() =R= "Always on.*"
	 *	haku5.get(1).getName() =R= "Living on.*"
	 *	ArrayList<Track> haku6 = libr.search("", "", 100.0, 200.0, "", "");
	 *	haku6.size() === 2
	 *	haku6.get(0).getName() =R= "Living on.*"
	 *	haku6.get(1).getName() === "TestiBiisi"
	 * </pre>
	 */
	public ArrayList<Track> search(String artistName, String trackTitle,
			double bpmMin, double bpmMax, String genre, String mood) {

		ArrayList<Artist> artistMatch = null;
		if (!artistName.equals(""))
			artistMatch = new ArrayList<Artist>(this.artists.search(artistName));
		if (artistMatch != null && artistMatch.size() == 0) {
			return null;
		}

		int genreID = -1;
		if (!genre.equals(""))
			genreID = this.genres.search(genre).getID();
		int moodID = -1;
		if (!mood.equals(""))
			moodID = this.moods.search(mood).getID();

		return this.tracks.search(artistMatch, trackTitle, bpmMin, bpmMax,
				genreID, moodID);
	}

	/**
	 * Return String array containing all existing moods in library
	 * @return array with names of all the moods
	 * @example
	 * <pre name="test">
	 * 	SongLibrary libr = new SongLibrary();
	 *	libr.newTrack("Bon Jovi", "Always", 80.0, "Rock", "Slow");
	 *	libr.newTrack("Elvis Presley", "Always on my Mind", 90.0, "Rocknroll",
	 *			"Meininki");
	 *	libr.newTrack("Bon Jovi", "Living on a Prayer", 140.0, "Rock",
	 *			"Meininki");
	 *	libr.newTrack("TestiArtisti", "TestiBiisi", 140.0, "Genre",
	 *			"TestiMood");
	 *	String[] moodit = libr.getMoodList();
	 *	moodit.length === 4
	 *	moodit[0] === "";
	 *	moodit[1] === "Meininki";
	 *	moodit[2] === "Slow";
	 *	moodit[3] === "TestiMood";
	 * </pre>
	 */
	public String[] getMoodList() {
		ArrayList<String> moodList = this.moods.getMoodList();

		String[] moodArrayString = new String[moodList.size() + 1];
		moodArrayString[0] = "";
		for (int i = 1; i < moodArrayString.length; i++) {
			moodArrayString[i] = moodList.get(i - 1);
		}
		return moodArrayString;
	}

	/**
	 * Return String array containing all existing genres in library
	 * @return list of all genres
	 * @example
	 * <pre name="test">
	 * 	SongLibrary libr = new SongLibrary();
	 *	libr.newTrack("Bon Jovi", "Always", 80.0, "Rock", "Slow");
	 *	libr.newTrack("Elvis Presley", "Always on my Mind", 90.0, "Rocknroll",
	 *			"Meininki");
	 *	libr.newTrack("Bon Jovi", "Living on a Prayer", 140.0, "Rock",
	 *			"Meininki");
	 *	libr.newTrack("TestiArtisti", "TestiBiisi", 140.0, "Genre",
	 *			"TestiMood");
	 *	String[] genret = libr.getGenreList();
	 *	genret.length === 4
	 *	genret[0] === "";
	 *	genret[1] === "Genre";
	 *	genret[2] === "Rock";
	 *	genret[3] === "Rocknroll";
	 * </pre>
	 */
	public String[] getGenreList() {
		ArrayList<String> genreList = this.genres.getGenreList();

		String[] genreArrayString = new String[genreList.size() + 1];
		genreArrayString[0] = "";
		for (int i = 1; i < genreArrayString.length; i++) {
			genreArrayString[i] = genreList.get(i - 1);
		}
		return genreArrayString;
	}
	
	
	/**
	 * Returns the artist object with matching ID
	 * @param ID ID to check
	 * @return artist object with ID
	 */
	public Track getTrack(int ID) {
		return this.tracks.search(ID);
	}
	

	/**
	 * Returns the artist object with matching ID
	 * @param ID ID to check
	 * @return artist object with ID
	 */
	public Artist getArtist(int ID) {
		return this.artists.search(ID);
	}

	/**
	 * Returns the genre object with matching ID
	 * @param ID ID to check
	 * @return genre object with ID
	 */
	public Genre getGenre(int ID) {
		return this.genres.search(ID);
	}

	/**
	 * Returns the mood object with matching ID
	 * @param ID ID to check
	 * @return mood object with ID
	 */
	public Mood getMood(int ID) {
		return this.moods.search(ID);
	}

	/**
	 * Checks if an artist object with given name exists. If exists returns the matching artist,
	 * if not creates a new artist object and returns that created object.
	 * @param artist artist name to check
	 * @return matching artist object
	 */
	public Artist checkArtist(String artist) {
		return this.artists.check(artist);
	}

	/**
	 * Checks if a genre object with given name exists. If exists returns the matching genre,
	 * if not creates a new genre object and returns that created object.
	 * @param genre genre name to check
	 * @return matching genre object
	 */
	public Genre checkGenre(String genre) {
		return this.genres.check(genre);
	}

	/**
	 * Checks if a mood object with given name exists. If exists returns the matching mood,
	 * if not creates a new mood object and returns that created object.
	 * @param mood mood name to check
	 * @return matching mood object
	 */
	public Mood checkMood(String mood) {
		return this.moods.check(mood);
	}

	/**
	 * Reads a file and
	 * constructs the SongLibrary object according to data in the file
	 * @param file name of the file to be read
	 * @return possible error message, if no error return null
	 * @example
	 * <pre name="test">
	 * 	#THROWS IOException
	 *	#import fi.jyu.mit.ohj2.VertaaTiedosto;
	 *	#import java.io.IOException;
	 *
	 *  String fileName = "test.dat";
	 *  VertaaTiedosto.kirjoitaTiedosto(fileName,
	 *  			  "#TRACKS\n" +
	 *				  "1|1|Waterloo|145.00|1|1\n" +
	 *				  "2|3|Always|80.00|2|2\n" +
	 *				  "3|4|Hounddog|140.00|2|1\n" +
	 *				  "#ARTISTS\n" +
	 *				  "1|Abba\n" +
	 *				  "3|Bon jovi\n" +
	 *				  "4|Elvis\n" +
	 *				  "#GENRES\n" +
	 *				  "1|Pop\n" +
	 *				  "2|Rock\n" +
	 *				  "#MOODS\n" +
	 *				  "1|Joyful\n" +
	 *				  "2|Slow");
	 *				  
	 *	SongLibrary libr = new SongLibrary();
	 * 	libr.readFile(fileName);
	 * 	ArrayList<Track> biisit = libr.search("", "", 0.0, 600.0, "", "");
	 * 	Track track1 = biisit.get(0);
	 * 	track1.getName() === "Waterloo"
	 * 	track1.getBpm() ~~~ 145.0
	 * 	Track track2 = biisit.get(1);
	 * 	track2.getName() === "Always"
	 * 	track2.getBpm() ~~~ 80.0
	 * 	Track track3 = biisit.get(2);
	 * 	track3.getName() === "Hounddog"
	 * 	track3.getBpm() ~~~ 140.0
	 * 	libr.getArtist(1).getName() === "Abba"
	 * 	libr.getArtist(3).getName() === "Bon jovi"
	 * 	libr.getArtist(4).getName() === "Elvis"
	 * 	libr.getGenre(1).getName() === "Pop"
	 * 	libr.getGenre(2).getName() === "Rock"
	 * 	libr.getMood(1).getName() === "Joyful"
	 * 	libr.getMood(2).getName() === "Slow"
	 * 
	 * 	VertaaTiedosto.tuhoaTiedosto(fileName);
	 * </pre>
	 */
	public String readFile(String file) {
		try (BufferedReader read = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = read.readLine()) != null) {
				while (!(line = read.readLine()).equals("#ARTISTS")) {
					this.tracks.newTrack(line.trim());
				}
				while (!(line = read.readLine()).equals("#GENRES")) {
					this.artists.newArtist().parse(line.trim());
				}
				while (!(line = read.readLine()).equals("#MOODS")) {
					this.genres.newGenre().parse(line.trim());
				}
				while ((line = read.readLine()) != null) {
					this.moods.newMood().parse(line.trim());
				}

			}
		} catch (FileNotFoundException e) {
			return "Virhe luettaessa";
		} catch (IOException e) {
			return "Virhe luettaessa";
		}
		return null;
	}

	/**
	 * Writes library contents into a file
	 * @param file file to write
	 * @example
	 * <pre name="test">
	 * 	#THROWS IOException
	 *	#import fi.jyu.mit.ohj2.VertaaTiedosto;
	 *	#import java.io.IOException;
	 *
	 *	String fileName = "test.dat";
	 *	VertaaTiedosto.kirjoitaTiedosto(fileName,
	 *  			  "#TRACKS\n" +
	 *				  "1|1|Waterloo|145.00|1|1\n" +
	 *				  "2|3|Always|80.00|2|2\n" +
	 *				  "3|4|Hounddog|140.00|2|1\n" +
	 *				  "#ARTISTS\n" +
	 *				  "1|Abba\n" +
	 *				  "3|Bon jovi\n" +
	 *				  "4|Elvis\n" +
	 *				  "#GENRES\n" +
	 *				  "1|Pop\n" +
	 *				  "2|Rock\n" +
	 *				  "#MOODS\n" +
	 *				  "1|Joyful\n" +
	 *				  "2|Slow");
	 * 
	 * 	SongLibrary libr = new SongLibrary();
	 *	libr.readFile(fileName);
	 *	libr.newTrack("TestiArtisti", "TestiBiisi", 140.0, "TestiGenre",
	 *			"TestiMood");
	 *	libr.writeFile(fileName);
	 *	
	 *	String data = "#TRACKS\n" +
	 *				  "1|1|Waterloo|145.00|1|1\n" +
	 *				  "2|3|Always|80.00|2|2\n" +
	 *				  "3|4|Hounddog|140.00|2|1\n" +
	 *				  "4|5|TestiBiisi|140.00|3|3\n" +
	 *				  "#ARTISTS\n" +
	 *				  "1|Abba\n" +
	 *				  "3|Bon jovi\n" +
	 *				  "4|Elvis\n" +
	 *				  "5|TestiArtisti\n" +
	 *				  "#GENRES\n" +
	 *				  "1|Pop\n" +
	 *				  "2|Rock\n" +
	 *				  "3|TestiGenre\n" +
	 *				  "#MOODS\n" +
	 *				  "1|Joyful\n" +
	 *				  "2|Slow\n" +
	 *				  "3|TestiMood";	
	 *
	 *	VertaaTiedosto.vertaaFileString(fileName, data) === null;
	 *	
	 *	VertaaTiedosto.tuhoaTiedosto(fileName);
	 * </pre>
	 */
	public void writeFile(String file) {
		this.removeUnused();
		try (FileWriter output = new FileWriter(file)) {
			output.write("#TRACKS\n");
			for (int i = 0; i < this.tracks.getCount(); i++) {
				output.write(this.tracks.get(i).toString() + "\n");
			}
			output.write("#ARTISTS\n");
			for (int i = 0; i < this.artists.getCount(); i++) {
				output.write(this.artists.get(i).toString() + "\n");
			}
			output.write("#GENRES\n");
			for (int i = 0; i < this.genres.getCount(); i++) {
				output.write(this.genres.get(i).toString() + "\n");
			}
			output.write("#MOODS\n");
			for (int i = 0; i < this.moods.getCount(); i++) {
				output.write(this.moods.get(i).toString() + "\n");
			}
		} catch (IOException ex) {
			//
		}
	}

	/**
	 * Enum for the removeUnused method
	 */
	@SuppressWarnings("javadoc")
	public enum Selection {
		ARTIST, GENRE, MOOD
	};

	/**
	 * Removes unused artist, track and mood objects from the library and rearranges the remaining
	 * ones to get rid of any null pointers.
	 */
	protected void removeUnused() {
		ArrayList<Integer> artistIDs = this.tracks.getIDs(Selection.ARTIST);
		ArrayList<Integer> genreIDs = this.tracks.getIDs(Selection.GENRE);
		ArrayList<Integer> moodIDs = this.tracks.getIDs(Selection.MOOD);

		this.artists.removeUnused(artistIDs);
		this.genres.removeUnused(genreIDs);
		this.moods.removeUnused(moodIDs);
	}

	/**
	 * Removes the track with the given ID
	 * @param ID ID of the track to be removed
	 * @example
	 * <pre name="test">
	 * 	#THROWS IOException
	 *	#import fi.jyu.mit.ohj2.VertaaTiedosto;
	 *	#import java.io.IOException;
	 *  #import java.util.ArrayList;
	 *
	 *	String fileName = "test.dat";
	 *	VertaaTiedosto.kirjoitaTiedosto(fileName,
	 *  			  "#TRACKS\n" +
	 *				  "1|1|Waterloo|145.00|1|1\n" +
	 *				  "2|3|Always|80.00|2|2\n" +
	 *				  "3|4|Hounddog|140.00|2|1\n" +
	 *				  "#ARTISTS\n" +
	 *				  "1|Abba\n" +
	 *				  "3|Bon jovi\n" +
	 *				  "4|Elvis\n" +
	 *				  "#GENRES\n" +
	 *				  "1|Pop\n" +
	 *				  "2|Rock\n" +
	 *				  "#MOODS\n" +
	 *				  "1|Joyful\n" +
	 *				  "2|Slow");
	 * 
	 * 	SongLibrary libr = new SongLibrary();
	 *	libr.readFile(fileName);
	 *
	 *	libr.removeTrack(1);
	 * ArrayList<Track> haku2 = libr.search("", "", 0.0, 200.0, "", "");
	 *	haku2.size() === 2;
	 * </pre>
	 */
	public void removeTrack(int ID) {
		this.tracks.remove(ID);
	}
}
