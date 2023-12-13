public class IMes implements IUsuario{
    private String nome = "MENSALISTA";
    @Override
    public void estacionar(Vaga vaga, Veiculo veiculo) {
        veiculo.estacionar(vaga, null, 2);
    }

    @Override
    public String getNome() {
        return nome;
    }
}
