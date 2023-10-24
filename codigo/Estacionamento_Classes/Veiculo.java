import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;

	public Veiculo(String placa) {
		this.placa=placa;
	}

	public void estacionar(Vaga vaga) {
        	if (usos == null) {
            	usos = new UsoDeVaga[1];
            	usos[0] = new UsoDeVaga(vaga); 
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
			valor += usos[i].getValorPago();
		}
		return valor;
	}

	public double arrecadadoNoMes(int mes) {
		double valor = 0;
		for (int i = 0; i < usos.length;i++){
			if(mes == usos[i].getMes())valor += usos[i].getValorPago();
		}
		return valor;
	}

	public String historico(LocalDate i, LocalDate f) {
		if(i==null && f== null){
		return "Veiculo{" +
				"placa='" + placa + '\'' +
				", usos=" + Arrays.toString(usos) +
				'}';}
		else{
			return "Veiculo{" +
					"placa='" + placa + '\'' +
					", usos=" + Arrays.toString(new LinkedList[]{getUsosMes(i, f)}) +
					'}';}
	}

	public LinkedList<UsoDeVaga> getUsosMes(LocalDate i, LocalDate f){
		LinkedList<UsoDeVaga> u = new LinkedList<>();
		for (int j = 0; j < usos.length;j++){
			if (usos[j].getEntrada().isAfter(i)||usos[j].getEntrada().isEqual(i)&&usos[j].getEntrada().isBefore(f)||usos[j].getEntrada().isEqual(f))
				u.add(usos[j]);
		}
		return u;
	}


	public int getTotalDeUsos() {
		return usos.length;
	}
	public String getPlaca(){return placa;};
}

