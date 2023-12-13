public class IHora implements IUsuario {
    private String nome = "HORISTA";
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga, null, 1);
    }

    public String getNome(){return nome;}
}
