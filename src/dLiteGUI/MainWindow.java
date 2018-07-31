package dLiteGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.gui.StringTable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

/**
 * Program's User interface
 * @author Juha-Pekka Hänninen, Joonas Kaski, Juuso Valkeejärvi
 * @version 5.4.2014
 */

public class MainWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private static final DliteSwing swing = new DliteSwing();
	private JPanel contentPane;
	private JTextField textArtist;
	private JTextField textTitle;
	private JTextField textBPMmin;
	private JTextField textBPMmax;
	private JTextField textArtistInfo;
	private JTextField textTitleInfo;
	private JTextField textBpmInfo;
	private JComboBox<String> dropdownMoodInfo;
	private JComboBox<String> dropdownGenreInfo;
	private JComboBox<String> dropdownMood;
	private JComboBox<String> dropdownGenre;
	private final Action searchAction;
	private StringTable stringTable;

	/**
	 * Launch the application.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					if (!frame.openFile()) {
						System.exit(ABORT);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Opens a dialog box with the given text.
	 * @param displayText text to be displayed
	 */
	public static void openDialog(String displayText) {
		JOptionPane.showMessageDialog(new JFrame(), displayText);
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (!swing.edited()) {
					dispose();
					return;
				}
				int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(new JFrame(),
						"Save library", "Save library", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					swing.writeFile();
					dispose();
				}
				if (dialogResult == JOptionPane.NO_OPTION)
					dispose();
				if (dialogResult == JOptionPane.CANCEL_OPTION)
					return;
			}
		});

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				swing.writeFile();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					System.exit(0);
				}
			}
		});
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmAddNewSong = new JMenuItem("Add new song");
		mntmAddNewSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmAddNewSong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddWindow.main(null);
			}
		});
		mnEdit.add(mntmAddNewSong);

		JMenuItem mntmDeleteSong = new JMenuItem("Delete song");
		mntmDeleteSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
		mntmDeleteSong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.removeTrack();
				swing.search();
			}
		});
		mnEdit.add(mntmDeleteSong);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDialog("Open software help");
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDialog("Show software info");
			}
		});
		mnHelp.add(mntmInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		contentPane.add(searchPanel, BorderLayout.NORTH);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[] { 200, 0, 115, 10, 103, 103 };
		gbl_searchPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_searchPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		gbl_searchPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE, 0.0,
				0.0 };
		searchPanel.setLayout(gbl_searchPanel);

		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblArtist = new GridBagConstraints();
		gbc_lblArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtist.gridx = 0;
		gbc_lblArtist.gridy = 0;
		searchPanel.add(lblArtist, gbc_lblArtist);

		JLabel lblBpm = new JLabel("BPM");
		GridBagConstraints gbc_lblBpm = new GridBagConstraints();
		gbc_lblBpm.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBpm.insets = new Insets(0, 0, 5, 5);
		gbc_lblBpm.gridx = 2;
		gbc_lblBpm.gridy = 0;
		searchPanel.add(lblBpm, gbc_lblBpm);

		JLabel lblMood = new JLabel("Mood");
		GridBagConstraints gbc_lblMood = new GridBagConstraints();
		gbc_lblMood.anchor = GridBagConstraints.WEST;
		gbc_lblMood.insets = new Insets(0, 0, 5, 5);
		gbc_lblMood.gridx = 4;
		gbc_lblMood.gridy = 0;
		searchPanel.add(lblMood, gbc_lblMood);

		textBPMmax = new JTextField();
		textBPMmax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				swing.search();
			}
		});
		textBPMmax.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textBPMmax = new GridBagConstraints();
		gbc_textBPMmax.ipadx = 35;
		gbc_textBPMmax.anchor = GridBagConstraints.EAST;
		gbc_textBPMmax.insets = new Insets(0, 0, 5, 5);
		gbc_textBPMmax.gridx = 2;
		gbc_textBPMmax.gridy = 1;
		searchPanel.add(textBPMmax, gbc_textBPMmax);
		textBPMmax.setColumns(1);

		textBPMmin = new JTextField();
		textBPMmin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				swing.search();
			}
		});
		textBPMmin.setColumns(1);
		textBPMmin.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textBPMmin = new GridBagConstraints();
		gbc_textBPMmin.ipadx = 35;
		gbc_textBPMmin.anchor = GridBagConstraints.WEST;
		gbc_textBPMmin.insets = new Insets(0, 0, 5, 5);
		gbc_textBPMmin.gridx = 2;
		gbc_textBPMmin.gridy = 1;
		searchPanel.add(textBPMmin, gbc_textBPMmin);

		JLabel label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		searchPanel.add(label, gbc_label);

		dropdownMood = new JComboBox<String>();
		dropdownMood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				swing.search();
			}
		});
		dropdownMood.setModel(new DefaultComboBoxModel<String>(new String[0]));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.ipady = -5;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 1;
		searchPanel.add(dropdownMood, gbc_comboBox);

		JLabel lblGenre = new JLabel("Genre");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.gridx = 2;
		gbc_lblGenre.gridy = 2;
		searchPanel.add(lblGenre, gbc_lblGenre);

		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 2;
		searchPanel.add(lblTitle, gbc_lblTitle);

		textArtist = new JTextField();
		textArtist.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				swing.search();
			}
		});
		GridBagConstraints gbc_textArtist = new GridBagConstraints();
		gbc_textArtist.insets = new Insets(0, 0, 5, 5);
		gbc_textArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArtist.gridx = 0;
		gbc_textArtist.gridy = 1;
		searchPanel.add(textArtist, gbc_textArtist);
		textArtist.setColumns(10);

		textTitle = new JTextField();
		textTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				swing.search();
			}
		});
		GridBagConstraints gbc_textTitle = new GridBagConstraints();
		gbc_textTitle.anchor = GridBagConstraints.NORTH;
		gbc_textTitle.insets = new Insets(0, 0, 0, 5);
		gbc_textTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTitle.gridx = 0;
		gbc_textTitle.gridy = 3;
		searchPanel.add(textTitle, gbc_textTitle);
		textTitle.setColumns(10);

		dropdownGenre = new JComboBox<String>();
		dropdownGenre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				swing.search();
			}
		});
		dropdownGenre.setModel(new DefaultComboBoxModel<String>(new String[0]));
		GridBagConstraints gbc_dropdownGenre = new GridBagConstraints();
		gbc_dropdownGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_dropdownGenre.anchor = GridBagConstraints.NORTH;
		gbc_dropdownGenre.ipady = -5;
		gbc_dropdownGenre.insets = new Insets(0, 0, 0, 5);
		gbc_dropdownGenre.gridx = 2;
		gbc_dropdownGenre.gridy = 3;
		searchPanel.add(dropdownGenre, gbc_dropdownGenre);

		JButton btnClear = new JButton("Clear");

		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.ipady = -5;
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 4;
		gbc_btnClear.gridy = 3;
		searchPanel.add(btnClear, gbc_btnClear);

		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, BorderLayout.EAST);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 66, 149, 0 };
		gbl_infoPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 20, 0, 20, 0, 20,
				0, 40, 0 };
		gbl_infoPanel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		infoPanel.setLayout(gbl_infoPanel);

		JLabel lblArtist_info = new JLabel("Artist");
		GridBagConstraints gbc_lblArtist_info = new GridBagConstraints();
		gbc_lblArtist_info.anchor = GridBagConstraints.WEST;
		gbc_lblArtist_info.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtist_info.gridx = 0;
		gbc_lblArtist_info.gridy = 1;
		infoPanel.add(lblArtist_info, gbc_lblArtist_info);

		textArtistInfo = new JTextField();
		textArtistInfo.setText("");
		GridBagConstraints gbc_textArtist_info = new GridBagConstraints();
		gbc_textArtist_info.gridwidth = 2;
		gbc_textArtist_info.insets = new Insets(0, 0, 5, 0);
		gbc_textArtist_info.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArtist_info.gridx = 0;
		gbc_textArtist_info.gridy = 2;
		infoPanel.add(textArtistInfo, gbc_textArtist_info);
		textArtistInfo.setColumns(10);

		JLabel lblTitle_1 = new JLabel("Title");
		GridBagConstraints gbc_lblTitle_1 = new GridBagConstraints();
		gbc_lblTitle_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle_1.anchor = GridBagConstraints.WEST;
		gbc_lblTitle_1.gridx = 0;
		gbc_lblTitle_1.gridy = 3;
		infoPanel.add(lblTitle_1, gbc_lblTitle_1);

		textTitleInfo = new JTextField();
		GridBagConstraints gbc_textTitle_info = new GridBagConstraints();
		gbc_textTitle_info.gridwidth = 2;
		gbc_textTitle_info.insets = new Insets(0, 0, 5, 0);
		gbc_textTitle_info.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTitle_info.gridx = 0;
		gbc_textTitle_info.gridy = 4;
		infoPanel.add(textTitleInfo, gbc_textTitle_info);
		textTitleInfo.setColumns(10);

		JLabel lblBpm_info = new JLabel("BPM");
		GridBagConstraints gbc_lblBpm_info = new GridBagConstraints();
		gbc_lblBpm_info.insets = new Insets(0, 0, 5, 5);
		gbc_lblBpm_info.gridx = 0;
		gbc_lblBpm_info.gridy = 6;
		infoPanel.add(lblBpm_info, gbc_lblBpm_info);

		textBpmInfo = new JTextField();
		GridBagConstraints gbc_textBpm_info = new GridBagConstraints();
		gbc_textBpm_info.insets = new Insets(0, 0, 5, 0);
		gbc_textBpm_info.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBpm_info.gridx = 1;
		gbc_textBpm_info.gridy = 6;
		infoPanel.add(textBpmInfo, gbc_textBpm_info);
		textBpmInfo.setColumns(10);

		JLabel lblGenre_info = new JLabel("Genre");
		GridBagConstraints gbc_lblGenre_info = new GridBagConstraints();
		gbc_lblGenre_info.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre_info.gridx = 0;
		gbc_lblGenre_info.gridy = 8;
		infoPanel.add(lblGenre_info, gbc_lblGenre_info);

		dropdownGenreInfo = new JComboBox<String>();
		dropdownGenreInfo.setModel(new DefaultComboBoxModel<String>(
				new String[0]));
		GridBagConstraints gbc_Genre_dropdown_info = new GridBagConstraints();
		gbc_Genre_dropdown_info.ipady = -5;
		gbc_Genre_dropdown_info.insets = new Insets(0, 0, 5, 0);
		gbc_Genre_dropdown_info.fill = GridBagConstraints.HORIZONTAL;
		gbc_Genre_dropdown_info.gridx = 1;
		gbc_Genre_dropdown_info.gridy = 8;
		infoPanel.add(dropdownGenreInfo, gbc_Genre_dropdown_info);

		JLabel lblMood_1 = new JLabel("Mood");
		GridBagConstraints gbc_lblMood_1 = new GridBagConstraints();
		gbc_lblMood_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMood_1.gridx = 0;
		gbc_lblMood_1.gridy = 10;
		infoPanel.add(lblMood_1, gbc_lblMood_1);

		dropdownMoodInfo = new JComboBox<String>();
		dropdownMoodInfo.setModel(new DefaultComboBoxModel<String>(
				new String[0]));
		GridBagConstraints gbc_Mood_dropdown_info = new GridBagConstraints();
		gbc_Mood_dropdown_info.ipady = -5;
		gbc_Mood_dropdown_info.insets = new Insets(0, 0, 5, 0);
		gbc_Mood_dropdown_info.fill = GridBagConstraints.HORIZONTAL;
		gbc_Mood_dropdown_info.gridx = 1;
		gbc_Mood_dropdown_info.gridy = 10;
		infoPanel.add(dropdownMoodInfo, gbc_Mood_dropdown_info);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.setEditWindow();
			}
		});

		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 12;
		infoPanel.add(btnCancel, gbc_btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.editTrack();
			}
		});

		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 12;
		infoPanel.add(btnSave, gbc_btnSave);

		JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		contentPane.add(listPanel, BorderLayout.CENTER);
		GridBagLayout gbl_listPanel = new GridBagLayout();
		gbl_listPanel.columnWidths = new int[] { 393, 0 };
		gbl_listPanel.rowHeights = new int[] { 0, 0 };
		gbl_listPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_listPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		listPanel.setLayout(gbl_listPanel);

		stringTable = new StringTable();
		stringTable.getTable().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		stringTable.getTable().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		stringTable.getTable().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						swing.setEditWindow();
					}

				});
		stringTable.getTable().setFillsViewportHeight(true);
		stringTable.setBackground(Color.WHITE);
		stringTable.getTable().setShowGrid(false);
		stringTable.getTable().setModel(
				new DefaultTableModel(new Object[][] { { null, null, null,
						null, null }, }, new String[] { "Artist", "Title",
						"BPM", "Genre", "Mood" }) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] { false, false,
							false, false, false };

					@Override
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		stringTable.clear();
		stringTable.getTable().getColumnModel().getColumn(0)
				.setPreferredWidth(150);
		stringTable.getTable().getColumnModel().getColumn(1)
				.setPreferredWidth(150);
		stringTable.getTable().getColumnModel().getColumn(2)
				.setPreferredWidth(40);
		stringTable.getTable().getColumnModel().getColumn(2).setMinWidth(40);
		GridBagConstraints gbc_stringTable = new GridBagConstraints();
		gbc_stringTable.fill = GridBagConstraints.BOTH;
		gbc_stringTable.gridx = 0;
		gbc_stringTable.gridy = 0;
		listPanel.add(stringTable, gbc_stringTable);
		dropdownGenre.setModel(new DefaultComboBoxModel<String>(swing
				.getGenreList()));
		dropdownMood.setModel(new DefaultComboBoxModel<String>(swing
				.getMoodList()));
		
		
		// Connecting the swing and GUI

		swing.setTextArtist(textArtist);
		swing.setTextTitle(textTitle);
		swing.setTextBPMmin(textBPMmin);
		swing.setTextBPMmax(textBPMmax);
		swing.setDropDownGenre(dropdownGenre);
		swing.setDropDownMood(dropdownMood);
		swing.setStringTable(stringTable);

		swing.setTextArtistInfo(textArtistInfo);
		swing.setTextTitleInfo(textTitleInfo);
		swing.setTextBpmInfo(textBpmInfo);
		swing.setDropdownGenreInfo(dropdownGenreInfo);
		swing.setDropDownMoodInfo(dropdownMoodInfo);

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.clearSearchFields();
				swing.search();
			}
		});

		searchAction = new SearchAction();

		JButton btnSearch = new JButton("Search");
		btnSearch.setAction(searchAction);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.gridheight = 4;
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 0;
		searchPanel.add(btnSearch, gbc_btnSearch);
		swing.search();

	}
	/**
	 * Triggers swing search method.
	 */
	private class SearchAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SearchAction() {
			putValue(NAME, "Search");
			putValue(SHORT_DESCRIPTION, "Search for songs");

		}
		/**
		 * Triggers the swing.search method
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			swing.search();
		}
	}

	/**
	 * Getter for Paaikkunas DliteSwing object
	 * @return swing object of this window
	 */
	protected static DliteSwing getSwing() {
		return swing;
	}
	
	/**
	 * Opens a file with the given filename and refreshes all GUI fields
	 * @return true if file could be opened, false if not
	 */
	protected boolean openFile(){
		String fileName;
		if ((fileName = OpenWindow.askFileName()) == null)
			return false;
		String error = swing.readFile(fileName);
		if (error != null)
			openDialog(error);
		swing.search();
		swing.refreshDropdowns();
		return true;
	}
}
