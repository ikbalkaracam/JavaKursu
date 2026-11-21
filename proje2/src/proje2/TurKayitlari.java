package proje2;

public class TurKayitlari {
    private final Govde[] govdeler = {
        new Paletli(), new Sicrayan(), new Havali(), new Kizakli(), new Zirhli()
    };

    private final Zemin[] zeminler = {
        new DuzZemin(), new TozFirtinasi(), new KraterliKaya(), new MetanGolu(),
        new BuzKabugu(), new Engel(), new TeslimRampasi(), new AsitHavuzu()
    };

    public Govde govdeBul(String ad) {
        for (Govde govde : govdeler) {
            if (govde.ad().equals(ad)) {
                return govde;
            }
        }
        return null;
    }

    public Zemin zeminBul(char sembol) {
        for (Zemin zemin : zeminler) {
            if (zemin.sembol() == sembol) {
                return zemin;
            }
        }
        return null;
    }
}
