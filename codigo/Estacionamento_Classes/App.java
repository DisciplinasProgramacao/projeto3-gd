import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha=99;
        while (escolha!=0) {
            scanner.nextLine();
            System.out.println("########################################");
            System.out.println("##------| Xulambs Parking |-------##    ");
            System.out.println("########################################");
            System.out.println("##--------------| MENU |--------------##");
            System.out.println("#  1. Cadastrar Cliente                #");
            System.out.println("#  2. Cadastrar Veiculo                #");
            System.out.println("#  3. Estacionar Veiculo               #");
            System.out.println("#  4. Sair Veiculo                       #");
            System.out.println("#  5. Sair                             #");
            System.out.println("########################################");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    int esc = 0;
                    System.out.println("Registrar novo cliente. Escolha a opção:");
                    System.out.println("1 para Cliente normal.");
                    System.out.println("2 para Anônimo.");
                    esc = scanner.nextInt();
                    scanner.nextLine();

                    if (esc == 1) {
                        System.out.print("Digite o nome do cliente: ");
                        String nomeCliente = scanner.nextLine();
                        try {
                            if (clienteJaExiste(nomeCliente)) {
                                throw new ClienteExistenteException("Cliente já cadastrado.");
                            }
                            Cliente novoCliente = new Cliente(nomeCliente);
                        } catch (ClienteExistenteException e) {
                            System.out.println(e.getMessage()); 
                        }
                    } else if (esc == 2) {
                        Cliente novoCliente = new Cliente();
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.flush();
                    System.out.println("Insira o código do Cliente dono do veículo:");
                    List<Cliente> clientes = Cliente.lerItensDoArquivo("clientes.txt");
                
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + ". " + clientes.get(i).toString());
                    }
                
                    int clienteIndex = scanner.nextInt();
                    scanner.nextLine(); 
                
                    if (clienteIndex >= 0 && clienteIndex < clientes.size()) {
                        Cliente clienteSelecionado = clientes.get(clienteIndex);
                
                        System.out.println("Insira a placa do veículo:");
                        String placa = scanner.nextLine();
                
                        try {
                            if (clienteSelecionado.veiculoJaExiste(placa)) {
                                throw new VeiculoExistenteException("Veículo com a mesma placa já cadastrado.");
                            } else {
                                System.out.println("Insira o modelo do veículo:");
                                String modelo = scanner.nextLine();
                
                                Veiculo novoVeiculo = new Veiculo(placa, modelo);
                                clienteSelecionado.adicionarVeiculo(novoVeiculo);
                
                                Cliente.salvarItensNoArquivo("clientes.txt", clientes);
                
                                List<Veiculo> veiculos = new ArrayList<>();
                                veiculos.add(novoVeiculo);
                                Veiculo.salvarVeiculosNoArquivo("veiculos.txt", veiculos);
                            }
                        } catch (VeiculoExistenteException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case 3:
                    System.out.println("Estacionar Veículo");
                    List<Cliente> clientes = Cliente.lerItensDoArquivo("clientes.txt");
                    
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
                    } else {
                        System.out.println("Escolha um cliente para estacionar o veículo:");
                        for (int i = 0; i < clientes.size(); i++) {
                            System.out.println(i + ". " + clientes.get(i).getNome());
                        }
                        int clienteIndex = scanner.nextInt();
                        
                        if (clienteIndex >= 0 && clienteIndex < clientes.size()) {
                            Cliente clienteSelecionado = clientes.get(clienteIndex);
                
                            try {
                                if (clienteSelecionado.temUsoAtivo()) {
                                    throw new EstacionamentoException("Você já tem um uso de vaga ativo. Termine o uso anterior antes de estacionar novamente.");
                                }
                
                                System.out.println("Insira a placa do veículo:");
                                String placa = scanner.next();
                
                                System.out.println("Insira o modelo do veículo:");
                                String modelo = scanner.nextLine();
                                
                                Veiculo novoVeiculo = new Veiculo(placa, modelo);
                                clienteSelecionado.adicionarVeiculo(novoVeiculo);
                
                                Cliente.salvarItensNoArquivo("clientes.txt", clientes);
                
                                System.out.println("Veículo estacionado com sucesso.");
                            } catch (EstacionamentoException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Veículos estacionados. Escolha o cliente e veículo que deseja retirar:");
                
                    for (int i = 0; i < clientes.size(); i++) {
                        Cliente cliente = clientes.get(i);
                        System.out.println(i + ". " + cliente.getNome() + " - Veículos:");
                        List<Veiculo> veiculos = cliente.getVeiculos();
                        for (int j = 0; j < veiculos.size(); j++) {
                            Veiculo veiculo = veiculos.get(j);
                            System.out.println("   " + j + ". " + veiculo.getPlaca());
                        }
                    }
                
                    int clienteIndex = scanner.nextInt();
                    int veiculoIndex = -1;
                
                    if (clienteIndex >= 0 && clienteIndex < clientes.size()) {
                        Cliente clienteSelecionado = clientes.get(clienteIndex);
                        List<Veiculo> veiculosCliente = clienteSelecionado.getVeiculos();
                
                        if (veiculosCliente.size() > 0) {
                            System.out.println("Escolha o veículo que deseja retirar:");
                            veiculoIndex = scanner.nextInt();
                
                            if (veiculoIndex >= 0 && veiculoIndex < veiculosCliente.size()) {
                                Veiculo veiculoARetirar = veiculosCliente.get(veiculoIndex);
                
                                try {
                                    double valorPago = veiculoARetirar.sair();
                
                                    System.out.println("Veículo com placa " + veiculoARetirar.getPlaca() + " retirado.");
                                    System.out.println("Valor a ser pago: " + valorPago);
                
                                    clienteSelecionado.removerVeiculo(veiculoARetirar);
                
                                    Cliente.salvarItensNoArquivo("clientes.txt", clientes);
                                } catch (UsoFinalizadoException e) {
                                    System.out.println("Erro ao retirar o veículo: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Opção de veículo inválida.");
                            }
                        } else {
                            System.out.println("Este cliente não possui veículos estacionados.");
                        }
                    } else {
                        System.out.println("Opção de cliente inválida.");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                 System.out.println("Escolha inválida. Tente novamente.");
      }
    }
        scanner.close();
    }
}
