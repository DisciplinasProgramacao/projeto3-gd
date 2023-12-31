import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Estacionamento estac[] = Estacionamento.carregarEstacionamento();
        Scanner scanner = new Scanner(System.in);
        int escolha = 99;
        while (escolha != 8) {
            //comentario
            scanner.nextLine();
            System.out.println("########################################");
            System.out.println("##------| Xulambs Parking |-----------##");
            System.out.println("########################################");
            System.out.println("##--------------| MENU |--------------##");
            System.out.println("#  1. Criar Estacionamento             #");
            System.out.println("#  2. Cadastrar Cliente                #");
            System.out.println("#  3. Cadastrar Veiculo                #");
            System.out.println("#  4. Estacionar Veiculo               #");
            System.out.println("#  5. Sair Veiculo                     #");
            System.out.println("#  6. Gerar Relatorios                 #");
            System.out.println("#  7. Gerarenciar clientes             #");
            System.out.println("#  8. Sair                             #");
            System.out.println("########################################");
            escolha = scanner.nextInt();
            int esc = 0;
            switch (escolha) {
                case 1:
                    criarEstacionamento(estac);
                    break;
                case 2:
                    cadastrarCliente(estac);
                    break;
                case 3:
                    cadastrarVeiculo(estac);
                    break;
                case 4:
                    estacionarVeiculo(estac);
                    break;
                case 5:
                    sairVeiculo(estac);
                    break;

                case 6:
                    gerarRelatorios(estac);
                    break;
                case 7:
                    gerenciarClientes(estac);
                    break;
                case 8:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    public static void criarEstacionamento(Estacionamento[] estac) {
        Scanner scanner = new Scanner(System.in);

        try {
            if (estac.length >= 3) {
                System.out.println("NÚMERO MÁXIMO DE ESTACIONAMENTOS ALCANÇADO!!");
                return; 
            }

            System.out.println("Entre com o nome do estacionamento:");
            String nome = scanner.nextLine();

            // Verifica se um estacionamento com o mesmo nome já existe
            for (Estacionamento estacionamento : estac) {
                if (estacionamento != null && estacionamento.getNome().equals(nome)) {
                    throw new EstaJaCadastradoException("Estacionamento com o mesmo nome já existe.");
                }
            }

            System.out.println("Entre com o número de fileiras:");
            int fileiras = scanner.nextInt();
            System.out.println("Entre com o número de vagas por fileiras:");
            int vagas = scanner.nextInt();

            // Cria um novo estacionamento e adiciona ao array
            Estacionamento novoEstacionamento = new Estacionamento(nome, fileiras, vagas);
            Estacionamento[] novoArrayEstac = Arrays.copyOf(estac, estac.length + 1);
            novoArrayEstac[estac.length] = novoEstacionamento;
            estac = novoArrayEstac;

        } catch (EstaJaCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarCliente(Estacionamento[] estac) {
        Scanner scanner = new Scanner(System.in);
        int esc = 0;
        int esc2 = 0;
        int esc3 = 0;
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++)
            System.out.println(i + 1 + " " + estac[i].getNome());
        esc = scanner.nextInt();
        System.out.println("Registrar novo cliente. Escolha a opção:");
        System.out.println("#1# para Cliente Horista.");
        System.out.println("#2# para Cliente Mensalista.");
        System.out.println("#3# para Cliente Turno.");
        System.out.println("#4# para Cliente Anonimo.");
        esc2 = scanner.nextInt();
        scanner.nextLine();
        if (esc2 == 3) {
            System.out.println("Escolha o turno desejado:");
            System.out.println("#1# MANHA (8:00 - 12:00).");
            System.out.println("#2# TARDE (12:01 - 18:00).");
            System.out.println("#3# NOITE (18:01 - 23:59).");
            esc3 = scanner.nextInt();
        }
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        try {
            for (Cliente cliente : estac[esc - 1].getClientes()) {
                if (cliente != null && cliente.getNome().equals(nomeCliente)) {
                    throw new ClienteJaCadastradoException("Cliente já cadastrado");
                }
            }

            if (esc2 == 1) {
                Cliente novoCliente = new Cliente(nomeCliente, esc2, 0);
                estac[esc - 1].addCliente(novoCliente);
            } else if (esc2 == 2) {
                Cliente novoCliente = new Cliente(nomeCliente, esc2, 0);
                estac[esc - 1].addCliente(novoCliente);
            } else if (esc2 == 3) {
                Cliente novoCliente = new Cliente(nomeCliente, esc2, esc3);
                estac[esc - 1].addCliente(novoCliente);
            }
        } catch (ClienteJaCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarVeiculo(Estacionamento[] estac) {
        Scanner scanner = new Scanner(System.in);
        int esc;
        String codigCli;
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++)
            System.out.println(i + 1 + " " + estac[i].getNome());
        esc = scanner.nextInt() - 1;
        Cliente[] cli = estac[esc].getClientes();
        System.out.flush();
        System.out.println("Insira o código do Cliente dono do veículo:");
        for (int i = 0; i < cli.length; i++) {
            System.out.println(cli[i].getNome() + " Codigo: " + cli[i].getId());
        }
        codigCli = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Insira a placa do veículo:");
        String placa = scanner.nextLine();
        try {
            verificarVeiculoCadastrado(estac, placa);
            System.out.println("Insira o modelo do veículo:");
            String modelo = scanner.next();
            Veiculo novoVeiculo = new Veiculo(placa, modelo);
            estac[esc].addVeiculo(novoVeiculo, codigCli);
        } catch (VeiculoJaCadastradoException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void verificarVeiculoCadastrado(Estacionamento[] estac, String placa)
        throws VeiculoJaCadastradoException {
    for (Estacionamento estacionamento : estac) {
        Cliente[] clientes = estacionamento.getClientes();

        if (clientes != null) { 
            for (Cliente cliente : clientes) {
                if (cliente != null) {
                    Veiculo[] veiculos = cliente.getVeiculo();

                    if (veiculos != null) { 
                        for (Veiculo veiculo : veiculos) {
                            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                                throw new VeiculoJaCadastradoException("Veículo já cadastrado.");
                            }
                        }
                    }
                }
            }
        }
    }
}

    public static void estacionarVeiculo(Estacionamento[] estac) {
        int esc;
        Scanner scanner = new Scanner(System.in);
        String placaEstacionar;
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++) {
            System.out.println(i + 1 + " " + estac[i].getNome());
        }
        esc = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Estacionar Veículo \nInsira a PLACA: ");
        placaEstacionar = scanner.nextLine();

        estac[esc].estacionar(placaEstacionar);
    }

    public static void sairVeiculo(Estacionamento[] estac) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++) {
            System.out.println(i + 1 + " " + estac[i].getNome());
        }
    
        int esc = scanner.nextInt() - 1;
        scanner.nextLine();
    
        System.out.println("Insira a placa do carro:");
        String placaSaida = scanner.nextLine();
    
        double valorAPagar = 0.0;
        for (Cliente cliente : estac[esc].getClientes()) {
            Veiculo[] veiculos = cliente.getVeiculo();
            if (veiculos != null) { // Verificar se a lista de veículos não é nula
                for (Veiculo veiculo : veiculos) {
                    if (veiculo != null && veiculo.getPlaca().equals(placaSaida)) {
                        LinkedList<UsoDeVaga> usos = veiculo.getUsos();
                        if (!usos.isEmpty()) {
                            UsoDeVaga ultimoUso = usos.getLast();
                            LocalDate entrada = ultimoUso.getEntrada(); // Obter a data e hora de entrada
                            LocalDateTime saida = LocalDateTime.now();
                            long minutosEstacionado = ChronoUnit.MINUTES.between(entrada, saida);
                            valorAPagar = calcularValorPago(minutosEstacionado);
                        }
                        break;
                    }
                }
            } else {
                System.out.println("Cliente sem veículos registrados.");
            }
        }
    
        if (valorAPagar > 0.0) {
            System.out.println("TOTAL A PAGAR: R$" + String.format("%.2f", valorAPagar));
        } else {
            System.out.println("Veículo não encontrado ou sem uso registrado.");
        }
    
        if (valorAPagar > 0.0) {
            System.out.println("TOTAL A PAGAR: R$" + String.format("%.2f", valorAPagar));
        } else {
            System.out.println("Veículo não encontrado ou sem uso registrado.");
        }
    }
    
    public static double calcularValorPago(long minutosEstacionado) {
        double valorPorFracao = 4.0; // Valor por fração de tempo (15 minutos)
        int minutosPorFracao = 15; // 15 minutos por fração
    
        int numeroFracoes = (int) Math.ceil((double) minutosEstacionado / minutosPorFracao);
    
        // Limitar o valor máximo a ser cobrado a R$50
        double valorAPagar = Math.min(numeroFracoes * valorPorFracao, 50.0);
        return valorAPagar;
    }

    public static void gerarRelatorios(Estacionamento[] estac) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++)
            System.out.println(i + 1 + " " + estac[i].getNome());
       int  esc = scanner.nextInt();
        int mesCorrente = LocalDate.now().getMonthValue();

        System.out.println("======= Relatório do Estacionamento =======");

            try {
                System.out.println("Estacionamento: " + estac[esc-1].getNome());
                System.out.println("Total arrecadado: " + estac[esc-1].calcularValorTotal());
                System.out.println("Arrecadação no mês corrente: " + estac[esc-1].arrecadacaoNoMes(mesCorrente));
                System.out.println("Valor médio por uso: " + estac[esc-1].calcularValorMedioPorUso());
                System.out.println("Média de uso mensalista no mês corrente: "
                        + estac[esc-1].mediaUsoMensalistaMesCorrente(mesCorrente));
                System.out.println("Média de arrecadação de horistas no mês corrente: "
                        + estac[esc-1].mediaArrecadacaoHorista(mesCorrente));
                System.out.println("Top 5 clientes no mês corrente: " + estac[esc-1].top5Clientes(mesCorrente));
                System.out.println("Relatório de arrecadação decrescente:");
                estac[esc-1].arrecadacaoTotalDecrescente();
                System.out.println("-----------------------------------------");
            } catch (NullPointerException e) {
                System.out.println(
                        "Ocorreu um erro ao gerar relatórios para o estacionamento " + estac[esc-1].getNome());
                System.out.println("Detalhes do erro: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
    }

    public static void gerenciarClientes(Estacionamento[] estac){
        int esc2 = 0;
        int esc3 = 0;
        String txt = "text";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o estacionamento:");
        for (int i = 0; i < estac.length; i++)
            System.out.println(i + 1 + " " + estac[i].getNome());
        int  esc = scanner.nextInt();

        System.out.println("######## CLIENTES CADASTRADOS ######### ");
        txt = estac[esc-1].clientes();
        System.out.println(txt);
        System.out.println("####### ALTERAR TIPO DE USUARIO #########");
        System.out.println("SELECIONE O CODIGO DO USUARIO QUE DESEJA ALTERAR:");
        scanner.nextLine();
        String codigo = scanner.nextLine();
        System.out.println("Escolha a opção:");
        System.out.println("#1# para Cliente Horista.");
        System.out.println("#2# para Cliente Mensalista.");
        System.out.println("#3# para Cliente Turno.");
        System.out.println("#4# para Cliente Anonimo.");
        esc2 = scanner.nextInt();
        scanner.nextLine();
        if (esc2 == 3) {
            System.out.println("Escolha o turno desejado:");
            System.out.println("#1# MANHA (8:00 - 12:00).");
            System.out.println("#2# TARDE (12:01 - 18:00).");
            System.out.println("#3# NOITE (18:01 - 23:59).");
            esc3 = scanner.nextInt();
        };

        estac[esc-1].aterarTipoCliente(codigo, esc2, esc3);

    }


}
