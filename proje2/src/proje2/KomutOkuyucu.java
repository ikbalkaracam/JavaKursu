package proje2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class KomutOkuyucu {
	private final String dosyaYolu;
	public KomutOkuyucu(String dosyaYolu) {
		this.dosyaYolu=dosyaYolu;
	}
	private String temizleme(String satir) {
		String metin=satir;
		int diyez=metin.indexOf('#');
		if(diyez!=-1) {
			metin=metin.substring(0,diyez);
		}
		return metin.trim();
	}
	private int satirSay() throws IOException {
		int sayi=0;
		try(BufferedReader okuyucu=new BufferedReader(new FileReader(dosyaYolu))){
			String satir=okuyucu.readLine();
			while(satir!=null) {
				if(!temizleme(satir).isEmpty()) {//isEmpty() bos mu
					sayi++;
				}
				satir=okuyucu.readLine();
			}
			
		}
		return sayi;
	}
	
	private Komut komutOlustur(String metin,int satirNo) throws DosyaHatalari{
		String[] parcalar=metin.split("\\s+");
		String ad=parcalar[0];
		if(ad.equals("ILERI")) {
			return ileriOlustur(parcalar,satirNo);
		}
		if(ad.equals("SAG")) {
			parametreKontrolu(parcalar,satirNo);
			return new SagKomutu();
		}
		if(ad.equals("SOL")) {
			parametreKontrolu(parcalar,satirNo);
			return new SolKomutu();
		}
		throw new DosyaHatalari(dosyaYolu,satirNo,"bilinmeyen komut:"+ad);
	}
	private Komut ileriOlustur(String[] parcalar,int satirNo) throws DosyaHatalari{
		if(parcalar.length!=2) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"ILERI komutu tam olarak sayi alir.");
		}
		int adim;
		try {
			adim=Integer.parseInt(parcalar[1]);
			
		}catch (NumberFormatException e) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"ILERI komutu icin gecersiz sayi girdiniz:"+parcalar[1]);
		}
		if (adim < IleriKomutu.min || adim > IleriKomutu.max) {
	        throw new DosyaHatalari(dosyaYolu, satirNo, "ILERI adim sayisi "
	                + IleriKomutu.min + "-" + IleriKomutu.max
	                + " arasinda olmali: " + adim);
		}
	        
		return new IleriKomutu(adim);
		
	}
	public Komut[] oku() throws IOException ,DosyaHatalari {
		Komut[] komutlar=new Komut[satirSay()];
		int sira=0;
		try(BufferedReader okuyucu=new BufferedReader(new FileReader(dosyaYolu))){
			int satirNo=0;
			String satir=okuyucu.readLine();
			while(satir!=null) {
				satirNo++;
				String metin=temizleme(satir);
				if(!metin.isEmpty()) {
					komutlar[sira]=komutOlustur(metin,satirNo);
					sira++;
				}
				satir=okuyucu.readLine();
			}
		}
		return komutlar;
	}
	private void parametreKontrolu(String[] parcalar,int satirNo) throws DosyaHatalari{
		if(parcalar.length!=1) {
			throw new DosyaHatalari(dosyaYolu,satirNo,parcalar[0]+"komut parametre almaz.");
		}
		
	}
	
 
}
