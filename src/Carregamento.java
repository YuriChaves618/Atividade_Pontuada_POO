public interface Carregamento {

    double consultarCarga();

    void atualizarCargaPorTempo(int minutos);

    void atualizarCargaPorPercentual(double percentual);

    int calcularTempoMinimoCarregamento();
}