package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.Tables.CollectionTableModel;
import Models.Album;
import Models.Collection;
import Utility.Utility;

public class FileManager {

	private String Id="", Name="", NumberOfSongs="";
	
	public static int StoI(String s) {
		int x = -55;
		x = Integer.parseInt(s);
		return x;
	}// end StoI
	
	
	public static void CsvReaderAlbum(File fnev, ArrayList<Album> ctm) {
		String s = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(fnev));
			s = in.readLine(); // === mezõnevek az elsõ sorból
			s = in.readLine(); // === adatsor

			while (s != null) {
				String[] st = s.split(";"); //tömbbe tördeli az adatokat
				Album aaa = new Album(Utility.StringToInt(st[0]),st[1],st[2],Utility.StringToDate(st[3]),st[4],st[5],Utility.StringToInt(st[6]));
				ctm.add(aaa);
				s = in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: " + ioe.getMessage(), "", 0);
		}
	}// end CsvReader
	/*
	public static void CsvReaderCollection(File fnev, ArrayList<Collection> ctm) {
		String s = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(fnev));
			s = in.readLine(); // === mezõnevek az elsõ sorból
			s = in.readLine(); // === adatsor

			while (s != null) {
				String[] st = s.split(";"); //tömbbe tördeli az adatokat
				ArrayList<Album> a = new ArrayList<Album>();
				for(int i=2;i<st.length;i=i+7) {
					a.add(new Album(Utility.StringToInt(st[i]), st[i+1], st[i+2],Utility.StringToDate(st[i+3]) , st[i+4] , st[i+5] , Utility.StringToInt(st[i+6])));
				}
				
				Collection aaa = new Collection(Utility.StringToInt(st[0]),st[1],a);
				ctm.add(aaa);
				s = in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: " + ioe.getMessage(), "", 0);
		}
	}// end CsvReader
	*/
	public static void CsvReaderCollection2(File fnev, ArrayList<Collection> ctm, ArrayList<Album> al) {
		String s = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(fnev));
			s = in.readLine(); // === mezõnevek az elsõ sorból
			s = in.readLine(); // === adatsor

			while (s != null) {
				String[] st = s.split(";"); //tömbbe tördeli az adatokat
				ArrayList<Album> a = new ArrayList<Album>();
				for(int i=2;i<st.length;i++) {
					try{
						a.add(al.get(Utility.AlbumArrayListFindId(al, Utility.StringToInt(st[i]))));
					}catch(ArrayIndexOutOfBoundsException exception){
						System.out.println("Hibás AlbumID");
						String ErrorMessage = "Hibás Album Id volt a beolvasott adatok közt\n Hibás Gyûjtemény: " + st[1] + "!";
						Utility.showMD(ErrorMessage, 1);
					}
				}
				
				Collection aaa = new Collection(Utility.StringToInt(st[0]),st[1],a);
				ctm.add(aaa);
				s = in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: " + ioe.getMessage(), "", 0);
		}
	}// end CsvReader
	
	public static void CsvWriterAlbum(String fnev, ArrayList<Album> ctm) {
		try {
			File file = new File(fnev);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Album");
	        bw.newLine();
	        for(int i=0;i<ctm.size();i++)
	        {
	            bw.write(ctm.get(i).getId()+";");
	            bw.write(ctm.get(i).getEloado()+";");
	            bw.write(ctm.get(i).getAlbum()+";");
				bw.write(Utility.DateToString(ctm.get(i).getEvjarat())+";");
	            bw.write(ctm.get(i).getCim()+";");
	            bw.write(ctm.get(i).getStilus()+";");
	            bw.write(ctm.get(i).getZeneSzamokDb()+";");
	            bw.newLine();
	        }
	        bw.close();
	        fw.close();
	        
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvWriter: " + ioe.getMessage(), "", 0);
		}
	}// end csvWriter
	/*
	public static void CsvWriterCollection(String fnev, ArrayList<Collection> ctm) {
		try {
			File file = new File(fnev);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Collection");
	        bw.newLine();
	        for(int i=0;i<ctm.size();i++)
	        {
	            bw.write(ctm.get(i).getId()+";");
	            bw.write(ctm.get(i).getName()+";");
	            
	            bw.write(AlbumToCSVArrayList(ctm.get(i).getAlbums()));
	            bw.newLine();
	        }
	        bw.close();
	        fw.close();
	        
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvWriter: " + ioe.getMessage(), "", 0);
		}
	}// end csvWriter
	*/
	public static void CsvWriterCollection2(String fnev, ArrayList<Collection> ctm) {
		try {
			File file = new File(fnev);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("Collection");
	        bw.newLine();
	        for(int i=0;i<ctm.size();i++)
	        {
	            bw.write(ctm.get(i).getId()+";");
	            bw.write(ctm.get(i).getName()+";");
	            
	            bw.write(AlbumsIds(ctm.get(i).getAlbums()));
	            bw.newLine();
	        }
	        bw.close();
	        fw.close();
	        
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", "", 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvWriter: " + ioe.getMessage(), "", 0);
		}
	}// end csvWriter
	
	public static String AlbumsIds(ArrayList<Album> al) {
		String albumIds = "";
		for(int i=0;i<al.size();i++)
        {
			albumIds += al.get(i).getId()+";";
        }
		return albumIds;
	}
	
	public static String AlbumToCSVArrayList (ArrayList<Album> al) {
		//ArrayList<String> aaaa = new ArrayList();
		String tordelt=""; 
		for(int i=0;i<al.size();i++)
	        {
				
				tordelt += al.get(i).getId()+";";
				tordelt += al.get(i).getEloado()+";";
				tordelt += al.get(i).getAlbum()+";";
				tordelt += Utility.DateToString(al.get(i).getEvjarat())+";";
				tordelt += al.get(i).getCim()+";";
				tordelt += al.get(i).getStilus()+";";
				tordelt += al.get(i).getZeneSzamokDb()+";";
				//aaaa.add(tordelt);
	        }
		return tordelt;
	}
	
}
