import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Estacionamento estac[] = Estacionamento.carregarEstacionamento();
        Scanner scanner = new Scanner(System.in);
        int escolha=99;
        while (escolha!=0) {
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
            System.out.println("#  7. Sair                             #");
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
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

     public static void criarEstacionamento(Estacionamento[] estac){
        Scanner scanner = new Scanner(System.in);
        if(estac.length == 3){System.out.println("NUMERO MAXIMO ALCANCADO !!");}
            else{
                System.out.println("Entre com o nome estacionamento: ");
                String nome = scanner.nextLine();
                System.out.println("Entre com o numero de fileiras: ");
                int fileiras = scanner.nextInt();
                System.out.println("Entre com o nome vagas por fileiras: ");
                int vagas = scanner.nextInt();
                if (estac == null) {
                    estac = new Estacionamento[1];
                    estac[0] = new Estacionamento(nome, fileiras, vagas);
                } else {
                    Estacionamento[] newEstac = new Estacionamento[estac.length + 1];
                    for (int i = 0; i < estac.length; i++) {
                        newEstac[i] = estac[i];
                    }
                    newEstac[estac.length] = new Estacionamento(nome, fileiras, vagas);
                        estac = newEstac;
                    }}

    }

    public static void cadastrarCliente(Estacionamento[] estac){
        Scanner scanner = new Scanner(System.in);
        int esc = 0;
        int esc2 =0;
        int esc3 = 0;
            System.out.println("Selecione o estacionamento:");
            for (int i =0; i < estac.length;i++)
                System.out.println(i+1 +" "+ estac[i].getNome());
                esc = scanner.nextInt();
                System.out.println("Registrar novo cliente. Escolha a opção:");
                System.out.println("#1# para Cliente Horista.");
                System.out.println("#2# para Cliente Mensalista.");
                System.out.println("#3# para Cliente Turno.");
                System.out.println("#4# para Cliente Anonimo.");
                esc2 = scanner.nextInt();
                scanner.nextLine();
                if (esc2 == 3){
                    System.out.println("Escolha o turno desejado:");
                    System.out.println("#1# MANHA (8:00 - 12:00).");
                    System.out.println("#2# TARDE (12:01 - 18:00).");
                    System.out.println("#3# NOITE (18:01 - 23:59).");
                    esc3 = scanner.nextInt();
                }
                System.out.print("Digite o nome do cliente: ");
                String nomeCliente = scanner.nextLine();
                if (esc2 == 1) {
                    Cliente novoCliente = new Cliente(nomeCliente, esc2, 0);
                    estac[esc-1].addCliente(novoCliente);
                } else if (esc2 == 2) {
                    Cliente novoCliente = new Cliente(nomeCliente, esc2, 0);
                    estac[esc-1].addCliente(novoCliente);
                } else if (esc2 == 3) {
                    Cliente novoCliente = new Cliente(nomeCliente, esc2, esc3);
                    estac[esc-1].addCliente(novoCliente);
                }
    }

    public static void cadastrarVeiculo(Estacionamento[] estac){
        Scanner scanner = new Scanner(System.in);
        int esc;
        String codigCli;
            System.out.println("Selecione o estacionamento:");
            for (int i =0; i < estac.length;i++)
                System.out.println(i+1 +" "+ estac[i].getNome());
                esc = scanner.nextInt()-1;
                Cliente[] cli = estac[esc].getClientes();
                System.out.flush();
                System.out.println("Insira o código do Cliente dono do veículo:");
            for (int i = 0; i < cli.length; i++){
                System.out.println(cli[i].getNome() + " Codigo: "+ cli[i].getId());
            }
            codigCli = scanner.nextLine();

                System.out.println("Insira a placa do veículo:");
                String placa = scanner.nextLine();
                System.out.println("Insira o modelo do veículo:");
                String modelo = scanner.nextLine();
                Veiculo novoVeiculo = new Veiculo(placa, modelo);
                estac[esc].addVeiculo(novoVeiculo, codigCli);
    }

    public static void estacionarVeiculo(Estacionamento[] estac){
        int esc;
        Scanner scanner = new Scanner(System.in);
        String placaEstacionar;
            System.out.println("Selecione o estacionamento:");
            for (int i =0; i < estac.length;i++)
                System.out.println(i+1 +" "+ estac[i].getNome());
                esc = scanner.nextInt()-1;
                System.out.println("Estacionar Veículo \nInsira a PLACA: ");
                placaEstacionar = scanner.nextLine();

                estac[esc].estacionar(placaEstacionar);
    }

    public static void sairVeiculo(Estacionamento[] estac){
        int esc;
        Scanner scanner = new Scanner(System.in);
        String placaSaida;
            Double valor;
            System.out.println("Selecione o estacionamento:");
            for (int i =0; i < estac.length;i++)
                System.out.println(i+1 +" "+ estac[i].getNome());
                esc = scanner.nextInt()-1;

                System.out.println("Insira a placa do carro:");
                placaSaida = scanner.nextLine();

                valor = estac[esc].sair(placaSaida);

                System.out.println("TOTAL A PAGAR: "+ valor);
    }
}
