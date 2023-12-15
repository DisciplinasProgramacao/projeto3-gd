import java.util.Observable;
import java.util.Observer;

public class ValorTotalArrecadadoObserver implements Observer {

    private double valorTotalArrecadado;
    private Estacionamento estacionamento;

    public ValorTotalArrecadadoObserver(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
        this.valorTotalArrecadado = 0.0;
        estacionamento.addObserver(this);
        calcularValorTotalArrecadado();
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Estacionamento) {
            Estacionamento est = (Estacionamento) observable;
            double novoValor = est.calcularValorTotal();
            if (this.valorTotalArrecadado < novoValor) {
                System.out.println("Valor total arrecadado aumentou de " + this.valorTotalArrecadado + " para " + novoValor);
                this.valorTotalArrecadado = novoValor;
            }
        }
    }

    public double getValorTotalArrecadado() {
        return valorTotalArrecadado;
    }

    public void pararObservacao() {
        estacionamento.deleteObserver(this);
    }
}
