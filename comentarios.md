# Correção
  - Na data, nem o readme estava completo
  - Na data, nenhum teste no projeto
  - Salvando sem serialização
  - **App**: leitura de teclado sem mensagem no início
  - **App**: erro para criar o estacionamento (e então não consigo testar mais nada)
  - **App**: erros (exceções não tratadas) para tudo
  - **App**: Relatórios não estão presentes
  - **Cliente**: gets sem necessidade gerando quebra de encapsulamento no app
  - **Cliente**: addVeiculo sobrescreve o primeiro (dois new)
  - **Cliente**: arrecadacao chamando método errado
  - **Cliente**: Historico void não faz nada
  - **Cliente**: Carrega todos os clientes por static (é a melhor solução?)
  - **Estacionamento**: duplicação no construtor
  - **Estacionamento**: não chama exceções
  - **Estacionamento**: cada cliente novo recria todo o vetor
  - **Estacionamento**: gerar vagas não devia ter parâmetro
  - **Estacionamento**: getVeiculo em cliente???
  - **Estacionamento**: total arrecadado: é por cliente. Erros em todos os métodos de arrecadação
  - **Estacionamento**: sem top 5
  - **Estacionamento**: getDesnecessário
  - **UsoDeVaga**: enum dentro da classe
  - **UsoDeVaga**: preço é por fração de minutos
  - **UsoDeVaga**: exceção de tempo mínimo
  - **UsoDeVaga**: getDesnecessario
  - **Veículo**: mata primeiro uso de vaga (dois new)
  - **Veículo**: mata primeiro uso de vaga (dois new)
  - **Veículo**: histórico x to string de lista
  - Sem documentação (apenas parcial em estacionamento)

## Nota base do grupo: 7,5

  - Contribuições
    - Augusto
      - App ⚠️⚠️⚠️
      - Uso de Vaga1: ⚠️
      - Excecoes em veiculo: ❌
      - Serialização: ⚠️
      - Teste veículo: ❌
    - Davi
      - Estacionamento: ⚠️⚠️⚠️⚠️
      - Uso com serviços: ⚠️
      - Excecoes em Uso: ➕➖ 
    - Pedro Ramos
      - App ⚠️⚠️⚠️
      - Estacionamento: ⚠️ (mexendo no que não era seu, e fez com erro (getVeiculo))
      - Teste Cliente ❌
      - Hist. Cliente ➕➖
      - Veiculo: ⚠️
      - Serialização: ⚠️
      - Relatorio Veiculos: ➕➖
    - Pedro Silva
      - Estacionamento: ⚠️⚠️⚠️
      - Teste Uso: ❌
      - Excecoes em estacionamento ❌
    - Renato
      - Cliente ⚠️⚠️
      - Estacionamento: mapas ❌
      - Estacionamento: teste ❌
      - Carga de dados: ❌
      

- Tarefas nas aulas 04 e 11/10: 5 pontos;
    - **Nenhuma atualização entre 04 e 16/10**
    - Augusto ✔️❌
    - Davi ✔️❌
    - Pedro Ramos ✔️❌
    - Pedro Silva ✔️ ❌
    - Renato ➕➖ ❌

- Requisitos : 7,5/12 pontos;
- Documentação: 0/3 pontos;
- Atrasos: 

   

## Requisitos

## Requisitos
  - Estacionar, sair e cobrança: R$4 a cada 15 minutos, com valor limite de R$50.   ➕➖
  - Serviços, tempo mínimo e cobrança  ➕➖
  - Um cliente identificado tem acesso a seu histórico de uso do estacionamento.   ➕➖
  - Os dados das classes existentes devem ser salvos utilizando-se serialização;  ❌
  - App: ❌
    - Cadastrar estacionamentos com número de vagas ➕➖
    - Veículos registrados por placa e ligados a clientes. 
    - Cliente identificado com nome e com mais de um veículo. 
    - Dados de clientes e veículos salvos e carregados.
    - 3 estacionamentos
	  - Gerar aleatoriamente 50 usos de vagas
  - Relatórios: 
    - Valor total arrecadado do estacionamento;
    - Valor arrecadado em determinado mês;
    - Valor médio de cada utilização do estacionamento;
    - Ranking dos clientes que mais geraram arrecadação em um determinado mês.
  - Exceções que serão tratadas no aplicativo:
    - Sair de uma vaga cujo uso já foi finalizado;
    - Estacionar em uma vaga sem haver finalizado o uso anterior;
    - Cadastrar um cliente já existente;
    - Cadastrar um veículo já existente;
  






