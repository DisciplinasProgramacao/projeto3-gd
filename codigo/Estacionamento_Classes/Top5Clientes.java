public class Top5Clientes implements Observer {

    private List<Cliente> clientes;
    private int mes;
    private DadosObservados dadosObservados;

    public Top5Clientes(List<Cliente> clientes, int mes, DadosObservados dadosObservados) {
        this.clientes = clientes;
        this.mes = mes;
        this.dadosObservados = dadosObservados;
        dadosObservados.adicionarObservador(this);  
    }

    @Override
    public void update() {
        calcularTop5Clientes();
    }

    private void calcularTop5Clientes() {
        List<Cliente> top5Clientes = clientes.stream()
                .filter(cliente -> cliente.arrecadadoNoMes(mes) > 0)
                .sorted(Comparator.comparing(Cliente::arrecadadoNoMes).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 clientes que mais geraram arrecadação no mês " + mes + ":");
        for (int i = 0; i < top5Clientes.size(); i++) {
            System.out.println("Cliente " + (i + 1) + ": " + top5Clientes.get(i).getNome() + " - " + top5Clientes.get(i).arrecadadoNoMes(mes));
        }
    }

    public void pararObservacao() {
        dadosObservados.removerObservador(this);
    }
}
