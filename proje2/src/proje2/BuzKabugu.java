package proje2;

public class BuzKabugu extends Zemin {
	@Override
	public boolean gecilebilir(Govde g) {
		return true;
	}

	@Override
	public int maliyet(Govde g) {
		if (g.ucabilir()) {
			return Maliyetler.BUZ_UCAN;
		} else if (g.kayabilir()) {
			return Maliyetler.BUZ_KAYAN;
		}
		return Maliyetler.BUZ_NORMAL;
	}

	@Override
	public boolean kaydirir(Govde g) {
		return g.kayabilir();
	}
	@Override
	public char sembol() {
		return 'I';
	}
}
