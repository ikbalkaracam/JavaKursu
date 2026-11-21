package proje2;

public class TozFirtinasi extends Zemin{
	@Override
	public boolean gecilebilir(Govde g) {
		return true;
	}
	@Override
	public int maliyet(Govde g) {
		if(g.ucabilir()) {
			return Maliyetler.TOZ_UCAN;
			
		}
		return Maliyetler.TOZ_NORMAL;
	}
	@Override
	public boolean savurur(Govde g) {
		return g.ucabilir();
	}
	@Override
	public char sembol() {
		return 'T';
	}
	}

