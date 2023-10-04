
public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;

	public Veiculo(String placa) {
		this.placa=placa;
	}

	public void estacionar(Vaga vaga) {
		if (usos == null) {
			usos = new UsoDeVaga[1];
		} else {
			UsoDeVaga[] newUsos = new UsoDeVaga[usos.length + 1];
			for (int i = 0; i < usos.length; i++) {
				newUsos[i] = usos[i];
			}
			newUsos[usos.length] = new UsoDeVaga(vaga);
			usos = newUsos;
		}
	}


	public double sair() {
		return usos[usos.length - 1].sair();
	}

	public double totalArrecadado() {
		double valor = 0;
		for (int i = 0; i < usos.length;i++){
			valor += usos[i].valorPago();
		}
		return valor;
	}

	public double arrecadadoNoMes(int mes) {
		double valor = 0;
		for (int i = 0; i < usos.length;i++){
			if(mes == usos[i].getMes())valor += usos[i].valorPago();
		}
		return valor;
	}

	public int totalDeUsos() {
		return usos.length;
	}

}
