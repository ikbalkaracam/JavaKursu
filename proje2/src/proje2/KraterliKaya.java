package proje2;

public class KraterliKaya extends Zemin {
	@Override
	public boolean gecilebilir(Govde g) {
		return true;
	}
	@Override
	public int maliyet(Govde g) {
		if(g.atlayabilir()) {
			return Maliyetler.KAYA_ATLAYAN;
		}
		else if(g.ucabilir()) {
			return Maliyetler.KAYA_UCAN;
		}
		return Maliyetler.KAYA_NORMAL;
	}
	@Override
	public boolean atla(Govde g) {
		return g.atlayabilir();
	}
@Override
public boolean inisyapabilir(Govde g) {
	return false;//kayanin ustune inemez
}
@Override
public char sembol() {
	return 'K';
}
}


