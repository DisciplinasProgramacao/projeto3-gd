package observer;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Um observador que calcula o ranking dos 5 clientes que mais geraram arrecadação em um determinado mês.
 */
public class Top5Clientes implements Observer {

    /**
     * A lista de clientes que serão usados para calcular o ranking.
     */
    private List<Cliente> clientes;

    /**
     * O mês para o qual o ranking será calculado.
     */
    private int mes;

    /**
     * Construtor.
     *
     * @param clientes A lista de clientes que serão usados para calcular o ranking.
     * @param mes O mês para o qual o ranking será calculado.
     */
    public Top5Clientes(List<Cliente> clientes, int mes) {
        this.clientes = clientes;
        this.mes = mes;
    }

    /**
     * Notifica o observador de que uma mudança ocorreu nos dados.
     */
    @Override
    public void update() {
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
}
