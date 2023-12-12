import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (clientes != null) {
            List<Cliente> top5Clientes = clientes.stream()
                    .filter(cliente -> cliente.arrecadadoTotal() > 0)
                    .sorted(criarComparadorTop5Clientes())
                    .limit(5)
                    .collect(Collectors.toList());

            System.out.println("Top 5 clientes que mais geraram arrecadação:");
            for (int i = 0; i < top5Clientes.size(); i++) {
                System.out.println("Cliente " + (i + 1) + ": " + top5Clientes.get(i).getNome() + " - " + top5Clientes.get(i).arrecadadoTotal());
            }
        }
    }

    private Comparator<Cliente> criarComparadorTop5Clientes() {
        return Comparator.<Cliente, Double>comparing(cliente -> cliente.arrecadadoTotal())
                .thenComparing(Comparator.<Cliente, Double>comparing(cliente -> cliente.arrecadadoNoMes(mes))
                        .reversed())
                .reversed();
    }

    public void pararObservacao() {
        dadosObservados.removerObservador(this);
    }
}
