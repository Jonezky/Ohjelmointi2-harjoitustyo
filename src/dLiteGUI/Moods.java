package dLiteGUI;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for managing mood objects
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 24.4.2014
 */
public class Moods {

	
	private int count;
	private ArrayList<Mood> moods = new ArrayList<Mood>();
	
	
	/**
	 * Searches for matching mood object with the given name
	 * Returns the current count of moods
	 * @return current number of moods
	 */
	public int getCount(){
		return this.count;
	}

	/**
	 * Searches for moods with names matching the given string
	 * @param mood Name of the mood
	 * @return Mood object with the given name
	 * @example
	 * <pre name="test">
	 * #import java.util.ArrayList;
	 * Moods testi = new Moods();
	 * String testi1 = "Joyfull";
	 * String testi2 = "Energetic";
	 * String testi3 = "Calm";
	 * 
	 * //test for String search and creating new mood object
	 * Mood m1 = testi.newMood(testi1);
	 * Mood m2 = testi.newMood(testi2);
	 * Mood m3 = testi.newMood(testi3);
	 * testi.search(testi1).getName() === "Joyfull";
	 * testi.search(testi2).getName() === "Energetic";
	 * testi.search(testi3).getName() === "Calm";
	 * 
	 * //test for ID search
	 * testi.search(m1.getID()).getName() ===("Joyfull");
	 * testi.search(m2.getID()).getName() === ("Energetic");
	 * testi.search(m3.getID()).getName() === ("Calm");
	 * testi.search(-1) === null;
	 * testi.search(6) === null;  
	 * 
	 * //test for check
     * String test1 = "Playful";
     * String test2 = "Slow";
     * String test3 = "Mad";
     * testi.check(test1).getName() === "Playful";
     * testi.check(test2).getName() === "Slow";
     * testi.check(test3).getName() === "Mad";
     * int testiCount = testi.getCount();
     * testi.check("mad");
     * testi.getCount() === testiCount;
     * 
     * //test for getMoods
     * Moods testaus2 = new Moods();
     * testaus2.newMood("Sad");
	 * testaus2.newMood("Happy");
	 * testaus2.newMood("Agressive");
	 * ArrayList<Mood> lista = testaus2.getMoods();
	 * lista.get(0).getName() === "Sad";
	 * lista.get(1).getName() === "Happy"
	 * lista.get(2).getName() === "Agressive"
	 * </pre>
	 */
	public Mood search(String mood) {
		for (int i = 0; i < this.moods.size(); i++) {
			if (this.moods.get(i).getName().equals(mood))
				return this.moods.get(i);
		}
		return null;
	}
	

	/**
	 * Searches for an mood with the given ID
	 * @param ID ID of the mood
	 * @return index of Artist object with the given ID or null if not found
	 */
	public Mood search(int ID) {
		for (int i = 0; i < this.moods.size(); i++) {
			if (this.moods.get(i).getID() == ID)
				return this.moods.get(i);
		}
		return null;
	}
	
	
	 /**
     * Checks if any existing mood matches the given name, if not creates a new mood object
     * @param mood name of the mood
     * @return mood id
     */
    public Mood check(String mood) {
        String mood2 = mood.toLowerCase().trim();
        for (int i = 0; i < this.moods.size(); i++) {
            if (this.moods.get(i).getName().toLowerCase().matches(mood2))
                return this.moods.get(i);
        }
        this.newMood(mood);
        return this.moods.get(this.moods.size()-1);
    }
	
	
	/**
	 * Creates new mood object with given name
	 * @param name Name of the mood
	 * @return created mood object
	 */
	public Mood newMood(String name){
	    Mood m = new Mood(name);
		moods.add(m);
		count++;
		return m;
	}
	
	
	/**
	 * Creates new mood object with given name
	 * @return new mood
	 */
	public Mood newMood(){
		Mood moodNew = new Mood();
		moods.add(moodNew);
		count++;
		return moodNew;
	}
	
	
	/**
	 * Removes a mood object
	 * @param index index of the object to be removed
	 */
	private void remove(int index){
		this.moods.remove(index);
		count--;
	}
	
	/**
	 * Returns String list containing all mood names.
	 * @return String list of all moods
	 * </pre>
	 */
	public ArrayList<String> getMoodList(){
	    ArrayList<String> moodList = new ArrayList<String>();
	    for(int i=0; i<this.getCount(); i++) {
	        moodList.add(this.moods.get(i).getName());
	    }
	    Collections.sort(moodList);
	    return moodList;
	}
	
	
	/**
	 * returns an arraylist containing all the mood objects
	 * @return mood
	 */
	public ArrayList<Mood> getMoods(){
		return this.moods;
	}

	/**
     * Removes mood to which there are no references
     * @param IDs All mood IDs
     * @example
     * <pre name="test">
     *  #import java.util.ArrayList;
     *  Moods moods = new Moods();
     *  Mood a1 = moods.check("Frederik");
     *  Mood a2 = moods.check("Bon Jovi");
     *  Mood a3 = moods.check("Abba");
     *  moods.getCount() === 3
     *  
     *  ArrayList<Integer> moodList = new ArrayList<Integer>();
     *  moodList.add(a1.getID());
     *  moodList.add(a3.getID());
     *  moods.removeUnused(moodList);
     * 
     *  moods.getCount() === 2
     *  
     * </pre>
	 */
	public void removeUnused(ArrayList<Integer> IDs){
		for(int i=0; i<this.moods.size(); i++){
			int ID = this.moods.get(i).getID();
			if (!IDs.contains(ID))
				this.remove(i);
		}
	}
	
	
	/**
	 * Getter for moods by their index
	 * @param index index of the object
	 * @return object with the given index
	 */
	public Mood get(int index){
		return this.moods.get(index);
	}
}
