import com.sun.source.tree.NewArrayTree;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Veiculo {

	private String placa;
	private String modelo;
	private UsoDeVaga[] usos;

	public Veiculo(String placa, String modelo) {
		this.placa=placa;
		this.modelo=modelo;
	}


	public void estacionar(Vaga vaga) {
		if (usos == null) {
			usos = new UsoDeVaga[1];
			usos[0] = new UsoDeVaga(vaga);
			usos[0].salvarUsoDeVaga(placa);
		} else {
			UsoDeVaga[] newUsos = new UsoDeVaga[usos.length + 1];
			for (int i = 0; i < usos.length; i++) {
				newUsos[i] = usos[i];
			}
			newUsos[usos.length] = new UsoDeVaga(vaga);
			usos = newUsos;
			usos[usos.length-1].salvarUsoDeVaga(placa);
		}
	}


	public void setUsos(UsoDeVaga[] usos){
		this.usos=usos;
	}
	public double sair() {
		return usos[usos.length - 1].sair();
	}

	public double totalArrecadado() {
		double valor = 0;
		for (int i = 0; i < usos.length;i++){
			valor += usos[i].getValorPago();
		}
		return valor;
	}

	public double arrecadadoNoMes(int mes) {
		double valor = 0;
		for (int i = 0; i < usos.length;i++){
			if(mes == usos[i].getMes())valor += usos[i].getValorPago();
		}
		return valor;
	}

	public String historico(LocalDate i, LocalDate f) {
		if(i==null && f== null){
			return "Veiculo{" +
					"placa='" + placa + '\'' +
					", usos=" + Arrays.toString(usos) +
					'}';}
		else{
			return "Veiculo{" +
					"placa='" + placa + '\'' +
					", usos=" + Arrays.toString(new LinkedList[]{getUsosMes(i, f)}) +
					'}';}
	}

	public LinkedList<UsoDeVaga> getUsosMes(LocalDate i, LocalDate f){
		LinkedList<UsoDeVaga> u = new LinkedList<>();
		for (int j = 0; j < usos.length;j++){
			if (usos[j].getEntrada().isAfter(i)||usos[j].getEntrada().isEqual(i)&&usos[j].getEntrada().isBefore(f)||usos[j].getEntrada().isEqual(f))
				u.add(usos[j]);
		}
		return u;
	}


	public int getTotalDeUsos() {
		return usos.length;
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