package proje2;

public class BirAdimIlerlet {
	private final Dunya dunya;
	public BirAdimIlerlet(Dunya dunya) {
		this.dunya=dunya;
	}
	public boolean calistir(Robot r) {
		Harita harita=dunya.getHarita();
		Konum hedef=r.getKonum().ilerle(r.getYon());//robotun konumunu aldık ve yone gore ilerlettik
		if(!harita.icinde(hedef)) {//haritanin icinde mi
			return false;
		}
		
		if(dunya.robotVarmi(hedef)) {
			return false;
		}
		Zemin zemin=harita.zemin(hedef);
		
		if(!zemin.gecilebilir(r.getGovde())) {
			return false;
		}
		int maliyet=zemin.maliyet(r.getGovde());
		if(zemin.savurur(r.getGovde())) {
			r.enerjiHarca(maliyet);
			return false;
		}
		Konum yeni=hedef;
		if(zemin.atla(r.getGovde())) {
			yeni=hedef.ilerle(r.getYon());
			if(!harita.icinde(yeni)) {
				return false;
			}
			if(dunya.robotVarmi(yeni)) {
				return false;
			}
			if(!harita.zemin(yeni).inisyapabilir(r.getGovde())) {
				return false;
			}
		}
		else if(zemin.kaydirir(r.getGovde())) {
			yeni=kay(r,hedef);
		}
		if(!r.hareket(yeni, maliyet)) {
			return false;
		}
	if(harita.zemin(yeni).teslim()) {
		r.teslimEt();
		return false;
	}
	return true;
		
		
	}
	private Konum kay(Robot r, Konum baslangic) {
		Harita harita=dunya.getHarita();
		Konum sonBuz=baslangic;
		Konum onundeki=sonBuz.ilerle(r.getYon());
		while(harita.icinde(onundeki) && !dunya.robotVarmi(onundeki) && harita.zemin(onundeki).kaydirir(r.getGovde())){
			sonBuz=onundeki;
			onundeki=sonBuz.ilerle(r.getYon());
		}
		return sonBuz;
		
	}
}
