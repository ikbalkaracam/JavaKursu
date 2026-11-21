package proje2;
import java.io.IOException;
public class Proje2 {


	    public static void main(String[] args) {
	        if (args.length != 2) {
	            System.out.println("Kullanim: java proje2.Proje2 <harita.txt> <komutlar.txt>");
	            return;
	        }
	        try {
	            Dunya dunya = new HaritaOkuyucu(args[0]).oku();
	            Komut[] komutlar = new KomutOkuyucu(args[1]).oku();

	            Simulasyon simulasyon = new Simulasyon(dunya);
	            simulasyon.calistir(komutlar);
	            simulasyon.sonuc();
	        } catch (DosyaHatalari e) {
	            System.out.println("HATA: " + e.getMesaj());
	        } catch (IOException e) {
	            System.out.println("Dosya okunamadi: " + e.getMessage());
	        }
	    }
	}
		// TODO Auto-generated method stub

	


