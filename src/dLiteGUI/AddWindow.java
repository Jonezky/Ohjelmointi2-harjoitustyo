package dLiteGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 * Class for the add song window
 * @author Juha-Pekka Hänninen, Juuso Valkeejärvi, Joonas Kaski
 * @version 7.2.2014
 */
public class AddWindow extends JDialog {

    /**
     * "Add song" dialog window
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final DliteSwing swing = MainWindow.getSwing();


    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AddWindow frame = new AddWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * @param displayText teksti joka tulee näkyviin
     */
    public static void openDialog (String displayText){
        JOptionPane.showMessageDialog(new JFrame(), displayText);
    }
    
    private JTextField textArtist_info;
    private JTextField textTitle_info;
    private JTextField textBpm_info;
    private final Action addAction = new SwingAction();
    /**
     * Create the frame.
     */
    public AddWindow() {
        addWindowListener(new WindowAdapter() {
            @Override
           public void windowClosing(WindowEvent arg0) {
            	swing.search();
                dispose();   
           }
           
        });
    	setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Add song");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 320, 397);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JPanel infoPanel = new JPanel();
        contentPane.add(infoPanel, BorderLayout.EAST);
        GridBagLayout gbl_infoPanel = new GridBagLayout();
        gbl_infoPanel.columnWidths = new int[]{66, 149, 0};
        gbl_infoPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 20, 0, 20, 0, 20, 0, 40, 0};
        gbl_infoPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        infoPanel.setLayout(gbl_infoPanel);
        
        JLabel lblArtist_info = new JLabel("Artist");
        GridBagConstraints gbc_lblArtist_info = new GridBagConstraints();
        gbc_lblArtist_info.anchor = GridBagConstraints.WEST;
        gbc_lblArtist_info.insets = new Insets(0, 0, 5, 5);
        gbc_lblArtist_info.gridx = 0;
        gbc_lblArtist_info.gridy = 1;
        infoPanel.add(lblArtist_info, gbc_lblArtist_info);
        
        textArtist_info = new JTextField();
        textArtist_info.setText("");
        GridBagConstraints gbc_textArtist_info = new GridBagConstraints();
        gbc_textArtist_info.gridwidth = 2;
        gbc_textArtist_info.insets = new Insets(0, 0, 5, 0);
        gbc_textArtist_info.fill = GridBagConstraints.HORIZONTAL;
        gbc_textArtist_info.gridx = 0;
        gbc_textArtist_info.gridy = 2;
        infoPanel.add(textArtist_info, gbc_textArtist_info);
        textArtist_info.setColumns(10);
        
        JLabel lblTitle_1 = new JLabel("Title");
        GridBagConstraints gbc_lblTitle_1 = new GridBagConstraints();
        gbc_lblTitle_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblTitle_1.anchor = GridBagConstraints.WEST;
        gbc_lblTitle_1.gridx = 0;
        gbc_lblTitle_1.gridy = 3;
        infoPanel.add(lblTitle_1, gbc_lblTitle_1);
        
        textTitle_info = new JTextField();
        GridBagConstraints gbc_textTitle_info = new GridBagConstraints();
        gbc_textTitle_info.gridwidth = 2;
        gbc_textTitle_info.insets = new Insets(0, 0, 5, 0);
        gbc_textTitle_info.fill = GridBagConstraints.HORIZONTAL;
        gbc_textTitle_info.gridx = 0;
        gbc_textTitle_info.gridy = 4;
        infoPanel.add(textTitle_info, gbc_textTitle_info);
        textTitle_info.setColumns(10);
        
        JLabel lblBpm_info = new JLabel("BPM");
        GridBagConstraints gbc_lblBpm_info = new GridBagConstraints();
        gbc_lblBpm_info.insets = new Insets(0, 0, 5, 5);
        gbc_lblBpm_info.gridx = 0;
        gbc_lblBpm_info.gridy = 6;
        infoPanel.add(lblBpm_info, gbc_lblBpm_info);
        
        textBpm_info = new JTextField();
        GridBagConstraints gbc_textBpm_info = new GridBagConstraints();
        gbc_textBpm_info.insets = new Insets(0, 0, 5, 0);
        gbc_textBpm_info.fill = GridBagConstraints.HORIZONTAL;
        gbc_textBpm_info.gridx = 1;
        gbc_textBpm_info.gridy = 6;
        infoPanel.add(textBpm_info, gbc_textBpm_info);
        textBpm_info.setColumns(10);
        
        JLabel lblGenre_info = new JLabel("Genre");
        GridBagConstraints gbc_lblGenre_info = new GridBagConstraints();
        gbc_lblGenre_info.insets = new Insets(0, 0, 5, 5);
        gbc_lblGenre_info.gridx = 0;
        gbc_lblGenre_info.gridy = 8;
        infoPanel.add(lblGenre_info, gbc_lblGenre_info);
        
        JComboBox<String> Genre_dropdown_info = new JComboBox<String>();
        Genre_dropdown_info.setEditable(true);
        Genre_dropdown_info.setModel(new DefaultComboBoxModel<String>(new String[0]));
        GridBagConstraints gbc_Genre_dropdown_info = new GridBagConstraints();
        gbc_Genre_dropdown_info.ipady = -5;
        gbc_Genre_dropdown_info.insets = new Insets(0, 0, 5, 0);
        gbc_Genre_dropdown_info.fill = GridBagConstraints.HORIZONTAL;
        gbc_Genre_dropdown_info.gridx = 1;
        gbc_Genre_dropdown_info.gridy = 8;
        infoPanel.add(Genre_dropdown_info, gbc_Genre_dropdown_info);
        
        JLabel lblMood_1 = new JLabel("Mood");
        GridBagConstraints gbc_lblMood_1 = new GridBagConstraints();
        gbc_lblMood_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblMood_1.gridx = 0;
        gbc_lblMood_1.gridy = 10;
        infoPanel.add(lblMood_1, gbc_lblMood_1);
        
        JComboBox<String> Mood_dropdown_info = new JComboBox<String>();
        Mood_dropdown_info.setEditable(true);
        Mood_dropdown_info.setModel(new DefaultComboBoxModel<String>(new String[0]));
        GridBagConstraints gbc_Mood_dropdown_info = new GridBagConstraints();
        gbc_Mood_dropdown_info.ipady = -5;
        gbc_Mood_dropdown_info.insets = new Insets(0, 0, 5, 0);
        gbc_Mood_dropdown_info.fill = GridBagConstraints.HORIZONTAL;
        gbc_Mood_dropdown_info.gridx = 1;
        gbc_Mood_dropdown_info.gridy = 10;
        infoPanel.add(Mood_dropdown_info, gbc_Mood_dropdown_info);
        
        JButton btnCancel = new JButton("Close");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	swing.search();
                dispose();
            }
        });
        
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancel.gridx = 0;
        gbc_btnCancel.gridy = 12;
        infoPanel.add(btnCancel, gbc_btnCancel);
        
        JButton btnAdd = new JButton("Save");
        btnAdd.setAction(addAction);
        
        
        GridBagConstraints gbc_btnAdd = new GridBagConstraints();
        gbc_btnAdd.gridx = 1;
        gbc_btnAdd.gridy = 12;
        infoPanel.add(btnAdd, gbc_btnAdd);
        
        swing.setTextArtistAdd(textArtist_info);
	    swing.setTextTitleAdd(textTitle_info);
	    swing.setTextBPMAdd(textBpm_info);
	    swing.setDropDownGenreAdd(Genre_dropdown_info);
	    swing.setDropDownMoodAdd(Mood_dropdown_info);
	    swing.refreshDropdownsAdd();
    }
    
	/**
	 * Adds a new song to the library
	 */
    private class SwingAction extends AbstractAction {
		/**
         * Serial number
         */
        private static final long serialVersionUID = 1L;
        public SwingAction() {
			putValue(NAME, "Add song");
			putValue(SHORT_DESCRIPTION, "Adds a song to the library");
		}
		@Override
        public void actionPerformed(ActionEvent e) {
			swing.addSong();
		}
	}
}
