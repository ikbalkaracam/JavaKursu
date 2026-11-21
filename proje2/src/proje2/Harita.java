package proje2;

public class Harita {
private final int genislik;
private final int yukseklik;
private final Zemin[][] tablo;
public Harita(int genislik,int yukseklik) {
	this.genislik=genislik;
	this.yukseklik=yukseklik;
	this.tablo=new Zemin[yukseklik][genislik];	

for(int y=0;y<yukseklik;y++) {//sutun
	for(int x=0;x<genislik;x++) {//satir
		tablo[y][x]=new DuzZemin();
	}
}
}
public boolean icinde(Konum a) {
	int x=a.getX();
	int y=a.getY();
	return x>=0 && x<genislik && y>=0 && y<yukseklik;
}
public void yerlestir(Konum a,Zemin z) {
	tablo[a.getY()][a.getX()]=z;
}
public Zemin zemin(Konum a) {
	if(!icinde(a)) {
		return null;
	}
	else {
		return tablo[a.getY()][a.getX()];
	}
}


}

