public class MeidaHrMes implements Observer {
    private Estacionamento estacionamento;
    private int mes;

    public MeidaHrMes(Estacionamento estacionamento, int mes) {
        this.estacionamento = estacionamento;
        this.mes = mes;
        estacionamento.addObserver(this);
    }

    @Override
    public void update(String message) {
        calcularArrecadacaoMediaClientesHoristas();
    }

    private void calcularArrecadacaoMediaClientesHoristas() {
        long mediaArrecadacaoHorista = estacionamento.mediaArrecadacaoHorista(mes);
        System.out.println("Arrecadação média dos clientes horistas no mês " + mes + ": " + mediaArrecadacaoHorista);
    }

    public void pararObservacao() {
        estacionamento.removeObserver(this);
    }
}
