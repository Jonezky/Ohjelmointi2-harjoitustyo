
package dLiteGUI;

/**
 * Artist class
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 5.3.2014
 */
public class Artist {
	
	private String name;
	private int ID;
	private static int nextID = 0; 
	
	
	
	/**
	 * Creates a new artist object with the given name. ID will be generated automatically.
	 * @param name Name of the artist
	 * @example
	 * <pre name="test">
	 * Artist a1 = new Artist("Frederik");
	 * Artist a2 = new Artist("Kikka");
	 * a1.getName().equals("Frederik") === true;
	 * a2.getName().equals("Kikka") === true;
	 * a1.getName().equals("Danny") === false;
	 * a1.getID() === 0;
	 * a2.getID() === 1;
	 * </pre>
	 */
	public Artist(String name){
		this.name = name;
		this.ID = nextID;
		nextID++;
	}
	
	
	/**
	 * Constructor
	 */
	public Artist(){
	}
	
	
	/**
	 * Parses song into String[]
	 * @param info Songs information
	 */
	public void parse(String info){
		String[] infoArray = info.split("\\|");
		this.ID = Integer.parseInt(infoArray[0].trim());
		this.name = infoArray[1].trim();
		nextID = this.ID + 1;
	}
	
	
	/**
	 * Returns the ID of the artist
	 * @return Artist ID
	 */
	public int getID(){
		return this.ID;
	}
	
	
	/**
	 * Returns the name of the artist
	 * @return Name of artist
	 */
	public String getName(){
		return this.name;
	}
	
	@Override
	/**
	 * @return artists ID and name in format "id|name"
	 */
	public String toString(){
		return String.format("%d|%s", this.getID(), this.getName());
	}
	
}
