import java.util.Observable;
import java.util.Observer;

public class ValorTotalArrecadadoObserver implements Observer {

    private double valorTotalArrecadado;

    public ValorTotalArrecadadoObserver() {
        this.valorTotalArrecadado = 0.0;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Estacionamento && arg instanceof Double) {
            Estacionamento estacionamento = (Estacionamento) o;
            double novoValor = (Double) arg;
            
            this.valorTotalArrecadado = novoValor;
           
            System.out.println("Novo valor total arrecadado: " + novoValor);
        }
    }

    public double getValorTotalArrecadado() {
        return valorTotalArrecadado;
    }
}
