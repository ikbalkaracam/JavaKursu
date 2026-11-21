package proje2;

public class AsitHavuzu extends Zemin {
public static final int MALIYET_DAYANIKLI=1;
public static final int MALIYET_UCAN = 2;
public static final int MALIYET_NORMAL = 5;
@Override
public boolean gecilebilir(Govde govde) {
	return true;
}
@Override
public int maliyet(Govde govde) {
	if(govde.yetenegiVar(Yetenek.ASIT_DAYANIKLI)) {
		return MALIYET_DAYANIKLI;
	}
	if(govde.ucabilir()) {
		return MALIYET_UCAN;
	}
	return MALIYET_NORMAL;
}
@Override
public char sembol() {
	return 'A';
}
}
