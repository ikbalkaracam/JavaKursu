package proje2;

public class MetanGolu extends Zemin{
	@Override
	public boolean gecilebilir(Govde g) {
		return g.ucabilir();
	}
@Override
public int maliyet(Govde g) {
	return Maliyetler.METAN_UCAN;
}
@Override
public char sembol() {
	return '~';
}
}


