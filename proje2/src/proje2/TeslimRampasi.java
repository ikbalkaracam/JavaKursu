package proje2;

public class TeslimRampasi extends Zemin{
	@Override
	public boolean gecilebilir(Govde g){
		return true;
	}
	@Override
	public int maliyet(Govde g) {
		return Maliyetler.RAMPA;
	}
	@Override
	public boolean teslim() {
		return true;
	}
	@Override
	public char sembol() {
		return 'H';
	}

}
