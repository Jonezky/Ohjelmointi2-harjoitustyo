package dLiteGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 * Class for the "open library" dialog window
 * @author Juha-Pekka Hänninen, Juuso Valkeejärvi & Joonas Kaski
 * @version 11.4.2014
 */
public class OpenWindow extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
	protected String file = null;
	private JTextField fileName = new JTextField();

	/**
	 * Launch the application.
	 * @return name of the file what to be opened
	 */
	public static String askFileName() {
		String fileString = null;
		try {
			OpenWindow dialog = new OpenWindow();
			dialog.setVisible(true);
			fileString = dialog.getFile();
			dialog.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileString;
	}

	private void setFile(String res) {
		this.file = res;
	}
	
	protected String getFile() {
		return file;
	}

	/**
	 * Create the dialog window.
	 */
	public OpenWindow() {
		setTitle("Open library");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 329, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{110, 174, 86, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEnterLibraryName = new JLabel("Enter library name:");
			GridBagConstraints gbc_lblEnterLibraryName = new GridBagConstraints();
			gbc_lblEnterLibraryName.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterLibraryName.gridx = 1;
			gbc_lblEnterLibraryName.gridy = 2;
			contentPanel.add(lblEnterLibraryName, gbc_lblEnterLibraryName);
		}
		{
			GridBagConstraints fileNameTextField = new GridBagConstraints();
			fileNameTextField.fill = GridBagConstraints.HORIZONTAL;
			fileNameTextField.insets = new Insets(0, 0, 5, 5);
			fileNameTextField.anchor = GridBagConstraints.NORTH;
			fileNameTextField.gridx = 1;
			fileNameTextField.gridy = 3;
			fileName.setText("biisit");
			contentPanel.add(fileName, fileNameTextField);
			fileName.setColumns(10);
			fileName.selectAll();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Open");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setFile(fileName.getText());
						setVisible(false);
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setFile(null);
						setVisible(false);
					}
				});
			}
		}
	}

}
