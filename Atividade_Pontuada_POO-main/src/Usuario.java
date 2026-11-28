import java.time.LocalDate;

public  abstract class Usuario {
   private final String nome;
   private final int matricula;
private boolean ativo;
   private LocalDate dataUltimaAtualizacao;
    
    public Usuario(String nome, int matricula) {
        if (nome == null || nome.equals("")) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (matricula <= 0) {
            throw new IllegalArgumentException("A matrícula deve ser um número positivo.");
        }
       
        this.nome = nome;
        this.matricula = matricula;
        this.ativo = true;
        this.dataUltimaAtualizacao = LocalDate.now();
    }   
    
   public abstract int getLimiteEmprestimos();

   public void alterarStatus(){
   ativo = !ativo;
   dataUltimaAtualizacao = LocalDate.now();
        
    }
   
   public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean estaAtivo() {
        if(ativo){
            return true;
        }else{
            return false;
        }
        
    }
       public String getSituacao() {
        if (ativo) {
            return "Ativa";
        } else {
            return "Inativa";
        }
    }

    public LocalDate getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }
    public void exibirDados() {
   System.out.println("Nome: " + getNome());
        System.out.println("Matricula: " + getMatricula());
        System.out.println("Situacao: " + getSituacao());
        System.out.println("Ultima atualizacao: " + getDataUltimaAtualizacao());
        System.out.println("Limite de emprestimos: " + getLimiteEmprestimos());
}
}
