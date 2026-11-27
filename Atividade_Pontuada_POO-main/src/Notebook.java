public class Notebook extends Equipamentos implements Carregamento {

    private static int prazoEmprestimo = 7;

    private static final int taxa = 1;
    private static final double cargaMinima = 40;
    private double carga;

    public Notebook(String codigoPatrimonio, String nome, String marca, String modelo, double cargaInicial) {
        super(codigoPatrimonio, nome, marca, modelo);
        if (cargaInicial < 0 || cargaInicial > 100) {
            throw new IllegalArgumentException("O percentual de carga deve estar entre 0% e 100%!");
        }
        carga = cargaInicial;
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
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual de carga deve estar entre 0% e 100%!");
        }
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
        if (carga >= cargaMinima) {
            return true;
        } else {
            return false;
        }
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
   
    
}
