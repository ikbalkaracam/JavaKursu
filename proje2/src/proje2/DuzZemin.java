package proje2;

public class DuzZemin extends Zemin {
	@Override
	public boolean gecilebilir(Govde g) {
		return true;
	}
	@Override
	public int maliyet(Govde g) {
		return Maliyetler.DUZ_ZEMIN;
	}
	@Override
	public char sembol() {
		return '.';
	}

}
