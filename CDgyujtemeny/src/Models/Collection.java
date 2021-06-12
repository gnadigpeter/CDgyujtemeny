package Models;

import java.util.ArrayList;

public class Collection {
	private int Id;
	private String Name;
	private ArrayList<Album> Albums = new ArrayList<Album>();
	
	
	public Collection(int id, String name, ArrayList<Album> albums) {
		super();
		Id = id;
		Name = name;
		Albums = albums;
	}
	
	public Collection(int id, String name) {
		super();
		Id = id;
		Name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	public ArrayList<Album> getAlbums() {
		return Albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		Albums = albums;
	}

	@Override
	public String toString() {
		return "Collection [Id=" + Id + ", Name=" + Name + ", Albums=" + Albums + "]";
	}
	
	
	
	
	
	
}
