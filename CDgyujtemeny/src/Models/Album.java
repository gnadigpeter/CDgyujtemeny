package Models;

import java.util.Date;

public class Album {
	private int Id;
	private String Eloado;
	private String Album;
	private Date Evjarat;
	private String Cim;
	private String Stilus;
	private int ZeneSzamokDb;

	public Album(int id, String eloado, String album, Date evjarat, String cim, String stilus, int zeneSzamokDb) {
		super();
		Id = id;
		Eloado = eloado;
		Album = album;
		Evjarat = evjarat;
		Cim = cim;
		Stilus = stilus;
		ZeneSzamokDb = zeneSzamokDb;
	}

	public String getEloado() {
		return Eloado;
	}

	public void setEloado(String eloado) {
		Eloado = eloado;
	}

	public String getAlbum() {
		return Album;
	}

	public void setAlbum(String album) {
		Album = album;
	}

	public Date getEvjarat() {
		return Evjarat;
	}

	public void setEvjarat(Date evjarat) {
		Evjarat = evjarat;
	}

	public String getCim() {
		return Cim;
	}

	public void setCim(String cim) {
		Cim = cim;
	}

	public String getStilus() {
		return Stilus;
	}

	public void setStilus(String stilus) {
		Stilus = stilus;
	}

	public int getZeneSzamokDb() {
		return ZeneSzamokDb;
	}

	public void setZeneSzamokDb(int zeneSzamokDb) {
		ZeneSzamokDb = zeneSzamokDb;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Album [Id=" + Id + ", Eloado=" + Eloado + ", Album=" + Album + ", Evjarat=" + Evjarat + ", Cim=" + Cim
				+ ", Stilus=" + Stilus + ", ZeneSzamokDb=" + ZeneSzamokDb + "]";
	}

	
}
