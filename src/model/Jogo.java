package model;

public class Jogo {
	
	public String nome;
	public Double avg;
	
	@Override
	public String toString() {
		String ret = nome + ";" + avg;
		return ret;
	}
}
