import com.sun.source.tree.NewArrayTree;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Veiculo {

	private String placa;
	private String modelo;
	private LinkedList<UsoDeVaga>  usos;

	public Veiculo(String placa, String modelo) {
		this.placa=placa;
		this.modelo=modelo;
	}


	public void estacionar(Vaga vaga, Turnos turno, int Tipo) {
		switch (Tipo){
			case 1:
				UsoDeVaga newUso = new UsoHora(vaga);
				usos.add(newUso);
				break;
			case 2:
				UsoDeVaga newUso2 = new UsoMensal(vaga);
				usos.add(newUso2);
				break;
			case 3:
				UsoDeVaga newUso3 = new UsoTurno(vaga, turno);
				usos.add(newUso3);
				break;
		}
	}


	public void setUsos(UsoDeVaga[] usos) {
		if (usos != null) {
			this.usos = new LinkedList<>(Arrays.asList(usos));
		} else {
			throw new IllegalArgumentException("O array de usos não pode ser nulo.");
		}
	}
	public double sair() {
		usos.getLast().salvarUsoDeVaga(placa);
		return usos.getLast().sair();
	}

	public double totalArrecadado() {
		double valor = 0;
		for (UsoDeVaga uso : usos) {
			valor += uso.getValorPago();
		}
		return valor;
	}

	public double arrecadadoNoMes(int mes) {
		double valor = 0;
		for (UsoDeVaga uso : usos) {
			if (mes == uso.getEntrada().getMonthValue()) {
				valor += uso.getValorPago();
			}
		}
		return valor;
	}

	public String historico(LocalDate i, LocalDate f) {
		StringBuilder historico = new StringBuilder("Veiculo{" +
				"placa='" + placa + '\'' +
				", usos=[");
		if (i == null && f == null) {
			for (UsoDeVaga uso : usos) {
				historico.append(uso.toString()).append(", ");
			}
		} else {
			LinkedList<UsoDeVaga> usosMes = getUsosMes(i, f);
			for (UsoDeVaga uso : usosMes) {
				historico.append(uso.toString()).append(", ");
			}
		}
		if (historico.toString().endsWith(", ")) {
			historico.setLength(historico.length() - 2);
		}
		historico.append("]}");
		return historico.toString();
	}

	public LinkedList<UsoDeVaga> getUsosMes(LocalDate inicio, LocalDate fim) {
		LinkedList<UsoDeVaga> usosMes = new LinkedList<>();
		for (UsoDeVaga uso : usos) {
			if ((uso.getEntrada().isAfter(inicio) || uso.getEntrada().isEqual(inicio))
					&& (uso.getEntrada().isBefore(fim) || uso.getEntrada().isEqual(fim))) {
				usosMes.add(uso);
			}
		}
		return usosMes;
	}
	public void RelatorioOrdenado(int opcao) {
		List<UsoDeVaga> usosList = usos;
		for (int i = 0; i < usosList.size(); i++) {
			for (int j = i; j < usosList.size(); j++) {
				switch (opcao) {
					case 1:
						if (usosList.get(j).getValorPago() < usosList.get(i).getValorPago()) {
							UsoDeVaga controle = usosList.get(i);
							usosList.set(i, usosList.get(j));
							usosList.set(j, controle);
						}
						break;
					case 2:
						if (usosList.get(j).getEntrada().isAfter(usosList.get(i).getEntrada())) {
							UsoDeVaga controle = usosList.get(i);
							usosList.set(i, usosList.get(j));
							usosList.set(j, controle);
						}
						break;
				}
			}
		}
		for (UsoDeVaga uso : usosList) {
			System.out.println(uso.toString());
		}
	}


	public int getTotalDeUsos() {
		return usos.size();
	}
	public String getPlaca(){return placa;}

	public void salvarVeiculo(String Id) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Veiculos.txt", true))) {
			writer.printf(this.placa + ";" + this.modelo + ";" + Id + "\n");
			System.out.println("Veiculo salvo com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static Veiculo[] carregarVeiculos(String IdCliente) {
		LinkedList<Veiculo> veiculos = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("Veiculos.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length == 3) {
					String placa = parts[0];
					String modelo = parts[1];
					String id = parts[2];
					if(IdCliente == id ){
						Veiculo vec = new Veiculo(placa, modelo);
						veiculos.add(vec);}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Veiculo[]vec = new Veiculo[veiculos.size()];
		veiculos.toArray(vec);
		return vec;
	}

}