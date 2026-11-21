package proje2;

public class DosyaHatalari extends Exception{
	private final String dosyaAdi;
	private final int satirNo;
	private final String sebep;
	public DosyaHatalari(String dosyaAdi,int satirNo,String sebep) {
		this.dosyaAdi=dosyaAdi;
		this.satirNo=satirNo;
		this.sebep=sebep;
	}
	public String getMesaj() {
		return dosyaAdi+" satir "+satirNo+" : "+sebep;
	}

}
