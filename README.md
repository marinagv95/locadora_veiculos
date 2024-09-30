# Locadora de Veículos

## Descrição

Este é um projeto de locadora de veículos desenvolvido em Java, que permite gerenciar o aluguel de veículos, 
incluindo funcionalidades para cadastrar, listar, e gerenciar clientes e veículos. 
O sistema visa facilitar a administração de locações, devoluções e consulta de informações.

## Tecnologias Utilizadas

- **Java SDK 21**: Linguagem de programação utilizada para o desenvolvimento.
- **IDE** IntelliJ

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

## Funcionalidades

- **Cadastro de Veículos**: Adicione novos veículos ao sistema.
- **Aluguel de Veículos**: Realize a locação de veículos para clientes.
- **Devolução de Veículos**: Gerencie a devolução de veículos alugados.
- **Listagem de Veículos**: Consulte todos os veículos disponíveis no sistema.
- **Cadastro de Clientes**: Registre clientes que podem alugar veículos.
- **Listagem de Clientes**: Consulte todos os clientes cadastrados.
- **Cadastro de Locadoras**: Registre locadoras para realizar aluguéis de veículos.
- **Listagem de Locadoras**: Consulte todas as locadoras cadastradas.
- **Gerar comprovate de aluguel**: Gere um comprovante para a locação de veículos.
- **Gerar comprovante de devolução**: Gere um comprovante para a devolução de veículos.

## Interfaces interativas

### Menu Principal
![menuPrincipal](imagens/menuPrincipal.png)

### Menu Locadora
![menuLocadora](imagens/menuLocadora.png)

### Menu Veículos
![menuVeiculos](imagens/menuVeiculo.png)

### Menu Cliente
![menuCliente](imagens/menuCliente.png)

### Menu Agencia
![menuAgencia](imagens/menuAgencia.png)

### Menu Aluguel
![menuAluguela](imagens/menuAluguel.png)

### Veja passo a passo: 
[Clique aqui](https://drive.google.com/file/d/1V1xHrbvwf5Gm77JHN4UXc7a94ZMJ1Kcg/view?usp=sharing)

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
### Diagrama de Classes
![diagramaClasses](imagens/diagramaDeClasses.png)

## Princípios SOLID

Neste projeto, foram aplicados os princípios SOLID para garantir uma arquitetura limpa e manutenível:

1. **S - Single Responsibility Principle (Princípio da Responsabilidade Única)**: Cada classe e método tem uma única responsabilidade, facilitando a manutenção e evolução do código.
   
2. **O - Open/Closed Principle (Princípio do Aberto/Fechado)**: As classes estão abertas para extensão, mas fechadas para modificação, permitindo que novas funcionalidades sejam adicionadas sem alterar o código existente.
   
3. **L - Liskov Substitution Principle (Princípio da Substituição de Liskov)**: As subclasses podem ser utilizadas no lugar de suas classes base sem alterar o comportamento do programa.
   
4. **I - Interface Segregation Principle (Princípio da Segregação de Interfaces)**: Interfaces específicas são preferíveis a interfaces gerais, garantindo que as classes implementem apenas os métodos que realmente utilizam.
   
5. **D - Dependency Inversion Principle (Princípio da Inversão de Dependência)**: Os módulos de alto nível não devem depender de módulos de baixo nível; ambos devem depender de abstrações. Isso foi implementado através do uso de interfaces e injeção de dependência.



## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para enviar um pull request ou abrir uma issue para discutir melhorias.

## Alunos

<a href="https://github.com/allanaavila">
    <img src="https://avatars.githubusercontent.com/allanaavila" alt="Allana Ávila" width="100" />
</a>

<a href="https://github.com/dev-luizotavio">
    <img src="https://avatars.githubusercontent.com/dev-luizotavio" alt="Luiz Otávio Ferreira" width="100" />
</a>

<a href="https://github.com/toscanomatheus">
    <img src="https://avatars.githubusercontent.com/toscanomatheus" alt="Matheus Toscano Vidal" width="100" />
</a>

<a href="https://github.com/marinagv95">
    <img src="https://avatars.githubusercontent.com/marinagv95" alt="Marina Guimarães Vieira" width="100" />
</a>





