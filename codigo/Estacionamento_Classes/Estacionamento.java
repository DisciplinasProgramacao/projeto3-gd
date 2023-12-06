import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Observable;

public class Estacionamento extends Observable {

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
        clientes[clientes.length-1].salvarCliente(nome);
    }

    private void gerarVagas(int quantFileiras, int vagasPorFileira) {
        vagas = new Vaga[quantFileiras * vagasPorFileira];

        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas[i + j] = new Vaga(i+1, j + 1);
                vagas[i + j].salvarVagas(nome);
            }
        }
    }


    public void estacionar(String placa) {
        Vaga vagaLivre = encontrarVagaLivre();
        if (vagaLivre != null) {
            for (int i=0 ; i < clientes.length;i++){
                clientes[i].estacionar(placa, vagaLivre);
            }
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

        public double calcularValorMedioPorUso(Estacionamento[] estacionamentos) {
        double totalValorPago = 0.0;
        int totalUsos = 0;
    
        for (Estacionamento estacionamento : estacionamentos) {
            Cliente[] clientes = estacionamento.getClientes();
            for (Cliente cliente : clientes) {
                Veiculo[] veiculos = cliente.getVeiculo();
                for (Veiculo veiculo : veiculos) {
                    LinkedList<UsoDeVaga> usos = veiculo.getUsos();
                    for (UsoDeVaga uso : usos) {
                        totalValorPago += uso.getValorPago();
                        totalUsos++;
                    }
                }
            }
        }
    
        if (totalUsos > 0) {
            return totalValorPago / totalUsos;
        } else {
            return 0.0;
        }
    }

    public double calcularValorTotal(Estacionamento estacionamento) {
        double valorTotalReal = 0.0;
        Cliente[] clientes = estacionamento.getClientes();
    
        for (Cliente cliente : clientes) {
            Veiculo[] veiculos = cliente.getVeiculo();
            for (Veiculo veiculo : veiculos) {
                LinkedList<UsoDeVaga> usos = veiculo.getUsos();
                for (UsoDeVaga uso : usos) {
                    double valorPago = uso.getValorPago();
                    double valorServicos = uso.getValorServicos();
                    valorTotalReal += valorPago + valorServicos;
                }
            }
        }
    
        return valorTotalReal;
    }

    public void arrecadacaoTotalDecrescente(){
        LinkedList<UsoDeVaga> usos = new LinkedList<>();
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                usos.addAll(cliente.relatorioTotalDecrescente());
            };
        }
        usos.sort((uso1, uso2) -> Long.compare((long) uso2.getValorPago(), (long) uso1.getValorPago()));
        for (int i = 0;i < usos.size();i++){
            System.out.println(i+1 + "- Data: " + usos.get(i).getEntrada() + " - VALOR: "+usos.get(i).getValorPago());
        }
    }

    public long mediaUsoMensalistaMesCorrente(int mes) {
        long usos = 0;
        long nclientes = 0;
        for (Cliente cliente : clientes) {
            if (cliente.getTipo() == 2) { // Verifica se é cliente mensalista
                usos += cliente.NumeroDeUsosMes(mes);
                nclientes++;
            }
        }
        // Verifica se há pelo menos um cliente mensalista antes de calcular a média
        if (nclientes != 0) {
            return usos / nclientes;
        } else {
            return 0; // Retorna 0 se não houver clientes mensalistas no mês
        }
    }

    public long mediaArrecadacaoHorista(int mes){
    long arrecadacao = 0;
    long nclientes = 0;
    for (Cliente cliente : clientes) {
        if (cliente.getTipo() == 1) { // Verifica se é cliente horista
            arrecadacao += cliente.arrecadadoNoMes(mes);
            nclientes++;
        }
    }
    // Verifica se há pelo menos um cliente horista antes de calcular a média
    if (nclientes != 0) {
        return arrecadacao / nclientes;
    } else {
        return 0; // Retorna 0 se não houver clientes horistas no mês
    }
}

    public double arrecadacaoNoMes(int mes) {
        double totalMes = 0.0;
        if (arrecadacaoPorPlaca != null) {
            for (Map.Entry<String, Double> entry : arrecadacaoPorPlaca.entrySet()) {
                String chave = entry.getKey();
                int mesEntrada;
                try {
                    mesEntrada = Integer.parseInt(chave.substring(4, 6));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    mesEntrada = -1; // Valor inválido para garantir que não seja adicionado ao total
                }
                if (mesEntrada == mes) {
                    totalMes += entry.getValue();
                }
            }
        }
        return totalMes;
    }


    public String top5Clientes(int mes) {
        Cliente[] topClientes = new Cliente[5];

        for (Cliente c : clientes) {
            if (c != null) {
                double valorDoCliente = c.arrecadadoNoMes(mes);

                for (int i = 0; i < 5; i++) {
                    if (topClientes[i] == null || valorDoCliente > topClientes[i].arrecadadoNoMes(mes)) {
                        for (int j = 4; j > i; j--) {
                            topClientes[j] = topClientes[j - 1];
                        }
                        topClientes[i] = c;
                        break;
                    }
                }
            }
        }

        // Agora topClientes contém os 5 principais clientes
        String[] nomesTopClientes = new String[5];
        for (int i = 0; i < 5; i++) {
            if (topClientes[i] != null) {
                nomesTopClientes[i] = topClientes[i].getNome();
            }
        }

        return Arrays.toString(nomesTopClientes);
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

     public void notificarAtualizacao(int mesAtual) {
        setChanged();
        notifyObservers(mesAtual);
    }

    

}
