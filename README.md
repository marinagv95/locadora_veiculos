# Locadora de Veículos

## Descrição do Projeto

Este projeto tem como objetivo desenvolver um sistema de gerenciamento para uma locadora de veículos, utilizando a linguagem de programação Java. O sistema permitirá a realização de diversas operações essenciais para o funcionamento de uma locadora, como:

- Cadastro de clientes, veículos e agências.
- Gerenciamento de aluguel de veículos, incluindo o cálculo do valor do aluguel com base no período selecionado.
- Cálculo de multas em caso de devolução após o prazo estabelecido.
- Geração de comprovantes de saída, fornecendo um registro detalhado da transação de aluguel.

O sistema foi projetado para oferecer uma interface intuitiva e funcionalidades que simplificam o controle de todas as operações relacionadas à locação de veículos, garantindo uma gestão eficiente e precisa.


## Estrutura do Projeto

O projeto está dividido em vários pacotes para facilitar a organização:

- modelo: Contém as classes que representam os dados de saída (agencia, aluguel, endereco, pessoa, veiculo).
- principal: Contém os principais métodos do programa (principalAgencia, principalAluguel, principalPessoa, principalVeiculo).
- repositorio: Contém os principais repositorios e implementações das principais classes do programa (agenciaRepositorio, aluguelRepositorio, pessoaRepositorio, veiculoRepositorio).
- servico: Contém as principais classes e implementações de cada funcionalidade do programa (agenciaServico, aluguelServico, pessoaServico, veiculoServico).
- util: Contém as classes e métodos que realizam as validações de acordo com os requisitos do programa (aluguelUtil, enderecoUtil, leitura, pessoaUtil).
- visual: Cuida de toda a parte visual e de menus do programa(Menu: Agencia, Aluguel, Pessoa, Principal, Veiculos).

## Requisitos

- Java SDK 21
- Sugerimos o uso do IntelliJ como IDE para melhor experiência de uso.

## Guia de Uso

Para melhor experiência durante o uso do programa, temos alguns requisitos:

- É necessário o cadastro de ao menos um veículo, um usuário e duas agências (a de retirada e a de entrega)
- Não é possível cadastrar com o mesmo CPF ou CNPJ (se aplica a agências também).
- Veículos não podem ser repetidos e nem poderão ser alugados e entregues na mesma agência.
- É necessário informar a data, local e horário de entrega no momento da retirada e entrega do veículo.


## Conceitos aprendidos e suas utilizações


## Dificuldades durante a criação do projeto






