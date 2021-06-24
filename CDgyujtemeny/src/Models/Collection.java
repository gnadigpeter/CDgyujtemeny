package Models;

import java.util.ArrayList;

public class Collection {
	private int Id;
	private String Name;
	//private ArrayList<Album> Albums = new ArrayList<Album>();
	private ArrayList<Integer> AlbumIds = new ArrayList<Integer>();
	
	/*
	public Collection(int id, String name, ArrayList<Album> albums) {
		super();
		Id = id;
		Name = name;
		Albums = albums;
	}*/
	
	public Collection(int id, String name, ArrayList<Integer> albumIds) {
		super();
		Id = id;
		Name = name;
		AlbumIds = albumIds;
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

	public ArrayList<Integer> getAlbumIds() {
		return AlbumIds;
	}

	public void setAlbumIds(ArrayList<Integer> albumIds) {
		AlbumIds = albumIds;
	}

	@Override
	public String toString() {
		return "Collection [Id=" + Id + ", Name=" + Name + ", AlbumIds=" + AlbumIds + "]";
	}
	
	

/*
	public ArrayList<Album> getAlbums() {
		return Albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		Albums = albums;
	}

	@Override
	public String toString() {
		return "Collection [Id=" + Id + ", Name=" + Name + ", Albums=" + Albums + "]";
	}*/
	
	
	
	
	
	
}
