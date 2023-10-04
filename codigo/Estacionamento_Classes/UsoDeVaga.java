import java.time.LocalDateTime;
import java.time.Duration;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.valorPago = 0.0;

	
	}

	public int getMes(){
		return entrada.getMonthValue();
	}


	public double sair() {
		this.saida = LocalDateTime.now();
		Duration tempoEsta = Duration.between(entrada, saida);
		long minutos = tempoEsta.toMinutes();
		double aPagar = minutos * VALOR_FRACAO * FRACAO_USO;

		if(aPagar > VALOR_MAXIMO){
			this.valorPago = VALOR_MAXIMO;
		}
		else{
			this.valorPago = aPagar;
		}


		vaga.disponivel();
		return valorPago;

	}

	public double valorPago() {
		return valorPago;
	}

}
