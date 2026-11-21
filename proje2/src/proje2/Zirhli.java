package proje2;

public class Zirhli extends Govde{
@Override
public String ad() {
	return "ZIRHLI";
}
@Override
public boolean yetenegiVar(Yetenek yetenek) {
	return yetenek==Yetenek.ASIT_DAYANIKLI;
}
}
