import java.time.LocalDate;

/**
 * A classe Cliente representa um cliente no sistema, com informações como nome,
 * identificação e uma lista de veículos associados.
 */
public class Cliente {

    private String nome;
    private String id;

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
     * @param id   O identificador único do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculo = new Veiculo[10]; 
    }

    /**
     * Construtor para criar um objeto Cliente com valores padrão.
     */
    public Cliente() {
        this.nome = "Anônimo";
        this.id = "000000"; // Identificador neutro
        this.veiculo = new Veiculo[10]; 
    }

    /**
     * Adiciona um novo veículo à lista de veículos do cliente.
     *
     * @param novoVeiculo O veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo novoVeiculo) {
        for (int i = 0; i < veiculo.length; i++) {
            if (veiculo[i] == null) {
                veiculo[i] = novoVeiculo;
                return;
            }
        }
        System.out.println("Não há espaço disponível para adicionar um novo veículo.");
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
}
