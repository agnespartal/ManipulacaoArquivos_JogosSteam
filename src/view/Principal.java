package view;

import controller.SteamController;

public class Principal {

	public static void main(String[] args) throws Exception {
		String caminho = "C:\\TEMP";
		String arquivo = "SteamCharts.csv";
		int ano = 2021;
		String mes = "February";
		Double avg = 500.0;
		SteamController steamCRT = new SteamController();
		steamCRT.analiseJogos(caminho, arquivo, ano, mes, avg);
	}

}
