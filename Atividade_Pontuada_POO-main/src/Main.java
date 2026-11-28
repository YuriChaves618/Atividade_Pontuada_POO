import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        CentroDeRecursos centro = new CentroDeRecursos();

        int opcao = -1,  tipo = 0, matricula = 0;
        double carga = 0;
        String nome = "", codigo = "", marca = "", modelo = "";
        boolean cartao = false;
        LocalDate data = null;

        do {
            exibirMenu();
            try {
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite um número inteiro válido!");
                scan.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {

                case 1:

                        try {
                            System.out.println("\n--- Tipos de Usuário ---");
                            System.out.println("1 - Estudante");
                            System.out.println("2 - Professor");
                            System.out.print("Selecione o tipo de usuário desejado: ");
                            tipo = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: digite um número inteiro válido!");
                            scan.nextLine();
                            continue;
                        }

                         System.out.print("Informe o nome do usuário que deseja cadastrar: ");
                         nome = scan.nextLine();

                        try {
                            System.out.print("Informe a matrícula do usuário que deseja cadastrar: ");
                            matricula = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: digite um número inteiro válido!");
                            scan.nextLine();
                            continue;
                        }

                    try {
                        if (tipo == 1) {
                            centro.cadastrarUsuario(new Estudante(nome, matricula));
                        } else if (tipo == 2) {
                            centro.cadastrarUsuario(new Professor(nome, matricula));
                        } else {
                            throw new IllegalArgumentException("Tipo de usuário inválido!");
                        }
                        System.out.println("Usuário cadastrado com sucesso!");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:

                        try {
                            System.out.println("\n---- Tipos de Equipamentos ----");
                            System.out.println("1 - Notebook");
                            System.out.println("2 - Projetor");
                            System.out.println("3 - Câmera digital");
                            System.out.print("Selecione o tipo de equipamento desejado: ");
                            tipo = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: digite um número inteiro válido!");
                            scan.nextLine();
                            continue;
                        }


                    if (tipo < 1 || tipo > 3) {
                        System.out.println("Erro: tipo de equipamento inválido!");
                        break;
                    }

                    System.out.print("Informe o código de patrimônio: ");
                    codigo = scan.nextLine();
                    System.out.print("Informe o nome do equipamento: ");
                    nome = scan.nextLine();
                    System.out.print("Informe a marca do equipamento: ");
                    marca = scan.nextLine();
                    System.out.print("Informe o modelo do equipamento: ");
                    modelo = scan.nextLine();

                    if (tipo == 1 || tipo == 3) {
                            try {
                                System.out.print("Informe a Carga inicial (0 a 100): ");
                                carga = scan.nextDouble();scan.nextLine();

                            } catch (InputMismatchException e) {
                                System.out.println("Erro: digite um número válido!");
                                scan.nextLine();
                               continue;
                            }
                    }

                    if (tipo == 3) {
                        System.out.print("O Cartão de memória está instalado? (s/n): ");
                        cartao = scan.nextLine().equalsIgnoreCase("s");
                    }

                    try {
                        if (tipo == 1) {
                            centro.cadastrarEquipamento(new Notebook(codigo, nome, marca, modelo, carga));
                        } else if (tipo == 2) {
                            centro.cadastrarEquipamento(new Projetor(codigo, nome, marca, modelo));
                        } else {
                            centro.cadastrarEquipamento(new CameraDigital(codigo, nome, marca, modelo, carga, cartao));
                        }
                        System.out.println("Equipamento cadastrado com sucesso!");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("\nInforme o código da bateria: ");
                    codigo = scan.nextLine();


                        try {
                            System.out.print("Informe a carga inicial (0 a 100): ");
                            carga = scan.nextDouble();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: digite um número válido!");
                            scan.nextLine();
                        }


                    try {
                        centro.cadastrarBateria(new BateriaPortatil(codigo, carga));
                        System.out.println("Bateria cadastrada com sucesso!");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:

                        try {
                            System.out.print("\n Informe a matrícula do usuário: ");
                            matricula = scan.nextInt();
                            scan.nextLine();

                        } catch (InputMismatchException e) {
                            System.out.println("Erro: digite um número inteiro válido!");
                            scan.nextLine();
                        }


                    System.out.print("Código do equipamento: ");
                    codigo = scan.nextLine();

                        try {
                            System.out.print("Informe a data da retirada, siga esse modelo: (aaaa-mm-dd): ");
                            data = LocalDate.parse(scan.nextLine());

                        } catch (DateTimeParseException e) {
                            System.out.println("Erro: data inválida! Use o formato aaaa-mm-dd.");

                        }


                    try {
                        centro.retiradaEquipamentos(1, matricula, codigo, data);
                        System.out.println("Empréstimo registrado com sucesso!");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println();
        System.out.println("\n========== CENTRO DE RECURSOS ==========");
        System.out.println(" 1  - Cadastrar usuário");
        System.out.println(" 2  - Cadastrar equipamento");
        System.out.println(" 3  - Cadastrar bateria portátil");
        System.out.println(" 4  - Registrar empréstimo");
        System.out.println(" 5  - Registrar devolução");
        System.out.println(" 6  - Enviar equipamento para manutenção");
        System.out.println(" 7  - Encerrar manutenção");
        System.out.println(" 8  - Consultar empréstimos");
        System.out.println(" 9  - Operações de bateria");
        System.out.println(" 10 - Atualizar departamento de professor");
        System.out.println(" 11 - Ativar/inativar usuário");
        System.out.println(" 12 - Prazos de empréstimo");
        System.out.println(" 13 - Limites de empréstimo");
        System.out.println(" 14 - Consultar equipamento");
        System.out.println(" 15 - Consultar usuário");
        System.out.println(" 0  - Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }
}