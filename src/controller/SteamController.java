package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Jogo;
import model.ListaObject;

public class SteamController {

	public SteamController() {
		super();
	}

	public void analiseJogos(String caminho, String arquivo, int ano, String mes, double avg) throws Exception {
		ListaObject l = new ListaObject();
		File arq = new File(caminho, arquivo);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leFluxo);
			String linha = buffer.readLine();
			System.out.format("%-80s\t" + "%-30s\n","Nome do Jogo","Media de Jogadores Ativos");
			while (linha != null) {
				if (linha.contains(",")) {
					String[] vetLinha = linha.split(",");
					if (vetLinha[1].equals(Integer.toString(ano)) && vetLinha[2].equals(mes) && Double.valueOf(vetLinha[3]).doubleValue() >= avg) {
						System.out.format("%-80s\t" + "%-11s\n", vetLinha[0], vetLinha[3]);
						Jogo j = new Jogo();
						j.nome = vetLinha[0];
						j.avg = Double.valueOf(vetLinha[3]).doubleValue();
						if (l.isEmpty()) {
							l.addFirst(j);
						}else {
							l.addLast(j);
						}
					}
					linha = buffer.readLine();
				}
			}
			buffer.close();
			leFluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido ou não existe");
		}
		String nomeArquivo = "Jogos"+ano+"-"+mes+".csv";
		gravaJogos(l, caminho, nomeArquivo, ano, mes, avg);
	}
	
	private void gravaJogos (ListaObject l, String caminho,String nomeArquivo, int ano, String mes, double avg) throws Exception {
		File dir = new File(caminho);
		if (dir.exists() && dir.isDirectory()) {
			File arq = new File(caminho, nomeArquivo);
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			
			StringBuffer buffer = new StringBuffer();
			int tamanho = l.size();
			String linha = "";
			for (int i = 0; i < tamanho; i++) {
				linha = l.get(i).toString();
				buffer.append(linha + "\r\n");
			}
			
			FileWriter abreArquivo = new FileWriter(arq, existe);
			PrintWriter escreveArq = new PrintWriter(abreArquivo);
			escreveArq.write(buffer.toString());
			escreveArq.flush();
			escreveArq.close();
			abreArquivo.close();
		} else {
			throw new IOException("Diretório inválido");
		}
	}
}
