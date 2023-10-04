public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculo;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Veiculo[] getVeiculo() {
        return veiculo;
    }

    
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculo = new Veiculo[10]; 
    }

    public Cliente() {
        this.nome = "Anônimo";
        this.id = "000000"; // Identificador neutro
        this.veiculo = new Veiculo[10]; // 
    }

    public void addVeiculo(Veiculo novoVeiculo) {
		
		for (int i = 0; i < veiculo.length; i++) {
			if (veiculo[i] == null) {
				veiculo[i] = novoVeiculo;
				return;
			}
		}
		System.out.println("Não há espaço disponível para adicionar um novo veículo.");
	}

    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculo) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public int totalDeUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculo) {
            totalUsos += veiculo.getTotalUsos();
        }
        return totalUsos;
    }

    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.getValorArrecadado();
        } else {
            return 0.0;
        }
    }

    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (Veiculo veiculo : veiculo) {
            totalArrecadado += veiculo.getValorArrecadado();
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;
        for (Veiculo veiculo : veiculo) {
            arrecadadoNoMes += veiculo.getValorArrecadadoNoMes(mes);
        }
        return arrecadadoNoMes;
    }
}
