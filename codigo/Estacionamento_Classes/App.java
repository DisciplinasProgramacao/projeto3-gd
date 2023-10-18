import java.time.LocalDate;
import java.util.Scanner;

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
            System.out.println("#  4. Sair Carro                       #");
            System.out.println("#  5. Sair                             #");
            System.out.println("########################################");
            escolha = scanner.nextInt();
            switch (escolha) {

                case 1:
                    int esc = 99;
                    System.out.flush();
                    System.out.println("Registrar novo cliente. 1 para Cliente normal. 2 para Anonimo:");
                    esc = scanner.nextInt();
                    if(esc == 1){
                        System.out.print("Digite o nome do cliente: ");
                        String nomeCliente = scanner.nextLine(); 
                        Cliente novoCliente = new Cliente(nomeCliente);
                    } 
                    else if (esc == 2){
                        Cliente novoCliente = new Cliente();
                    }
                    break;
                case 2:
                    System.out.flush();
                    System.out.println("Insira o codigo do Cliente dono do veiculo:");
                    List<Cliente> q = Cliente.lerItensDoArquivo("clientes.txt");
                    for (int i = 0; i < q.size(); i++){
                        System.out.println(q.get(i).toString());  
                    }
                    
                case 3:
                    
                case 4:
                
                case 5:
                    
                default:
                 System.out.println("Escolha inválida. Tente novamente.");
      }
    }
        scanner.close();
    }
}
