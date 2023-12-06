import java.util.Observable;
import java.util.Observer;

public class MediaUtilizacaoEstacionamento implements Observer {

    private Estacionamento estacionamento;

    public MediaUtilizacaoEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
        this.estacionamento.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Estacionamento && arg instanceof Integer) {
            int mesAtual = (int) arg;
            double valorMedioUtilizacao = estacionamento.valorMedioPorUso();
            System.out.println("Valor médio pela utilização do estacionamento no mês " + mesAtual + ": R$" + valorMedioUtilizacao);
        }
    }
}
