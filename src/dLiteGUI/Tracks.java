
package dLiteGUI;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for managing track objects with relation to other object classes (Artists, Genres, Moods).
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 5.4.2014
 */
public class Tracks {

	private int count;
	private ArrayList<Track> tracks = new ArrayList<Track>();

	/**
	 * Returns the current count of tracks
	 * @return current number of tracks
	 * @example
	 * <pre name="test">
	 * Tracks a1 = new Tracks();
	 * a1.getCount() === 0;
	 * a1.newTrack(0,"Apinamies",125.0,3,3);
	 * a1.newTrack(1,"Titanic",135.0,4,6);
	 * a1.getCount() === 2;
	 * </pre>
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * @param ID ID of the Track
	 * @return Index of the track
	 * 
	 *public Track search(int ID) {
	 *	for (int i = 0; i < this.tracks.size() || ID <= this.tracks.get(i).getID(); i++) {
	 *		if (this.tracks.get(i).getID() == ID)
	 *			return this.tracks.get(i);
	 *	}
	 *	return null;
	 *}
	 *
	*/

	/**
	 * Creates a new track object with the given parameters
	 * @param artistID Artists ID
	 * @param title title of the song
	 * @param bpm Songs bpm
	 * @param genreID genre ID
	 * @param moodID mood ID
	 */
	public void newTrack(int artistID, String title, Double bpm, int genreID,
			int moodID) {
		tracks.add(new Track(title, artistID, bpm, genreID, moodID));
		count++;
	}

	/**
	 * Removes an object
	 * @param ID ID of the object to be removed
	 */
	public void remove(int ID) {
		loop : for (int i = 0; i < this.tracks.size(); i++){
			if (this.tracks.get(i).getID() == ID){
				this.tracks.remove(i);
				break loop;
			}
		}
		count--;
	}

	/**
	* @param name Track name
	*/
	public void newTrack(String name) {
		tracks.add(new Track(name));
		count++;
	}

	/**
	 * @param artistMatch artist match
	 * @param trackTitle title of the track
	 * @param bpmMin Min bpm
	 * @param bpmMax max bpm
	 * @param genreID id of the genre
	 * @param moodID id of the mood
	 * @return arraylist containing all tracks that match given parameters
	 */
	public ArrayList<Track> search(ArrayList<Artist> artistMatch,
			String trackTitle, double bpmMin, double bpmMax, int genreID,
			int moodID) {
		ArrayList<Track> results = new ArrayList<Track>();

		for (int i = 0; i < this.tracks.size(); i++) {
			boolean matches = false;
			if (artistMatch != null) {
				for (int j = 0; j < artistMatch.size() && !matches; j++)
					matches = this.tracks.get(i).matches(trackTitle,
							artistMatch.get(j).getID(), bpmMin, bpmMax,
							genreID, moodID);
			} else
				matches = this.tracks.get(i).matches(trackTitle, -1, bpmMin,
						bpmMax, genreID, moodID);
			if (matches)
				results.add(this.tracks.get(i));
		}
		return results;
	}

	/**
	 * Returns a list of IDs of objects of given kind
	 * @param select type of object the IDs of which the method returns.
	 * @return list of IDs of objects of the given type
	 * @example
	 * <pre name="test">
	 * 	#import java.util.ArrayList;
	 * 	#import dLiteGUI.SongLibrary.*;
	 * 	Tracks tracks = new Tracks();
	 *  tracks.newTrack("1|1|Waterloo|145.00|1|1");
	 *	tracks.newTrack("2|3|Always|80.00|2|2");
	 *	tracks.newTrack("3|4|Hounddog|140.00|2|1");
	 *
	 *	ArrayList artistIDList = tracks.getIDs(Selection.ARTIST);
	 *	artistIDList.size() === 3
	 *	artistIDList.get(0) === 1
	 *	artistIDList.get(1) === 3
	 *	artistIDList.get(2) === 4
	 *
	 *	ArrayList genreIDList = tracks.getIDs(Selection.GENRE);
	 *	genreIDList.size() === 2
	 *	genreIDList.get(0) === 1
	 *	genreIDList.get(1) === 2
	 *
	 *	ArrayList moodIDList = tracks.getIDs(Selection.MOOD);
	 *	moodIDList.size() === 2
	 *	moodIDList.get(0) === 1
	 *	moodIDList.get(1) === 2
	 * </pre>
	 */
	public ArrayList<Integer> getIDs(SongLibrary.Selection select) {
		int ID = 0;
		ArrayList<Integer> IDList = new ArrayList<Integer>();

		switch (select) {
		case ARTIST:
			for (int i = 0; i < this.getCount(); i++) {
				ID = this.tracks.get(i).getArtist();
				if (!IDList.contains(ID))
					IDList.add(ID);
			}
			Collections.sort(IDList);
			return IDList;

		case GENRE:
			for (int i = 0; i < this.getCount(); i++) {
				ID = this.tracks.get(i).getGenre();
				if (!IDList.contains(ID))
					IDList.add(ID);
			}
			Collections.sort(IDList);
			return IDList;

		case MOOD:
			for (int i = 0; i < this.getCount(); i++) {
				ID = this.tracks.get(i).getMood();
				if (!IDList.contains(ID))
					IDList.add(ID);
			}
			Collections.sort(IDList);
			return IDList;

		default:
			return null;
		}

	}
	
	
	/**
	 * @param ID ID of the object
	 * @return object with the given ID
	 */
	public Track search(int ID){
		for (Track i : this.tracks) {
			if (i.getID() == ID)
				return i;
		}
		return null;
	}
	
	
	/**
	 * @param index index of the object
	 * @return object with the given index
	 */
	public Track get(int index){
		return this.tracks.get(index);
	}

}
