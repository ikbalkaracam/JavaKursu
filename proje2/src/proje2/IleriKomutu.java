package proje2;

public class IleriKomutu implements Komut{
	public static final int min=1;
	public static final int max=99;
	private final int adimsayisi;
	public IleriKomutu(int adimsayisi) {
		if(adimsayisi<min || adimsayisi>max) {
			   throw new IllegalArgumentException(
		                "ILERI adim sayisi " + min + "-" + max
		                + " arasinda olmali: " + adimsayisi);
		        }
			this.adimsayisi=adimsayisi;
		}
	@Override
	public void uygula(Robot r,BirAdimIlerlet adim) {
		for(int i=0;i<adimsayisi;i++) {
			if(!adim.calistir(r)) {
				break;
			}
		}
	}
}
