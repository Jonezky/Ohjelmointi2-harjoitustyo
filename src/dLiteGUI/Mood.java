package dLiteGUI;

/**
 * Mood object class
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 24.4.2014
 */
public class Mood {
	
	private String name;
	private int ID;
	private static int nextID = 0; 
	
	/**
	 * Creates a new mood object with the given name. ID will be generated automatically.
	 * @param name Name of the mood
	 * @example
	 * <pre name="test">
	 * Mood m1 = new Mood("Calm");
	 * Mood m2 = new Mood("Mättö");
	 * m1.getName().equals("Calm") === true;
	 * m2.getName().equals("Mättö") === true;
	 * m1.getName().equals("Rough") === false;
	 * m1.getID() === 0;
	 * m2.getID() === 1;
	 * </pre>
	 */
	public Mood(String name){
		this.name = name;
		this.ID = nextID;
		nextID++;
	}
	
	
	/**
	 * Constructor
	 */
	public Mood(){
	}
	
	
	/**
	 * Parses songs information into String[]
	 * @param info Songs information
	 */
	public void parse(String info){
		String[] infoArray = info.split("\\|");
		this.ID = Integer.parseInt(infoArray[0].trim());
		this.name = infoArray[1].trim();
		nextID = this.ID + 1;
	}
	
	
	/**
	 * Returns the ID of the mood
	 * @return Mood ID
	 */
	public int getID(){
		return this.ID;
	}
	
	
	/**
	 * Returns the name of the mood
	 * @return Name of mood
	 */
	public String getName(){
		return this.name;
	}
	
	
	@Override
	/**
	 * Returns the mood's information in string format.
	 * @return moods ID and name in format "id|name"
	 */
	public String toString(){
		return String.format("%d|%s", this.getID(), this.getName());
	}
	
}
