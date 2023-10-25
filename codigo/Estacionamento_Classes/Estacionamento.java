import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Estacionamento {

    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private Map<String, Double> arrecadacaoPorPlaca;
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new Cliente[0];
        this.vagas = new Vaga[0];
        this.arrecadacaoPorPlaca = new HashMap<>();
        gerarVagas(quantFileiras,vagasPorFileira);
        salvarEstacionamento();
    }

    public Estacionamento(Vaga[] vaga, int fileiras, int vagasPorFileira, String Nome){
        this.nome=Nome;
        this.vagas = vaga;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFileira;
        this.clientes=Cliente.carregarClientes(nome);
        for (int i = 0; i < clientes.length;i++){
            Veiculo[] vec = clientes[i].getVeiculo();
            for (int j = 0; j < vec.length;j++){
                vec[j].setUsos(UsoDeVaga.carregarUsos(vec[j].getPlaca(), vagas));
            }
        }
    }



    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = buscarClientePorId(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

    public void addCliente(Cliente cliente) {
        Cliente[] novosClientes = new Cliente[clientes.length + 1];
        System.arraycopy(clientes, 0, novosClientes, 0, clientes.length);
        novosClientes[clientes.length] = cliente;
        clientes = novosClientes;
    }

    private void gerarVagas(int quantFileiras, int vagasPorFileira) {
        vagas = new Vaga[quantFileiras * vagasPorFileira];

        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas[i * vagasPorFileira + j + 1] = new Vaga(i, j);
                vagas[i * vagasPorFileira + j + 1].salvarVagas(nome);
            }
        }
    }


    public void estacionar(String placa) {
        Vaga vagaLivre = encontrarVagaLivre();
        if (vagaLivre != null) {
            for (int i=0 ; i < clientes.length;i++){
                Veiculo[] vec = clientes[i].getVeiculo();
                for (int j = 0; j < vec.length;j++){
                    if (vec[j].getPlaca()==placa)
                        vec[j].estacionar(vagaLivre);
                }}
            arrecadacaoPorPlaca.put(placa, 0.0);
        }
    }

    public double sair(String placa) {
        for (int i=0 ; i < clientes.length;i++){
            Veiculo[] vec = clientes[i].getVeiculo();
            for (int j = 0; j < vec.length;j++){
                if (vec[j].getPlaca()==placa)
                    return vec[j].sair();
            }}
        return 0.0;
    }

    public double totalArrecadado() {
        double total = 0.0;
        for (double valor : arrecadacaoPorPlaca.values()) {
            total += valor;
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double totalMes = 0.0;
        for (Map.Entry<String, Double> entry : arrecadacaoPorPlaca.entrySet()) {
            String chave = entry.getKey();
            int mesEntrada = Integer.parseInt(chave.substring(4, 6));
            if (mesEntrada == mes) {
                totalMes += entry.getValue();
            }
        }
        return totalMes;
    }

    public double valorMedioPorUso() {
        if (arrecadacaoPorPlaca.size() == 0) {
            return 0.0;
        }
        return totalArrecadado() / arrecadacaoPorPlaca.size();
    }

    public String top5Clientes(int mes) {
        // Falta colocar a implementacao
        return "Top 5 clientes do mês " + mes;
    }

    private Cliente buscarClientePorId(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }


    private Vaga encontrarVagaLivre() {
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
                return vaga;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public Cliente[] getClientes(){
        return clientes;
    }
    public void salvarEstacionamento() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Estacionamento.txt", true))) {
            writer.printf(this.nome + ";" + this.quantFileiras +";" + this.vagasPorFileira + "\n");
            System.out.println("Estacionamento salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Estacionamento[] carregarEstacionamento() {
        LinkedList<Estacionamento> estac = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Estacionamento.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String nome = parts[0];
                    int fileiras = Integer.parseInt(parts[1]);
                    int vagasf = Integer.parseInt(parts[2]);
                    Vaga[] vag = Vaga.carregarVagas(nome);
                    Estacionamento est = new Estacionamento(vag,fileiras, vagasf, nome);
                        estac.add(est);}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Estacionamento[] esta = new Estacionamento[estac.size()];
        estac.toArray(esta);
        return esta;
    }

}