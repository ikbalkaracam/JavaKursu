package proje2;

public class Engel extends Zemin {
	@Override
	public boolean gecilebilir(Govde g) {
		return false;
	}
@Override
public int maliyet(Govde g) {
	return Maliyetler.ENGEL;
}
@Override
public char sembol() {
	return 'X';
}
}
