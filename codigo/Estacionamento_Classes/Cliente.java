import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.*;

public class Cliente {

	private String nome;
	private String id;
	private Veiculo[] veiculo;


	public String getId(){
		return id;
	}

	public String getNome(){
		return nome;
	}

	public Veiculo[] getVeiculo(){
		return veiculo;
	}


	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;

		
	}


	public void addVeiculo(Veiculo[] veiculo) {
  
    List<Veiculo> veiculoList = new ArrayList<>();
    Collections.addAll(veiculoList, veiculo);
    
}

	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("########").append("\n");
        sb.append("Cliente ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        return sb.toString();
    }


	public Veiculo possuiVeiculo(String placa) {
		
	}

	public int totalDeUsos() {
		
	}

	public double arrecadadoPorVeiculo(String placa) {
		
	}

	public double arrecadadoTotal() {
		System.out.println(total);
	}

	public double arrecadadoNoMes(int mes) {
		System.out.println(totalMes);
	}


}
