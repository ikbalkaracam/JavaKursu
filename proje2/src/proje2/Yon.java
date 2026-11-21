package proje2;

public enum Yon {
	E(1, 0),
	S(0, 1),
	W(-1, 0),
	N(0, -1);//D G B K
	 
	private final int dx;
	private final int dy;
	Yon(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
	}
	public int dx() {
		return dx;
	}
	public int dy() {
		return dy;
	}
	public Yon sag() {
		return values()[(ordinal()+1)%4];
	}
	public Yon sol() {
		return values()[(ordinal()+3)%4];
	}
	}



