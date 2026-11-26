public class Notebook extends Equipamentos implements Carregamento {

    private static int prazoEmprestimo = 7;

    private static final int taxa = 1;
    private static final double cargaMinima = 40;
    private double carga;

    public Notebook(String codigoPatrimonio, String nome, String marca, String modelo, double cargaInicial) {
        super(codigoPatrimonio, nome, marca, modelo);
        validarPercentual(cargaInicial);
        carga = cargaInicial;
    }

    public double consultarCarga() {
        return carga;
    }

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

    public void atualizarCargaPorPercentual(double percentual) {
        validarPercentual(percentual);
        carga = percentual;
    }

    public int calcularTempoMinimoCarregamento() {
        double falta = 100 - carga;
        if (falta <= 0) {
            return 0;
        } else {
            int intervalos = (int) (falta / taxa);
            if (intervalos * taxa < falta) {
                return (intervalos + 1) * 2;
            } else {
                return intervalos * 2;
            }
        }
    }

    public boolean verificarCondicoesEmprestimoManutencao() {
        if (carga >= cargaMinima) {
            return true;
        } else {
            return false;
        }
    }

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