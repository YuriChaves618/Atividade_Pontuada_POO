public class CameraDigital extends Equipamentos implements Carregamento{
    private static int prazoEmprestimo = 3;

    private static final int taxa = 3;
    private static final double cargaMinima = 60;

    private double carga;
    private boolean cartaoMemoria;


    public CameraDigital(String codigoPatrimonio, String nome, String marca, String modelo,
                         double carga, boolean cartaMemoria) {
        super(codigoPatrimonio, nome, marca, modelo);
        validarPercentual(carga);
        this.carga = carga;
        this.cartaoMemoria = cartaMemoria;
    }

    public boolean statusCartaoMemoria() {
        return cartaoMemoria;
    }

    public void setCartaoMemoria(boolean cartaoMemoria) {
        this.cartaoMemoria = cartaoMemoria;
    }

    @Override
    public double consultarCarga() {
        return carga;
    }

    @Override
    public void atualizarCargaPorTempo(int minutos) {
        if (minutos <= 0) {
            throw new IllegalArgumentException("O tempo de carregamento deve ser positivo!");
        } else {
            int intervalos = minutos / 2;
            double novaCarga = carga + intervalos * taxa;
            if (novaCarga > 100) {
                carga = 100;
            } else {
                carga = novaCarga;
            }
        }
    }

    @Override
    public void atualizarCargaPorPercentual(double percentual) {
        validarPercentual(percentual);
        carga = percentual;
    }

    @Override
    public int calcularTempoMinimoCarregamento() {
        double falta = 100 - carga;
        if (falta <= 0) {
            return 0;
        } else {
            int intervalos = (int) Math.ceil(falta / taxa);
            return intervalos * 2;
        }
    }

    @Override
    public boolean verificarCondicoesEmprestimoManutencao() {
        return carga >= cargaMinima && cartaoMemoria;
    }

    @Override
    public int consultarPrazoEmprestimo() {
        return prazoEmprestimo;
    }

    public static void alterarPrazoEmprestimo(int novoPrazo) {
        if (novoPrazo <= 0) {
            throw new IllegalArgumentException("O prazo de empréstimo deve ser um número inteiro positivo!");
        } else {
            prazoEmprestimo = novoPrazo;
        }
    }

    private void validarPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual de carga deve estar entre 0% e 100%!");
        }
    }
}

