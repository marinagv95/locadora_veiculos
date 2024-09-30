# Locadora de Veículos

## Descrição

Este é um projeto de locadora de veículos desenvolvido em Java, que permite gerenciar o aluguel de veículos, 
incluindo funcionalidades para cadastrar, listar, e gerenciar clientes e veículos. 
O sistema visa facilitar a administração de locações, devoluções e consulta de informações.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento. (Versão 22)
- **IDE** "IntelliJ

## Funcionalidades

- **Cadastro de Veículos**: Adicione novos veículos ao sistema.
- **Cadastro de Clientes**: Registre clientes que podem alugar veículos.
- **Aluguel de Veículos**: Realize a locação de veículos para clientes.
- **Devolução de Veículos**: Gerencie a devolução de veículos alugados.
- **Listagem de Veículos**: Consulte todos os veículos disponíveis no sistema.
- **Listagem de Clientes**: Consulte todos os clientes cadastrados.

## Estrutura do Projeto

```bash
locadora_veiculos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── modelo/          # Modelos de dados
│   │   │   │   ├── agencia/     # Modelos relacionados à agência
│   │   │   │   ├── aluguel/     # Modelos relacionados ao aluguel
│   │   │   │   ├── endereco/    # Modelos relacionados ao endereço
│   │   │   │   ├── pessoa/      # Modelos relacionados à pessoa
│   │   │   │   ├── veiculo/     # Modelos relacionados ao veículo
│   │   │   ├── repositorio/     # Repositórios de dados
│   │   │   ├── servico/         # Lógica de negócio 
│   │   │   ├── exception/       # Exceções personalizadas
│   │   │   ├── util/            # Classes utilitárias
│   │   │   ├── visual/          # Classes de visualização (UI)
│   │   │   ├── principal/        # Classe Principal
│   │   ├── resources/           # Recursos (configurações, etc.)
````

## Princípios SOLID

Neste projeto, foram aplicados os princípios SOLID para garantir uma arquitetura limpa e manutenível:

1. **S - Single Responsibility Principle (Princípio da Responsabilidade Única)**: Cada classe tem uma única responsabilidade, facilitando a manutenção e evolução do código.
   
2. **O - Open/Closed Principle (Princípio do Aberto/Fechado)**: As classes estão abertas para extensão, mas fechadas para modificação, permitindo que novas funcionalidades sejam adicionadas sem alterar o código existente.
   
3. **L - Liskov Substitution Principle (Princípio da Substituição de Liskov)**: As subclasses podem ser utilizadas no lugar de suas classes base sem alterar o comportamento do programa.
   
4. **I - Interface Segregation Principle (Princípio da Segregação de Interfaces)**: Interfaces específicas são preferíveis a interfaces gerais, garantindo que as classes implementem apenas os métodos que realmente utilizam.
   
5. **D - Dependency Inversion Principle (Princípio da Inversão de Dependência)**: Os módulos de alto nível não devem depender de módulos de baixo nível; ambos devem depender de abstrações. Isso foi implementado através do uso de interfaces e injeção de dependência.

## Como Usar

Para utilizar este projeto, clone o repositório e execute a aplicação em seu ambiente de desenvolvimento preferido.

## Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/allanaavila/locadora_veiculos.git
2. **Navegue até o diretório do projeto:**
   ```bash
   cd locadora_veiculos

3. **Abra o projeto na sua IDE (IntelliJ IDEA).**
4. **Compile e execute a aplicação.**

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para enviar um pull request ou abrir uma issue para discutir melhorias.






