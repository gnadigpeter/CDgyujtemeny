package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Album;
import Utility.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class NewAlbum extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField eloado;
	private JTextField album;
	private JTextField evjarat;
	private JTextField cim;
	private JTextField stilus;
	private JTextField numberOfSongs;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

	private Album newAlbum;
	private int exit = 0;

	public NewAlbum(JFrame f, int maxId) {
		super(f, true);
		setTitle("\u00DAj album felvitele");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("El\u0151ad\u00F3");
		lblNewLabel.setBounds(10, 11, 110, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblAlbum = new JLabel("Album:");
		lblAlbum.setBounds(10, 40, 110, 14);
		getContentPane().add(lblAlbum);

		JLabel lblvjrat = new JLabel("\u00C9vj\u00E1rat:");
		lblvjrat.setBounds(10, 65, 110, 14);
		getContentPane().add(lblvjrat);

		JLabel lblCm = new JLabel("C\u00EDm:");
		lblCm.setBounds(10, 101, 110, 14);
		getContentPane().add(lblCm);

		JLabel lblStlus = new JLabel("St\u00EDlus");
		lblStlus.setBounds(10, 134, 110, 14);
		getContentPane().add(lblStlus);

		JLabel lblZeneSzmok = new JLabel("Zene Sz\u00E1mok:");
		lblZeneSzmok.setBounds(10, 173, 110, 14);
		getContentPane().add(lblZeneSzmok);

		eloado = new JTextField();
		eloado.setBounds(135, 8, 179, 20);
		getContentPane().add(eloado);
		eloado.setColumns(10);

		album = new JTextField();
		album.setBounds(135, 37, 179, 20);
		getContentPane().add(album);
		album.setColumns(10);

		evjarat = new JTextField();
		evjarat.setBounds(135, 62, 86, 20);
		getContentPane().add(evjarat);
		evjarat.setColumns(10);

		cim = new JTextField();
		cim.setBounds(135, 98, 179, 20);
		getContentPane().add(cim);
		cim.setColumns(10);

		stilus = new JTextField();
		stilus.setBounds(135, 131, 179, 20);
		getContentPane().add(stilus);
		stilus.setColumns(10);

		numberOfSongs = new JTextField();
		numberOfSongs.setBounds(135, 170, 86, 20);
		getContentPane().add(numberOfSongs);
		numberOfSongs.setColumns(10);

		JLabel lblhhnn = new JLabel("\u00E9\u00E9\u00E9\u00E9.hh.nn");
		lblhhnn.setBounds(243, 65, 71, 14);
		getContentPane().add(lblhhnn);

		JButton btnAddAlbum = new JButton("Add Album");
		btnAddAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Utility.filled(eloado)) {
					Utility.showMD("Elõadó mezõ üres!", 0);
				} else if (!Utility.filled(album)) {
					Utility.showMD("Album mezõ üres!", 0);
				} else if (!Utility.goodDate(evjarat)) {
					Utility.showMD("Évjárat mezõben hibásadat van!", 0);
				} else if (!Utility.filled(cim)) {
					Utility.showMD("Cím mezõ üres!", 0);
				} else if (!Utility.filled(stilus)) {
					Utility.showMD("Stílus mezõ üres!", 0);
				} else if (!Utility.filled(numberOfSongs)) {
					Utility.showMD("ZeneSzámok mezõ üres!", 0);
				} else if (!Utility.goodInt(numberOfSongs)) {
					Utility.showMD("ZeneSzámok mezõben hibás adat van!", 0);
				} else {
					newAlbum = new Album(maxId + 1, Utility.ReadField(eloado), Utility.ReadField(album),
							Utility.StringToDate(Utility.ReadField(evjarat)), Utility.ReadField(cim), Utility.ReadField(stilus),
							Utility.StringToInt(Utility.ReadField(numberOfSongs)));
					Utility.showMD("Adat beszúrva!", 1);
					exit = 1;
					dispose();
					setVisible(false);
				}
			}
		});
		btnAddAlbum.setBounds(54, 227, 89, 23);
		getContentPane().add(btnAddAlbum);

		JButton btnClose = new JButton("Bez\u00E1r");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(257, 227, 89, 23);
		getContentPane().add(btnClose);

		JLabel lblDb = new JLabel("db");
		lblDb.setBounds(243, 173, 46, 14);
		getContentPane().add(lblDb);

	}// end konstruktor


	
	public Album getAlbum() {
		return newAlbum;
	}

	public int Exit() {
		return exit;
	}

}
