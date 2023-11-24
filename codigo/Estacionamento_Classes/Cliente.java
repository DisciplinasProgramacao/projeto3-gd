import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * A classe Cliente representa um cliente no sistema, com informações como nome,
 * identificação e uma lista de veículos associados.
 */
public class Cliente {

    private String nome;
    private String id;
    private IUsuario IU;
    private int turno;
    private int tipo;

    /**
     * Um array de objetos Veiculo associados ao cliente.
     */
    private Veiculo[] veiculo;

    /**
     * Obtém o identificador do cliente.
     *
     * @return O identificador do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a lista de veículos associados ao cliente.
     *
     * @return Um array de objetos Veiculo.
     */
    public Veiculo[] getVeiculo() {
        return veiculo;
    }

    /**
     * Construtor para criar um objeto Cliente com nome e identificação.
     *
     * @param nome O nome do cliente.
     */
    public Cliente(String nome, int tipo, int turno) {
        this.turno=turno;
        this.tipo = tipo;
        this.nome = nome;
        this.id = gerarIDAleatorio();
        switch (tipo){
            case 1:
                this.IU = new IHora();
                break;
            case 2:
                this.IU=new IMes();
                break;
            case 3:
                this.IU=new ITurno(turno);
        }


    }

    public Cliente(String nome, String id, Veiculo[] veiculo, int tipo, int turno){
       this.nome = nome;
       this.id=id;
       this.veiculo = veiculo;
       this.turno = turno;
       this.tipo = tipo;
        switch (tipo){
            case 1:
                this.IU = new IHora();
                break;
            case 2:
                this.IU=new IMes();
                break;
            case 3:
                this.IU=new ITurno(turno);
        }

    }

    /**
     * Construtor para criar um objeto Cliente com valores padrão.
     */
    public Cliente() {
        this.nome = "Anônimo";
        this.id = "000000"; // Identificador neutro
    }

    public void setTurno(int turno){
        this.IU=new ITurno(turno);
    }

    public void setTipo(int tipo, int turno){
        this.turno = turno;
        this.tipo = tipo;
        switch (tipo){
            case 1:
                this.IU = new IHora();
                break;
            case 2:
                this.IU=new IMes();
                break;
            case 3:
                this.IU=new ITurno(turno);
        }
    }

    public void estacionar(String placa, Vaga vaga){
        for (int i = 0; i < veiculo.length; i++) {
            if (veiculo[i].getPlaca()==placa)IU.estacionar(vaga, veiculo[i]);
        }
    }

    /**
     * Adiciona um novo veículo à lista de veículos do cliente.
     *
     * @param novoVeiculo O veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo novoVeiculo) {
        if (veiculo == null) {
            veiculo = new Veiculo[1];
            veiculo[0] = novoVeiculo;
            veiculo[veiculo.length-1].salvarVeiculo(id);
        } else {
            Veiculo[] novov = new Veiculo[veiculo.length + 1];
            for (int i = 0; i < veiculo.length; i++) {
                novov[i] = veiculo[i];
            }
            novov[veiculo.length] = novoVeiculo;
            veiculo = novov;
            veiculo[veiculo.length-1].salvarVeiculo(id);
        }
    }

    /**
     * Verifica se o cliente possui um veículo com uma determinada placa.
     *
     * @param placa A placa do veículo a ser verificado.
     * @return O objeto Veiculo se encontrado, ou null se não encontrado.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculo) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Calcula o total de usos de todos os veículos associados ao cliente.
     *
     * @return O total de usos dos veículos.
     */
    public int totalDeUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculo) {
            if (veiculo != null) {
                totalUsos += veiculo.getTotalDeUsos();
            }
        }
        return totalUsos;
    }

    public int getTipo(){return tipo;
    }
    /**
     * Calcula a arrecadação de um veículo com uma determinada placa.
     *
     * @param placa A placa do veículo.
     * @return A arrecadação do veículo, ou 0.0 se não encontrado.
     */
    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.getTotalDeUsos();
        } else {
            return 0.0;
        }
    }


    public float NumeroDeUsosMes(int mes){
        float numeroDeUsos = 0;
        for (int i=0;i < veiculo.length;i++){
            numeroDeUsos+= veiculo[i].numeroDeUsosMes( mes);
        }
        return numeroDeUsos;
    }
    /**
     * Calcula o total arrecadado por todos os veículos associados ao cliente.
     *
     * @return O total arrecadado.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (Veiculo veiculo : veiculo) {
            if (veiculo != null) {
                totalArrecadado += veiculo.totalArrecadado();
            }
        }
        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação de todos os veículos associados ao cliente em um determinado mês.
     *
     * @param mes O mês para o qual a arrecadação será calculada.
     * @return A arrecadação total do mês.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;
        for (Veiculo veiculo : veiculo) {
            if (veiculo != null) {
                arrecadadoNoMes += veiculo.arrecadadoNoMes(mes);
            }
        }
        return arrecadadoNoMes;
    }

    public LinkedList<UsoDeVaga> relatorioTotalDecrescente(){
        LinkedList<UsoDeVaga> usos = null;
        for (Veiculo veiculo : veiculo) {
            if (veiculo != null) {
                usos.addAll(veiculo.getUsos());
            };
        }
        return usos;
    }


    /**
     * Obtém o histórico de todos os veículos associados ao cliente no intervalo de datas especificado.
     *
     * @param i A data de início do intervalo.
     * @param f A data de fim do intervalo.
     */
    public void Historico(LocalDate i, LocalDate f){
        for (int j = 0; j < veiculo.length; j++){
            if (veiculo[j] != null) {
                veiculo[j].historico(i, f);
            }
        }
    }

    public void salvarCliente(String Id) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Clientes.txt", true))) {
            writer.printf(this.nome + ";" + this.id + ";" + Id + ";" + this.tipo + ";"+this.turno +"\n");
            System.out.println("Cliente salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Cliente[] carregarClientes(String nomeEstacionamento) {
            LinkedList<Cliente> clientes = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Clientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String nome = parts[0];
                    String id = parts[1];
                    String Estacionamento = parts[2];
                    int tipo = Integer.parseInt(parts[3]);
                    int turno = Integer.parseInt(parts[4]);
                    if(Estacionamento == nomeEstacionamento ){
                        Veiculo[] vec = Veiculo.carregarVeiculos(id);
                        Cliente cli = new Cliente(nome, id, vec, tipo, turno);
                        clientes.add(cli);}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cliente[] cli = new Cliente[clientes.size()];
        clientes.toArray(cli);
        return cli;
    }
    public static String gerarIDAleatorio() {
        Random random = new Random();
        String id;
            int num = 100000 + random.nextInt(900000);
            id = String.format("%06d", num);
        return id;
    }
}
