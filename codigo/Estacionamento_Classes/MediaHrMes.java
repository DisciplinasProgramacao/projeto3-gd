import java.util.Observable;
import java.util.Observer;

public class MediaHrMes implements Observer {

    private Estacionamento estacionamento;

    public MediaHrMes(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
        this.estacionamento.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Estacionamento && arg instanceof Integer) {
            int mesAtual = (int) arg;
            long arrecadacaoMediaHoristas = estacionamento.mediaArrecadacaoHorista(mesAtual);
            System.out.println("Arrecadação média dos clientes horistas no mês " + mesAtual + ": R$" + arrecadacaoMediaHoristas);
        }
    }
}
