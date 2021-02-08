package ufr_mobile_unique_id_via_nfc_examples_java;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import ufr_mobile_unique_id_via_nfc_examples_java.UfrCoder.ERRORCODES;
import ufr_mobile_unique_id_via_nfc_examples_java.UfrCoder.uFrFunctions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sun.jna.ptr.IntByReference;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.text.html.ListView;

import com.sun.jna.Native;

import javax.swing.text.Element;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static ufr_mobile_unique_id_via_nfc_examples_java.UfrCoder.uFrFunctions;
import static ufr_mobile_unique_id_via_nfc_examples_java.UfrCoder.GetLibFullPath;

import javax.swing.JSeparator;
import com.sun.jna.Pointer;

public class window {

	uFrFunctions ufr;

	javax.swing.Timer tmr;

	private JFrame frmMainWindow;
	private JTextField txtReaderType;
	private JTextField txtPortInterface;
	private JTextField txtPortName;
	private JTextField txtArg;
	JLabel lblStatus;

	JCheckBox chkAdvanced;
	private JTextField txtUID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
		try {
			ufr = (uFrFunctions) Native.loadLibrary(GetLibFullPath(false), uFrFunctions.class);

		} catch (UnsatisfiedLinkError e) {
			JOptionPane.showMessageDialog(null, "Unable to load library for path : " + GetLibFullPath(false));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainWindow = new JFrame();
		frmMainWindow.getContentPane().setBackground(Color.WHITE);
		frmMainWindow.setTitle("Mobile Unique ID via NFC Desktop example v1.0");
		frmMainWindow.setBounds(100, 100, 650, 240);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnReaderOpen = new JButton("Reader Open");
		btnReaderOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReaderOpenActionPerformed(e);
			}
		});
		btnReaderOpen.setBackground(Color.WHITE);
		btnReaderOpen.setFont(new Font("Dialog", Font.BOLD, 12));
		btnReaderOpen.setBounds(10, 11, 120, 30);

		JButton btnReaderReset = new JButton("Reader Reset");
		btnReaderReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReaderResetActionPerformed(e);
			}
		});
		btnReaderReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReaderReset.setBackground(Color.WHITE);
		btnReaderReset.setBounds(140, 10, 120, 30);

		JButton btnReaderClose = new JButton("Reader Close");
		btnReaderClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReaderCloseActionPerformed(e);
			}
		});
		btnReaderClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReaderClose.setBackground(Color.WHITE);
		btnReaderClose.setBounds(270, 10, 120, 30);

		chkAdvanced = new JCheckBox("Use Advanced options");

		chkAdvanced.setBackground(Color.WHITE);
		chkAdvanced.setBounds(10, 48, 179, 23);

		JLabel lblReaderType = new JLabel("Reader type:");
		lblReaderType.setEnabled(false);
		lblReaderType.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblReaderType.setBounds(10, 83, 75, 13);

		JLabel lblPortName = new JLabel("Port name:");
		lblPortName.setEnabled(false);
		lblPortName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPortName.setBounds(140, 83, 64, 14);

		JLabel lblPortInterface = new JLabel("Port interface:");
		lblPortInterface.setEnabled(false);
		lblPortInterface.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPortInterface.setBounds(300, 83, 85, 14);

		JLabel lblArg = new JLabel("Arg:");
		lblArg.setEnabled(false);
		lblArg.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblArg.setBounds(430, 83, 25, 14);

		txtReaderType = new JTextField();
		txtReaderType.setEnabled(false);
		txtReaderType.setBounds(92, 79, 30, 20);
		txtReaderType.setColumns(10);

		txtPortInterface = new JTextField();
		txtPortInterface.setEnabled(false);
		txtPortInterface.setBounds(390, 79, 30, 20);
		txtPortInterface.setColumns(10);

		txtPortName = new JTextField();
		txtPortName.setEnabled(false);
		txtPortName.setBounds(214, 79, 67, 20);
		txtPortName.setColumns(10);

		txtArg = new JTextField();
		txtArg.setEnabled(false);
		txtArg.setBounds(462, 79, 150, 20);
		txtArg.setColumns(10);
		frmMainWindow.getContentPane().setLayout(null);
		frmMainWindow.getContentPane().add(btnReaderOpen);
		frmMainWindow.getContentPane().add(btnReaderReset);
		frmMainWindow.getContentPane().add(btnReaderClose);
		frmMainWindow.getContentPane().add(chkAdvanced);
		frmMainWindow.getContentPane().add(lblReaderType);
		frmMainWindow.getContentPane().add(txtReaderType);
		frmMainWindow.getContentPane().add(lblPortName);
		frmMainWindow.getContentPane().add(txtPortName);
		frmMainWindow.getContentPane().add(lblPortInterface);
		frmMainWindow.getContentPane().add(txtPortInterface);
		frmMainWindow.getContentPane().add(lblArg);
		frmMainWindow.getContentPane().add(txtArg);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 160, 764, 7);
		frmMainWindow.getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel("STATUS:");
		lblNewLabel_1.setBounds(20, 165, 55, 14);
		frmMainWindow.getContentPane().add(lblNewLabel_1);

		lblStatus = new JLabel("");
		lblStatus.setBounds(88, 165, 342, 14);
		frmMainWindow.getContentPane().add(lblStatus);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 104, 602, 13);
		frmMainWindow.getContentPane().add(separator_1);

		txtUID = new JTextField();
		txtUID.setBounds(92, 128, 293, 20);
		frmMainWindow.getContentPane().add(txtUID);
		txtUID.setColumns(10);

		JLabel lblDevIdLabel = new JLabel("UID:");
		lblDevIdLabel.setBounds(55, 130, 30, 14);
		frmMainWindow.getContentPane().add(lblDevIdLabel);

		chkAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblReaderType.setEnabled(!lblReaderType.isEnabled());
				lblPortName.setEnabled(!lblPortName.isEnabled());
				lblPortInterface.setEnabled(!lblPortInterface.isEnabled());
				lblArg.setEnabled(!lblArg.isEnabled());

				txtReaderType.setEnabled(!txtReaderType.isEnabled());
				txtPortName.setEnabled(!txtPortName.isEnabled());
				txtPortInterface.setEnabled(!txtPortInterface.isEnabled());
				txtArg.setEnabled(!txtArg.isEnabled());

			}
		});
	}

	// ------------------------------------------------------------------------------

	private void btnReaderOpenActionPerformed(java.awt.event.ActionEvent evt) {

		int status = 0x54;
		String readerType = txtReaderType.getText();
		String portName = txtPortName.getText();
		String portInterface = txtPortInterface.getText();
		String arg = txtArg.getText();

		if (!chkAdvanced.isSelected()) {
			status = ufr.ReaderOpen();
		} else {
			if (readerType.equals("") || portInterface.equals("")) {
				JOptionPane.showMessageDialog(null,
						"You have to fill in required parameters for ReaderOpenEx: Reader type, Port interface",
						"ReaderOpenEx parameters error:", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				int reader_type = Integer.parseInt(readerType);
				byte[] bportName = portName.getBytes(StandardCharsets.US_ASCII);
				int port_interface = 0;

				if (portInterface.equals("U")) {
					port_interface = 85;
				} else if (portInterface.equals("T")) {
					port_interface = 84;
				} else {
					port_interface = Integer.parseInt(portInterface);
				}

				byte[] bArg = arg.getBytes(StandardCharsets.US_ASCII);

				status = ufr.ReaderOpenEx(reader_type, bportName, port_interface, bArg);
			}
		}

		if (status == 0) {
			ufr.ReaderUISignal(1, 1);

			int delay = 150; // milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					int status = 0x54;

					txtUID.setText("");

					status = ufr.s_block_deselect((byte) 100);
					try {
						Thread.sleep(110);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					ByteByReference card_type = new ByteByReference((byte) 0);
					
					PointerByReference rapdu_ptr = new PointerByReference();

					String UIDStr = "";

					status = ufr.GetDlogicCardType(card_type);
					byte db = card_type.getValue();
					if (card_type.getValue() == (byte) 0x40) {
						status = ufr.SetISO14443_4_Mode();
						if (status != 0) {
							System.out.println(" Error occurred while switching into ISO 14443-4 mode, uFR status is: "
									+ ufr.UFR_Status2String(status));
							lblStatus.setText(ufr.UFR_Status2String(status));

							status = ufr.s_block_deselect((byte) 100);
							return;
						}

						status = ufr.APDUHexStrTransceive("00 A4 04 00 06 F00102030406 00", rapdu_ptr);
						if (status != 0) {
							System.out.println(" Error occurred while sending APDU command, uFR status is: "
									+ ufr.UFR_Status2String(status));
							lblStatus.setText(ufr.UFR_Status2String(status));

							status = ufr.s_block_deselect((byte) 100);
							return;
						} else {
							String rapduStr = rapdu_ptr.getValue().getString(0);
							if (rapduStr.length() != 16) {
								// Android app service probably didn't return correct ID. Or it encountered an
								// error.
								txtUID.setText("0000000000000000");
								return;
							}

							txtUID.setText(rapduStr);
							lblStatus.setText(ufr.UFR_Status2String(status));

							status = ufr.s_block_deselect((byte) 100);
						}
					} else {
						UIDStr = "";
						ByteByReference type = new ByteByReference((byte) 0);
						byte[] uid = new byte[16];
						ByteByReference uid_size = new ByteByReference((byte) 0);
						status = ufr.GetCardIdEx(card_type, uid, uid_size);

						if (status == 0) {
							for (byte bCount = 0; bCount < uid_size.getValue(); bCount++) {
								UIDStr += Integer.toHexString((((char) uid[bCount] & 0xFF)));
							}

							txtUID.setText(UIDStr.toUpperCase().toUpperCase());
						}

						lblStatus.setText(ufr.UFR_Status2String(status));
					}
				}
			};

			tmr = new javax.swing.Timer(delay, taskPerformer);
			tmr.start();
		}

		lblStatus.setText(ufr.UFR_Status2String(status));
	}

	// ------------------------------------------------------------------------------

	private void btnReaderCloseActionPerformed(java.awt.event.ActionEvent evt) {
		if (tmr != null) {
			tmr.stop();
			txtUID.setText("");
		}

		int status = 0x54;

		status = ufr.ReaderClose();
		lblStatus.setText(ufr.UFR_Status2String(status));
	}

	// ------------------------------------------------------------------------------

	private void btnReaderResetActionPerformed(java.awt.event.ActionEvent evt) {
		int status = 0x54;

		status = ufr.ReaderReset();
		lblStatus.setText(ufr.UFR_Status2String(status));
	}
}
