package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GUI.Tables.AlbumTableModel;
import Models.Album;
import Models.Collection;
import Utility.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class NewCollection extends JDialog {
	private JTextField collectionName;
	private JTable table;
	private AlbumTableModel atm;
	
	private Collection newCollection;
	private int exit=0;

	public NewCollection(JFrame f, ArrayList<Album> al, int maxKod) {
		super(f, true);
		Object tableColumnsATM[] = { "-", "Id", "Elõadó", "Album","Évjárat","Cím","Stílus","Zeneszámok(db)" };
		atm = new AlbumTableModel(tableColumnsATM, 0);

		Utility.AlbumArrayListToTableModel(al, atm);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblCollectionName = new JLabel("Collection Name:");
		lblCollectionName.setBounds(32, 36, 92, 14);
		getContentPane().add(lblCollectionName);

		collectionName = new JTextField();
		collectionName.setBounds(132, 33, 86, 20);
		getContentPane().add(collectionName);
		collectionName.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(266, 227, 89, 23);
		getContentPane().add(btnCancel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 61, 370, 127);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Create Collection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Utility.filled(collectionName)) {
					Utility.showMD("Gyûjtemény neve mezõ üres!", 0);
				} else {
					
					ArrayList<Album> aa = new ArrayList<Album>();
					
					int db = 0, x = 0;
					for (x = 0; x < atm.getRowCount(); x++)
						if ((Boolean) atm.getValueAt(x, 0)) { // ha kivan jelölve
							db++;
						}
					if (db == 0)
						Utility.showMD("Nincs kijelölve album", 0);
					else {
						for (int i = 0; i < atm.getRowCount(); i++)
							if ((Boolean) atm.getValueAt(i, 0)) { // ha kivan jelölve
								aa.add(al.get(i));
							}
						newCollection = new Collection(maxKod+1,Utility.ReadField(collectionName),aa);
						Utility.showMD("Gyûjtemény Hozzáadva", 1);
						exit=1;
						dispose();
						setVisible(false);
					}
				}
			}
		});
		btnNewButton.setBounds(52, 227, 89, 23);
		getContentPane().add(btnNewButton);
	}
	
	public Collection getCollection() {
		return newCollection;
	}
	public int Exit() {
		return exit;
	}
}
