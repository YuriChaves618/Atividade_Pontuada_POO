public class BateriaPortatil implements Carregamento{
    private final String codigo;
    private double cargaAtual;

    public BateriaPortatil(String codigo, double cargaAtual){
        if(codigo == null  || codigo.isBlank()){
            throw new IllegalArgumentException("Informações vazias não são permitidas!");
        }else{
            this.codigo = codigo.trim();
        }
        if(cargaAtual >= 0 && cargaAtual <= 100){
            this.cargaAtual = cargaAtual;
        }else{
            throw new IllegalArgumentException("Informe valores válidos para a carga inicial do equipamento!");
        }
    }

    public String getCodigo(){
        return codigo;
    }

    @Override
    public int calcularTempoMinimoCarregamento(){
        double percentualFaltante = 0;
        int tempoCarregamento;

        if(cargaAtual >= 100){
            return 0;
        }else{
            percentualFaltante = 100 - cargaAtual;
            tempoCarregamento = (int)Math.ceil(percentualFaltante / 5) * 2;
            return tempoCarregamento;
        }
    }

    @Override
    public void atualizarCargaPorTempo(int minutos){
        int carga = 0;
        double resultadoCargaAtual;
        if(minutos <= 0){
            throw new IllegalArgumentException("Informe minutos válidos!");
        }
        else{
            for(int i = 1; i <= minutos; i++){
                if(i % 2 == 0){
                    carga += 5;
                }
            }
            resultadoCargaAtual = cargaAtual + carga;
            if(resultadoCargaAtual > 100){
                cargaAtual = 100;
            }else{
                cargaAtual += carga;
            }
        }
    }

    @Override
    public void atualizarCargaPorPercentual(double percentual){
        if (percentual < 0 || percentual > 100 ) {
            throw new IllegalArgumentException("Informe um percentual entre 0% e 100%!");
        }

        if (percentual > cargaAtual) {
            throw new IllegalArgumentException("O percentual informado não pode ser maior que a carga atual!");
        }

        cargaAtual = percentual;
        int tempoNecessario = calcularTempoMinimoCarregamento();

        if (tempoNecessario > 0) {
            atualizarCargaPorTempo(tempoNecessario);
        }

    }

    @Override
    public double consultarCarga() {
        return cargaAtual;
    }
}