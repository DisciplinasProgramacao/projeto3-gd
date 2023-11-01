public class EstacionamentoTeste {

    public static void main(String[] args) {
        
        Estacionamento estacionamento1 = new Estacionamento("Estacionamento A", 3, 4);

        
        Cliente cliente1 = new Cliente("123", "John Doe", null);
        estacionamento1.addCliente(cliente1);

        
        Veiculo veiculo1 = new Veiculo("ABC123", "Car");
        estacionamento1.addVeiculo(veiculo1, "123");

        
        estacionamento1.estacionar("ABC123");

        
        double valorArrecadado = estacionamento1.sair("ABC123");
        System.out.println("Valor arrecadado: " + valorArrecadado);

       
        double totalArrecadado = estacionamento1.totalArrecadado();
        System.out.println("Total arrecadado: " + totalArrecadado);

        
        double arrecadacaoNoMes = estacionamento1.arrecadacaoNoMes(10);
        System.out.println("Arrecadacao no mes: " + arrecadacaoNoMes);

        
        double valorMedioPorUso = estacionamento1.valorMedioPorUso();
        System.out.println("Valor medio por uso: " + valorMedioPorUso);

        
        String top5Clientes = estacionamento1.top5Clientes(10);
        System.out.println(top5Clientes);

        
    }
}
