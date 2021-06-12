package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	
	public CollectionView(JFrame f, Collection c) {
		super(f, c.getName(), true);
		Object tableColumnsATM[] = { "-", "Id", "Elõadó", "Album","Évjárat","Cím","Stílus","Zeneszámok(db)" };
		atm = new AlbumTableModel(tableColumnsATM, 0);
		
		Utility.AlbumArrayListToTableModel(c.getAlbums(), atm);
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);

		JButton btnBezar = new JButton("Bezar");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); setVisible(false);
			}
		});
		btnBezar.setBounds(270, 219, 97, 25);
		getContentPane().add(btnBezar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 482, 218);
		getContentPane().add(scrollPane);

		table = new JTable(atm);
		scrollPane.setViewportView(table);
	}

}
