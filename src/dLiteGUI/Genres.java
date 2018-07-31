package dLiteGUI;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for managing genre objects
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 24.4.2014
 */
public class Genres {

	
	private int count;
	private ArrayList<Genre> genres = new ArrayList<Genre>();
	
	
	

	/**
     * Searches for matching genre object with the given name
     * @param genre Name of the genre
     * @return Genre object with the given name
     * @example
     * <pre name="test">
     * #import java.util.ArrayList;
     * Genres test = new Genres();
     * 
     * //test for check and creating new Genre object
     * Genre g1 = test.check("Rock");
     * Genre g2 = test.check("House");
     * Genre g3 = test.check("Pop");
     * Genre g4 = test.check("Punk"); 
     * 
     * //test for String search
     * test.search("Trance") === null;
     * test.search("") === null;
     * test.search("Rock").getID() === g1.getID();
     * test.search("Rock").getName() === "Rock";
     * test.search("House").getID() === g2.getID();
     * test.search("House").getName() === "House";
     * 
     * //test for ID search
	 * test.search(g1.getID()).getName() ==="Rock";
	 * test.search(g2.getID()).getName() ==="House";
	 * test.search(g3.getID()).getName() ==="Pop";
	 * 
	 * //test for getGenres
	 * Genres testaus2 = new Genres();
     * testaus2.newGenre("Jazz");
	 * testaus2.newGenre("Trance");
	 * testaus2.newGenre("Blues");
	 * ArrayList<Genre> lista = testaus2.getGenres();
	 * lista.get(0).getName() === "Jazz";
	 * lista.get(1).getName() === "Trance"
	 * lista.get(2).getName() === "Blues"
     * </pre>
     */
	public Genre search(String genre) {
		for (int i = 0; i < this.genres.size(); i++) {
			if (this.genres.get(i).getName().equals(genre))
				return this.genres.get(i);
		}
		return null;
	}
	
	
	/**
	 * Returns the current count of genres
	 * @return current number of genres
	 */
	public int getCount(){
		return this.count;
	}
	

	/**
	 * Searches for a genre with the given ID
	 * @param ID ID of the genre
	 * @return index of Genre object with the given ID or null if not found
	 */
	public Genre search(int ID) {
		for (int i = 0; i < this.genres.size(); i++) {
			if (this.genres.get(i).getID() == ID)
				return this.genres.get(i);
		}
		return null;
	}
	
	
	/**
     * Checks if any existing genre matches the given name, if not creates a new genre object
     * @param genre name of the genre
     * @return genre id
     */
	public Genre check(String genre) {
        String genre2 = genre.toLowerCase().trim();
        for (int i = 0; i < this.genres.size(); i++) {
            if (this.genres.get(i).getName().toLowerCase().matches(genre2))
                return this.genres.get(i);
        }
        this.newGenre(genre);
        return this.genres.get(this.genres.size()-1);
    }
	
	
	/**
	 * Creates a new genre object
	 * @param name Name of the genre
	 */
	public void newGenre(String name){
		genres.add(new Genre(name));
		count++;
	}
	
	
	/**
	 * Creates a new Genre
	 * @return New genre
	 */
	public Genre newGenre(){
		Genre genreNew = new Genre();
		genres.add(genreNew);
		count++;
		return genreNew;
	}
	
	
	/**
	 * Removes a genre object
	 * @param index index of the object to be removed
	 */
	public void remove(int index){
		this.genres.remove(index);
		count--;
	}
	
	
	/**
	 * Returns String list containing all genre names.
	 * @return String list of all genres
	 */
	public ArrayList<String> getGenreList(){
	    ArrayList<String> genreList = new ArrayList<String>();
	    for(int i=0; i<this.genres.size(); i++) {
	        genreList.add(this.genres.get(i).getName());
	    }
	    Collections.sort(genreList);
	    return genreList;
	}
	
	/**
	 * getter for the genre
	 * @return genre
	 */
	public ArrayList<Genre> getGenres(){
		return this.genres;
	}
	
	
	/**
	 * Removes genre from database if there are no songs whit tht genre
	 * @param IDs IDs of the genres
	 * @example
	 * <pre name="test">
     * @example
     * <pre name="test">
     *  #import java.util.ArrayList;
     *  Genres genres = new Genres();
     *  Genre a1 = genres.check("Frederik");
     *  Genre a2 = genres.check("Bon Jovi");
     *  Genre a3 = genres.check("Abba");
     *  genres.getCount() === 3
     *  
     *  ArrayList<Integer> genreList = new ArrayList<Integer>();
     *  genreList.add(a1.getID());
     *  genreList.add(a3.getID());
     *  genres.removeUnused(genreList);
     * 
     *  genres.getCount() === 2
	 * </pre>
	 */
	public void removeUnused(ArrayList<Integer> IDs){
		for(int i=0; i<this.genres.size(); i++){
			int ID = this.genres.get(i).getID();
			if (!IDs.contains(ID))
				this.remove(i);
		}
	}
	
	
	/**
	 * Getter by the index of the genre object
	 * @param index index of the object
	 * @return object with the given index
	 */
	public Genre get(int index){
		return this.genres.get(index);
	}

}
