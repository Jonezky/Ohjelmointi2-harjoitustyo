package dLiteGUI;

import java.util.ArrayList;

/**
 * Class for managing artist objects 
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 5.4.2014
 */
public class Artists {

	private int count;
	private Artist[] artists = new Artist[1];

	/**
	 * Returns the current count of artists
	 * @return current number of artists
	 * @example <pre name="test">
	 * Artists abc = new Artists();
	 * Artist a1 = abc.check("Frederik");
	 * Artist a2 = abc.check("Bon Jovi");
	 * Artist a3 = abc.check("Abba");
	 * abc.getCount() === 3
	 * Artist a4 = abc.check("Apina");
	 * abc.getCount() === 4
	 * </pre>
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Returns all the artists matching the given parameters
	 * @param artist Name of the artist
	 * @return Artist object with the given name
	 * @example <pre name="test">
	 * 	#import java.util.ArrayList;
	 * 	Artists abc = new Artists();
	 * 	Artist a1 = abc.check("Frederik");
	 * 	Artist a2 = abc.check("Bon Jovi");
	 * 	Artist a3 = abc.check("Abba");
	 * 	Artist a4 = abc.check("Apina");
	 * 	abc.search("Kikka").size() === 0;
	 * 	ArrayList<Artist> result = abc.search("jovi");
	 * 	result.size() === 1;
	 * 	ArrayList<Artist> result2 = abc.search("a");
	 * 	result2.size() === 2;
	 * 	result2.get(0).getName() === "Abba";
	 * 	result2.get(1).getName() === "Apina";
	 * </pre>
	 */
	public ArrayList<Artist> search(String artist) {
		String artist2 = artist.toLowerCase().trim();
		ArrayList<Artist> artistArray = new ArrayList<Artist>();
		for (int i = 0; i < count; i++) {
			if (this.artists[i].getName().toLowerCase()
					.matches(".*" + artist2 + ".*"))
				artistArray.add(this.artists[i]);
		}
		return artistArray;
	}

	/**
	 * Checks if any existing artist matchest the given name, if not creates a
	 * new artist object
	 * @param artist name of the artist
	 * @return artist id
	 * @example <pre name="test">
	 * Artists abc = new Artists();
	 * abc.check("Frederik");
	 * abc.check("Bon Jovi");
	 * int testID = abc.check("Abba").getID();
	 * abc.search("Kikka").size() === 0;
	 * abc.getCount() === 3
	 * abc.check("abba").getID() === testID;
	 * abc.search(testID).getName() === "Abba"
	 * abc.getCount() === 3
	 * </pre>
	 */
	public Artist check(String artist) {
		String artist2 = artist.toLowerCase().trim();
		for (int i = 0; i < count; i++) {
				if (artists[i] != null) {
				if (this.artists[i].getName().toLowerCase().matches(artist2))
					return this.artists[i];
				}
		}
		this.newArtist(artist);
		return this.artists[count - 1];
	}

	/**
	 * Searches for an artist with the given ID
	 * @param ID of the artist
	 * @return index of Artist object with the given ID or null if not found
	 * @example <pre name="test">
	 * Artists abc = new Artists();
	 * Artist a1 = abc.check("Frederik");
	 * Artist a2 = abc.check("Bon Jovi");
	 * Artist a3 = abc.check("Abba");
	 * abc.search(a1.getID()).getName() === "Frederik";
	 * abc.search(a2.getID()).getName() === "Bon Jovi";
	 * abc.search(a3.getID()).getName() === "Abba";
	 * abc.search(-1) === null;
	 * </pre>
	 */
	public Artist search(int ID) {
		for (int i = 0; i < this.count; i++) {
			if (this.artists[i].getID() == ID)
				return this.artists[i];
		}
		return null;
	}

	/**
	 * Creates a new artist object and increases the count by one.
	 * @param name  Name of the artist
	 */
	public void newArtist(String name) {
		if (this.count >= this.artists.length) {
			Artist[] artistsNew = new Artist[this.artists.length * 2];
			for (int i = 0; i < artists.length; i++) {
				artistsNew[i] = artists[i];
			}
			this.artists = artistsNew;
		}

		artists[count] = (new Artist(name));
		count++;
	}
	
	
	/**
	 * Creates new artist
	 * @return new artist
	 */
	public Artist newArtist() {
		if (this.count >= this.artists.length) {
			Artist[] artistsNew = new Artist[this.artists.length * 2];
			for (int i = 0; i < artists.length; i++) {
				artistsNew[i] = artists[i];
			}
			this.artists = artistsNew;
		}

		Artist uusiArtisti = new Artist();
		this.artists[this.count] = uusiArtisti;
		this.count++;
		return uusiArtisti;
	}
	

	/**
	 * Getter for atrist
	 * @return artist
	 */
	public Artist[] getArtists(){
		return this.artists;
	}
	
	
	/**
	 * Removes artists to which there are no references
	 * @param IDs All artist IDs
	 * @example
	 * <pre name="test">
	 * 	#import java.util.ArrayList;
	 * 	Artists artists = new Artists();
	 * 	Artist a1 = artists.check("Frederik");
	 * 	Artist a2 = artists.check("Bon Jovi");
	 * 	Artist a3 = artists.check("Abba");
	 * 	artists.getCount() === 3
	 * 	artists.search("Bon Jovi").size() === 1;
	 * 	
	 * 	ArrayList<Integer> artistList = new ArrayList<Integer>();
	 * 	artistList.add(a1.getID());
	 * 	artistList.add(a3.getID());
	 * 	artists.removeUnused(artistList);
	 * 
	 * 	artists.getCount() === 2
	 * 	artists.search("Bon Jovi").size() === 0;
	 * 	
	 * 	artistList.clear();
	 * 	artists.removeUnused(artistList);
	 * 	artists.getCount() === 0
	 * 	
	 * </pre>
	 */
	public void removeUnused(ArrayList<Integer> IDs){
		for(int i = 0; i < this.count; i++){
			int ID = this.artists[i].getID();
			if (!IDs.contains(ID))
				this.artists[i] = null;
		}
		
		int j = 0;
		for(int i = 0; i < this.count; i++){
			if(this.artists[i] != null){
				artists[j] = artists[i];
				j++;
			}
		}
		count = j;
	}
	
	
	/**
	 * @param index index of the object
	 * @return object with the given index
	 */
	public Artist get(int index){
		return this.artists[index];
	}

}
