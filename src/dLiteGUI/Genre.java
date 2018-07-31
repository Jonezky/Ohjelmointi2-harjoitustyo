package dLiteGUI;

/**
 * Class for genres
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 24.4.2014
 */
public class Genre {
	
	private String name;
	private int ID;
	private static int nextID = 0; 
	
	/**
	 * Creates a new genre object with the given name. ID will be generated automatically.
	 * @param name Name of the genre
	 * @example
	 * <pre name="test">
	 * Genre g1 = new Genre("Rock");
	 * Genre g2 = new Genre("Pop");
	 * g1.getName().equals("Rock") === true;
	 * g2.getName().equals("Pop") === true;
	 * g1.getName().equals("Trance") === false;
	 * g1.getID() === 0;
	 * g2.getID() === 1;
	 * </pre>
	 */
	public Genre(String name){
		this.name = name;
		this.ID = nextID;
		nextID++;
	}
	
	
	/**
	 * Constructor
	 */
	public Genre(){
	}
	
	
	/**
	 * Parses song to String[]
	 * @param info Songs information
	 */
	public void parse(String info){
		String[] infoArray = info.split("\\|");
		this.ID = Integer.parseInt(infoArray[0].trim());
		this.name = infoArray[1].trim();
		nextID = this.ID + 1;
	}
	
	/**
	 * @return Genre ID
	 */
	public int getID(){
		return this.ID;
	}
	
	
	/**
	 * @return Name of genre
	 */
	public String getName(){
		return this.name;
	}
	
	@Override
	/**
	 * @return genres ID and name in format "id|name"
	 */
	public String toString(){
		return String.format("%d|%s", this.getID(), this.getName());
	}
	
}
