import java.time.LocalDate;
import java.util.ArrayList;

public class CentroDeRecursos {

    private static final int retiradaEmprestimo = 1;
    private static final int retiradaManuntecao = 2;

    private final ArrayList<Equipamentos> equipamentos;
    private final ArrayList<Usuario> usuarios;
    private final ArrayList<BateriaPortatil> bateriasPortatils;
    private final ArrayList<Emprestimo> emprestimos;

    public CentroDeRecursos() {
        equipamentos = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        bateriasPortatils = new ArrayList<>();
    }


    public Equipamentos localizarEquipamentos(String codigo) {
        for (Equipamentos i : equipamentos) {
            if (i.getCodigoPatrimonio().equals(codigo)) {
                return i;
            }
        }
        return null;
    }

    public Usuario localizarUsuarios(Integer matricula) {
        for (Usuario i : usuarios) {
            if (matricula != null && i.getMatricula() == matricula) {
                return i;
            }
        }
        return null;
    }

    public BateriaPortatil localizarBateriasPortateis(String codigo) {
        for (BateriaPortatil i : bateriasPortatils) {
            if (i.getCodigo().equals(codigo)) {
                return i;
            }
        }
        return null;
    }


    public Carregamento localizarItemCarregavel(String codigo) {
        Equipamentos equipamento = localizarEquipamentos(codigo);
        if (equipamento != null) {
            if (equipamento instanceof Carregamento) {
                return (Carregamento) equipamento;
            }
            throw new IllegalArgumentException("Projetores não possuem operações de bateria!");
        }

        BateriaPortatil bateria = localizarBateriasPortateis(codigo);
        if (bateria != null) {
            return bateria;
        }
        throw new IllegalArgumentException("Código inexistente: " + codigo);
    }

    public void cadastrarEquipamento(Equipamentos equipamento) {
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento é obrigatório!");
        }
        String codigo = equipamento.getCodigoPatrimonio();
        if (localizarEquipamentos(codigo) != null || localizarBateriasPortateis(codigo) != null) {
            throw new IllegalArgumentException("Já existe um item com o código " + codigo);
        }
        equipamentos.add(equipamento);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário é obrigatório!");
        }
        if (localizarUsuarios(usuario.getMatricula()) != null) {
            throw new IllegalArgumentException("Já existe um usuário com a matrícula " + usuario.getMatricula());
        }
        usuarios.add(usuario);
    }

    public void cadastrarBateria(BateriaPortatil bateria) {
        if (bateria == null) {
            throw new IllegalArgumentException("Bateria é obrigatória!");
        }
        String codigo = bateria.getCodigo();
        if (localizarBateriasPortateis(codigo) != null) {
            throw new IllegalArgumentException("Já existe um item com o código " + codigo);
        }
        bateriasPortatils.add(bateria);
    }

    public void retiradaEquipamentos(int tipoRetirada, Integer matricula, String codigo, LocalDate dataRetirada) {
        Equipamentos equipamento = localizarEquipamentos(codigo);
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento não localizado: " + codigo);
        }

        if (tipoRetirada == retiradaManuntecao) {
            equipamento.iniciarManutencao();
        } else if (tipoRetirada == retiradaEmprestimo) {
            retirarPorEmprestimo(matricula, equipamento, dataRetirada);
        } else {
            throw new IllegalArgumentException("Tipo de retirada inválido! Use 1 (empréstimo) ou 2 (manutenção).");
        }
    }

    private void retirarPorEmprestimo(Integer matricula, Equipamentos equipamento, LocalDate dataRetirada) {
        Usuario usuario = localizarUsuarios(matricula);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não localizado: " + matricula);
        }
        if (!usuario.estaAtivo()) {
            throw new IllegalStateException("Cadastro do usuário está inativo!");
        }
        if (contarEmprestimosEmAberto(usuario) >= usuario.getLimiteEmprestimos()) {
            throw new IllegalStateException("Limite de empréstimos simultâneos atingido!");
        }

        Emprestimo emprestimo = new Emprestimo(usuario, equipamento, dataRetirada);
        equipamento.retirarPorEmprestimo();
        emprestimos.add(emprestimo);
    }

    public int devolverEquipamento(String codigo, LocalDate dataEfetiva, boolean necessitaReparo,
                                   Double percentualCarga) {
        Equipamentos equipamento = localizarEquipamentos(codigo);
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento não localizado: " + codigo);
        }

        Emprestimo emprestimo = localizarEmprestimoEmAbertoDoEquipamento(equipamento);
        if (emprestimo == null) {
            throw new IllegalStateException("O equipamento não possui empréstimo em aberto!");
        }

        validarPercentualDevolucao(equipamento, percentualCarga);

        emprestimo.registrarDevolucao(dataEfetiva);

        if (equipamento instanceof Carregamento) {
            ((Carregamento) equipamento).atualizarCargaPorPercentual(percentualCarga);
        }
        equipamento.registrarDevolucao(necessitaReparo);

        return emprestimo.calcularDiasAtraso();
    }

    private void validarPercentualDevolucao(Equipamentos equipamento, Double percentual) {
        if (equipamento instanceof Carregamento) {
            if (percentual == null || percentual < 0 || percentual > 100) {
                throw new IllegalArgumentException("Informe um percentual de carga entre 0% e 100%!");
            }
        } else if (percentual != null) {
            throw new IllegalArgumentException("Projetores não possuem carga: o percentual deve ser nulo!");
        }
    }


    public void encerrarManutencao(String codigo) {
        Equipamentos equipamento = localizarEquipamentos(codigo);
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento não localizado: " + codigo);
        }
        equipamento.encerrarManutencao();
    }


    public int consultarEmprestimosEmAberto(Integer matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("A matrícula não pode ser nula!");
        }
        Usuario usuario = localizarUsuarios(matricula);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não localizado: " + matricula);
        }
        return contarEmprestimosEmAberto(usuario);
    }

    public Emprestimo buscarEmprestimoEmAberto(Integer matricula, String codigo) {
        if (matricula == null || codigo == null) {
            throw new IllegalArgumentException("Matrícula e código do equipamento são obrigatórios!");
        }
        Usuario usuario = localizarUsuarios(matricula);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não localizado: " + matricula);
        }
        Equipamentos equipamento = localizarEquipamentos(codigo);
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento não localizado: " + codigo);
        }

        for (Emprestimo e : emprestimos) {
            if (e.estaEmAberto() && e.getUsuario() == usuario && e.getEquipamento() == equipamento) {
                return e;
            }
        }
        throw new IllegalArgumentException("Não há empréstimo em aberto para essa matrícula e equipamento!");
    }


    private int contarEmprestimosEmAberto(Usuario usuario) {
        int total = 0;
        for (Emprestimo e : emprestimos) {
            if (e.estaEmAberto() && e.getUsuario() == usuario) {
                total++;
            }
        }
        return total;
    }

    private Emprestimo localizarEmprestimoEmAbertoDoEquipamento(Equipamentos equipamento) {
        for (Emprestimo e : emprestimos) {
            if (e.estaEmAberto() && e.getEquipamento() == equipamento) {
                return e;
            }
        }
        return null;
    }
}

