
/** 
 * MIT License
 *
 * Copyright(c) 2023 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.LinkedList;

/**
 * Classe vaga para sistema de estacionamento. Apenas representa uma vaga e sua disponibilidade.
 * @author @joaocaram
 */
public class Vaga {

	//#region atributos
	private String id;
	private boolean disponivel;
	private static final String filas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//#endregion

	/**
	 * Constrói uma vaga de acordo com a regra de identificador. A fila 1 vira A, 2 vira B e assim por diante.
	 * O número é sempre usado com 2 dígitos. Por exemplo, fila 10 e número 8 vira J08.
	 * @param fila A fila da vaga. Deve ser um inteiro entre 0 e 25.
	 * @param numero O número na fila. Deve ser um número entre 1 e 99.
	 */
	public Vaga(int fila, int numero) {
		fila = (fila>0&&fila<26)?fila:1;
		numero = (numero>0&&numero<100)?numero:1;
		this.id = filas.charAt(fila-1)+String.format("%02d", numero);	
		this.disponivel = true;
	}

	public Vaga(String id, boolean disponivel){
		this.id = id;this.disponivel=disponivel;
	}

	/**
	 * Marca a vaga como indisponível por estar estacionada. Retorna TRUE/FALSE conforme seja
	 * possível efetuar a operação (ex: tentar estacionar em uma vaga ocupada retorna FALSE)
	 * @return TRUE/FALSE conforme tenha sido possível estacionar.
	 */
	public boolean estacionar() {
		boolean resposta = false;
		if(disponivel){
			resposta = true;
			disponivel = false;
		}
		return resposta;
	}

	/**
	 * Libera a vaga na saída de um carro. Retorna TRUE/FALSE conforme seja
	 * possível efetuar a operação (ex: tentar liberar uma vaga não ocupada retorna FALSE)
	 * @return TRUE/FALSE conforme a operação tenha ocorrido.
	 */
	public boolean sair() {
		boolean resposta = false;
		if(!disponivel){
			resposta = true;
			disponivel = true;
		}
		return resposta;
	}

	/**
	 * <i>Getter</i> para o estado da vaga (true se está disponível para uso)
	 * @return TRUE se a vaga está disponível para uso, FALSE caso contrário.
	 */
	public boolean disponivel() {
		return this.disponivel;
	}

	/**
	 * Igualdade de duas vagas: se ela têm o mesmo identificador. Caso o objeto não seja da 
	 * classe Vaga, retorna sempre falso.
	 * @param obj A outra vaga para comparação. Se obj não for uma Vaga, retorna falso pelo tratamento de exceção.
	 */
	@Override 
	public boolean equals(Object obj){
		if(obj.equals(this))
			return true;
		else{
			try{
				Vaga outra = (Vaga)obj;
				return this.id.equals(outra.id);
			}
			catch (ClassCastException ce){
				return false;	
			}
		}
	}

	/**
	 * Representação em String: identificador da vaga e seu estado (disponível ou ocupada)
	 * @return String formatada:
	 * <pre>
	 * Vaga &#60;id&#62 [disponível|ocupada].
	 * </pre>
	 */
	@Override
	public String toString(){
		String disp = this.disponivel?"disponível.":"ocupada.";
		return "Vaga "+this.id+ " "+disp;
	}

	public Boolean getDisponivel(){return disponivel;}

	public String getId() {
		return id;
	}

	public void salvarVagas(String nomeEstac) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Vagas.txt", true))) {
			writer.printf(this.id + ";" + this.disponivel +";" + nomeEstac +"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static Vaga[] carregarVagas(String nomeEstacionamento) {
		LinkedList<Vaga> vag = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("Vagas.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length == 3) {
					String id = parts[0];
					boolean disponivel = Boolean.parseBoolean(parts[1]);
					String estac = parts[2];
					if(estac == nomeEstacionamento){
						Vaga va = new Vaga(id, disponivel);vag.add(va);
					}
					}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vaga[] vagg = new Vaga[vag.size()];
		vag.toArray(vagg);
		return vagg;
	}

}
