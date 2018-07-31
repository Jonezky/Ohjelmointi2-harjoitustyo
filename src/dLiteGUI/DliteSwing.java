package dLiteGUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import fi.jyu.mit.gui.StringTable;

/**
 * Interface between classes MainWindow & SongLibrary.
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 15.4.2014
 */
public class DliteSwing {
	private SongLibrary libr;
	private JTextField textArtist;
	private JTextField textTitle;
	private JTextField textBPMmin;
	private JTextField textBPMmax;
	private JComboBox<String> dropdownMood;
	private JComboBox<String> dropdownGenre;
	private StringTable stringTable;

	private JTextField textArtistAdd;
	private JTextField textTitleAdd;
	private JTextField textBPMAdd;
	private JComboBox<String> dropdownMoodAdd;
	private JComboBox<String> dropdownGenreAdd;

	private JTextField textArtistInfo;
	private JTextField textTitleInfo;
	private JTextField textBpmInfo;
	private JComboBox<String> dropdownMoodInfo;
	private JComboBox<String> dropdownGenreInfo;
	private String file;
	private boolean edited;

	/**
	 * Creates new DliteSwing object
	 */
	public DliteSwing() {
		libr = new SongLibrary();
		edited = false;
	}

	/**
	 * Returns all genre names in a string array.
	 * @return all genre names in a string array.
	 */
	public String[] getGenreList() {
		return this.libr.getGenreList();
	}

	/**
	 * @return all mood names in a string array.
	 */
	public String[] getMoodList() {
		return this.libr.getMoodList();
	}

	/**
	 * defines the field to which textArtist is placed.
	 * @param kentta defines the field to which textArtist is placed.
	 */
	public void setTextArtist(JTextField kentta) {
		this.textArtist = kentta;
	}

	/**
	 * defines the field to which textTitle is placed.
	 * @param kentta defines the field to which textTitle is placed.
	 */
	public void setTextTitle(JTextField kentta) {
		this.textTitle = kentta;
	}

	/**
	 * defines the field to which textBPMmin is placed.
	 * @param kentta defines the field to which textBPMmin is placed.
	 */
	public void setTextBPMmin(JTextField kentta) {
		this.textBPMmin = kentta;
	}

	/**
	 * defines the field to which textBPMmax is placed.
	 * @param kentta defines the field to which textBPMmax is placed.
	 */
	public void setTextBPMmax(JTextField kentta) {
		this.textBPMmax = kentta;
	}

	/**
	 * defines the field to which dropdownGenre is placed.
	 * @param kentta defines the field to which dropdownGenre is placed.
	 */
	public void setDropDownGenre(JComboBox<String> kentta) {
		this.dropdownGenre = kentta;
	}

	/**
	 * defines the field to which dropdownMood is placed.
	 * @param kentta defines the field to which dropdownMood is placed.
	 */
	public void setDropDownMood(JComboBox<String> kentta) {
		this.dropdownMood = kentta;
	}

	/**
	 * defines the field to which stringTable is placed.
	 * @param kentta defines the field to which stringTable is placed.
	 */
	public void setStringTable(StringTable kentta) {
		this.stringTable = kentta;
	}

	/**
	 * defines the field to which textArtist_info is placed.
	 * @param kentta defines the field to which textArtist_info is placed.
	 */
	public void setTextArtistInfo(JTextField kentta) {
		this.textArtistInfo = kentta;
	}

	/**
	 * defines the field to which textTitle_info is placed.
	 * @param kentta defines the field to which textTitle_info is placed.
	 */
	public void setTextTitleInfo(JTextField kentta) {
		this.textTitleInfo = kentta;
	}

	/**
	 * defines the field to which textBPMinfo is placed.
	 * @param kentta defines the field to which textBPMinfo is placed.
	 */
	public void setTextBpmInfo(JTextField kentta) {
		this.textBpmInfo = kentta;
	}

	/**
	 * defines the field to which dropdownGenreInfo is placed.
	 * @param kentta defines the field to which dropdownGenreInfo is placed.
	 */
	public void setDropdownGenreInfo(JComboBox<String> kentta) {
		this.dropdownGenreInfo = kentta;
	}

	/**
	 * defines the field to which DropDownMoodInfo is placed.
	 * @param kentta defines the field to which DropDownMoodInfo is placed.
	 */
	public void setDropDownMoodInfo(JComboBox<String> kentta) {
		this.dropdownMoodInfo = kentta;
	}

	/**
	 * defines the field to which textArtistAdd is placed.
	 * @param kentta defines the field to which textArtistAdd is placed.
	 */
	public void setTextArtistAdd(JTextField kentta) {
		this.textArtistAdd = kentta;
	}

	/**
	 * defines the field to which textTitleAdd is placed.
	 * @param kentta defines the field to which textTitleAdd is placed.
	 */
	public void setTextTitleAdd(JTextField kentta) {
		this.textTitleAdd = kentta;
	}

	/**
	 * defines the field to which textBPMAdd is placed.
	 * @param kentta defines the field to which textBPMAdd is placed.
	 */
	public void setTextBPMAdd(JTextField kentta) {
		this.textBPMAdd = kentta;
	}

	/**
	 * defines the field to which dropdownGenreAdd is placed.
	 * @param kentta defines the field to which dropdownGenreAdd is placed.
	 */
	public void setDropDownGenreAdd(JComboBox<String> kentta) {
		this.dropdownGenreAdd = kentta;
	}

	/**
	 * defines the field to which dropdownMoodAdd is placed.
	 * @param kentta defines the field to which dropdownMoodAdd is placed.
	 */
	public void setDropDownMoodAdd(JComboBox<String> kentta) {
		this.dropdownMoodAdd = kentta;
	}

	/**
	 * Search songs from library based on user inputs.
	 */
	public void search() {
		stringTable.clear();
		textBPMmin.setBackground(Color.white);
		textBPMmax.setBackground(Color.white);
		String artist = textArtist.getText();
		String title = textTitle.getText();
		Double bpmMin = 0.0;
		Double bpmMax = 500.0;

		if (!textBPMmin.getText().equals("")) {
			try {
				bpmMin = Double.parseDouble(textBPMmin.getText());
			} catch (NumberFormatException e) {
				textBPMmin.setBackground(Color.red);
				return;
			}
		}

		if (!textBPMmax.getText().equals("")) {
			try {
				bpmMax = Double.parseDouble(textBPMmax.getText());
			} catch (NumberFormatException e) {
				textBPMmax.setBackground(Color.red);
				return;
			}
		}

		String genre = this.dropdownGenre.getSelectedItem().toString();
		String mood = this.dropdownMood.getSelectedItem().toString();

		ArrayList<Track> result = this.libr.search(artist, title, bpmMin,
				bpmMax, genre, mood);

		if (result == null)
			return;

		for (Track track : result) {
			int r = stringTable.addRow();
			stringTable.setObjectAt(track, r);
			stringTable.setValueAt(libr.getArtist(track.getArtist()).getName(),
					r, 0);
			stringTable.setValueAt(track.getName(), r, 1);
			stringTable.setValueAt(track.getBpm(), r, 2);
			stringTable.setValueAt(libr.getGenre(track.getGenre()).getName(),
					r, 3);
			stringTable.setValueAt(libr.getMood(track.getMood()).getName(), r,
					4);
		}

		stringTable.getTable().clearSelection();
	}

	/**
	* Clears the search fields
	*/
	public void clearSearchFields() {
		textArtist.setText("");
		textTitle.setText("");
		textBPMmin.setText("");
		textBPMmax.setText("");
		refreshDropdowns();

	}

	/**
	 * Refresh information in fields
	 */
	public void refreshDropdowns() {
		dropdownGenre.setModel(new DefaultComboBoxModel<String>(this
				.getGenreList()));
		dropdownMood.setModel(new DefaultComboBoxModel<String>(this
				.getMoodList()));
	}

	/**
	 * Refresh infromtion in add windows fields
	 */
	public void refreshDropdownsAdd() {
		dropdownGenreAdd.setModel(new DefaultComboBoxModel<String>(this
				.getGenreList()));
		dropdownMoodAdd.setModel(new DefaultComboBoxModel<String>(this
				.getMoodList()));
	}

	/**
	 * Refreshes info view dropdowns (genre and mood field) according to selected track
	 */
	public void refreshDropdownsInfo() {
		int selection = this.stringTable.getTable().getSelectedRow();

		dropdownGenreInfo.setModel(new DefaultComboBoxModel<String>(this
				.getGenreList()));
		dropdownGenreInfo.setSelectedItem(this.stringTable.getTable()
				.getModel().getValueAt(selection, 3).toString());

		dropdownMoodInfo.setModel(new DefaultComboBoxModel<String>(this
				.getMoodList()));
		dropdownMoodInfo.setSelectedItem(this.stringTable.getTable().getModel()
				.getValueAt(selection, 4).toString());
	}

	/**
	 * Makes info view dropdowns blank
	 */
	public void clearDropdownsInfo() {
		dropdownGenreInfo.setModel(new DefaultComboBoxModel<String>(
				new String[0]));
		dropdownMoodInfo.setModel(new DefaultComboBoxModel<String>(
				new String[0]));
	}

	/**
	 * Add new song to library based on user inputs.
	 */
	public void addSong() {
		String artist = textArtistAdd.getText();
		String title = textTitleAdd.getText();
		double bpm = 0;
		if (!textBPMAdd.getText().equals("")) {
			try {
				bpm = Double.parseDouble(textBPMAdd.getText());
			} catch (NumberFormatException e) {
				textBPMAdd.setBackground(Color.red);
				return;
			}
		}

		String genre = "";
		if (this.dropdownGenreAdd.getSelectedItem() == null)
			return;
		genre = this.dropdownGenreAdd.getSelectedItem().toString();
		String mood = "";
		if (this.dropdownMoodAdd.getSelectedItem() == null)
			return;
		mood = this.dropdownMoodAdd.getSelectedItem().toString();
		this.textBPMAdd.setBackground(Color.white);

		this.libr.newTrack(artist, title, bpm, genre, mood);
		refreshAddFields();
		edited = true;
	}

	/**
	 * Clears fields on add window.
	 */
	public void refreshAddFields() {
		textArtistAdd.setText("");
		textTitleAdd.setText("");
		textBPMAdd.setText("");
		refreshDropdownsAdd();
	}

	/**
	 * Sets info view fields to match selected tracks data.
	 */
	public void setEditWindow() {
		int selection = this.stringTable.getTable().getSelectedRow();
		if (selection == -1) {
			clearInfoFields();
			return;
		}

		textArtistInfo.setText(this.stringTable.getTable().getModel()
				.getValueAt(selection, 0).toString());
		textTitleInfo.setText(this.stringTable.getTable().getModel()
				.getValueAt(selection, 1).toString());
		textBpmInfo.setText(this.stringTable.getTable().getModel()
				.getValueAt(selection, 2).toString());

		refreshDropdownsInfo();
	}

	/**
	 * Clears info view fields.
	 */
	public void clearInfoFields() {
		textArtistInfo.setText("");
		textTitleInfo.setText("");
		textBpmInfo.setText("");
		clearDropdownsInfo();
	}

	/**
	 * Saves the track with data in edit view fields
	 */
	public void editTrack() {
		String artist = textArtistInfo.getText();
		String title = textTitleInfo.getText();
		double bpm = 0;

		if (!textBpmInfo.getText().equals("")) {
			try {
				bpm = Double.parseDouble(textBpmInfo.getText());
			} catch (NumberFormatException e) {
				textBpmInfo.setBackground(Color.red);
				return;
			}
		}

		String genre = "";
		
		if (this.dropdownGenreInfo.getSelectedItem() == null)
			return;
		genre = this.dropdownGenreInfo.getSelectedItem().toString();
		
		String mood = "";
		if (this.dropdownMoodInfo.getSelectedItem() == null)
			return;
		mood = this.dropdownMoodInfo.getSelectedItem().toString();
		
		this.textBpmInfo.setBackground(Color.white);

		Track selected = (Track) stringTable.getSelectedObject();

		int artistID = libr.checkArtist(artist).getID();
		int genreID = libr.checkGenre(genre).getID();
		int moodID = libr.checkMood(mood).getID();

		selected.edit(artistID, title, bpm, genreID, moodID);
		edited = true;
		search();
	}
	
	/**
	 * @param fileName name of the file to be read
	 * @return file name
	 */
	public String readFile(String fileName){
		if (!fileName.endsWith(".dat"))
			fileName += ".dat";
		libr = new SongLibrary();
		this.file = fileName;
		return libr.readFile(fileName);
	}
	
	
	/**
	 *  Writes to file
	 */
	public void writeFile(){
		libr.removeUnused();
		libr.writeFile(this.file);
		this.edited = false;
		
	}
	

	/**
	 * Returns a boolean value showing if the library is edited after opening.
	 * @return true if file is edited after opening, otherwise false.
	 */
	public boolean edited(){
		return this.edited;
	}
	
	
	/**
	 * Removes the track selected by the user
	 */
	public void removeTrack(){
		int selection = this.stringTable.getTable().getSelectedRow();
		if (selection == -1) {
			clearInfoFields();
			return;
		}
		Track track = (Track) this.stringTable.getObjectAt(selection);
		this.libr.removeTrack(track.getID());
		this.edited = true;
	}

}
