package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Models.*;
import Utility.Utility;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import GUI.Tables.AlbumTableModel;

public class CollectionView extends JDialog {

	private JTable table;
	private AlbumTableModel atm;

	
	public CollectionView(JFrame f, Collection c, ArrayList<Album> AlbumArrayList) {
		super(f, c.getName(), true);
		Object tableColumnsATM[] = { "-", "Id", "Elõadó", "Album","Évjárat","Cím","Stílus","Zeneszámok(db)" };
		atm = new AlbumTableModel(tableColumnsATM, 0);
		
		Utility.AlbumArrayListToTableModel(c.getAlbums(), atm); //Collection Albums
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);

		JButton btnBezar = new JButton("Bezar");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); setVisible(false);
			}
		});
		btnBezar.setBounds(334, 220, 97, 25);
		getContentPane().add(btnBezar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 482, 218);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);
		
		JButton btnDeletealbum = new JButton("DeleteAlbum");
		btnDeletealbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel = 0, x = 0;
				for (x = 0; x < atm.getRowCount(); x++)
					if ((Boolean) atm.getValueAt(x, 0)) { // ha kivan jelölve
						db++;
						jel = x;
					}
				if (db == 0)
					Utility.showMD("Nincs kijelölve a törlendõ rekord!", 0);

				if (db > 1)
					Utility.showMD("Több rekord van kijelölve!\nEgyszerrecsak egy rekord törölhetõ!", 0);
				if (db == 1) {
					int Id = (int) atm.getValueAt(jel, 1);
					atm.removeRow(jel);
					Utility.AlbumArrayListRemoveById(c.getAlbums(),Id);
					Utility.showMD("A rekord törölve!", 1);
				}
			}
		});
		btnDeletealbum.setBounds(189, 221, 97, 25);
		getContentPane().add(btnDeletealbum);
	}

}
