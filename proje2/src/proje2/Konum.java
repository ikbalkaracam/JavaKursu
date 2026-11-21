package proje2;

public class Konum {
private final int x;
private final int y;
public Konum(int x,int y) {
	this.x=x;
	this.y=y;
}
public int getX() {//sayiyi oku ama degistirme
	return x;
}
public int getY() {
	return y;
}
public Konum ilerle(Yon yon) {
	return new Konum(x+yon.dx(),y+yon.dy());
}
public boolean ayniMi(Konum k) {
	return x==k.getX() && y==k.getY();
}
@Override
public String toString() {
	return x+" "+y;
}
}
