# Pizzaria das Winxs

Sistema de pedidos em Java 21 para a Pizzaria das Winxs, feito em grupo (Grupo 2). Aplicação de terminal, sem banco de dados — os dados ficam em memória durante a execução.

## Tecnologia

- Java 21
- Projeto Java simples no IntelliJ (sem Maven/Gradle)
- Sem uso de `for`, `while` ou `do-while` — iterações feitas com recursão

## Funcionalidades

- Identificação do cliente pelo nome
- Exibição do cardápio (pizzas, bebidas, sobremesas e combo)
- Montagem do pedido, com quantidade por item
- Escolha entre delivery ou retirada
- Validação de bairro atendido para delivery
- Escolha da forma de pagamento
- Cálculo automático de subtotal, taxa de entrega, desconto e total
- Resumo do pedido antes da finalização
- Bloqueio da finalização caso o pedido esteja vazio

## Regras de negócio

As duas regras abaixo foram especificadas diretamente pelo cliente:

- **Frete grátis:** pedidos de delivery acima de R$ 100,00 não pagam taxa de entrega.
- **Desconto de 10%:** aplicado apenas quando o pagamento é **PIX e** o valor do pedido é **acima de R$ 150,00** — as duas condições precisam ocorrer juntas.

---

Demais regras (definidas pelo grupo, com base no escopo do projeto):

- Delivery restrito aos bairros Santo Amaro, Boa Vista e Recife Antigo
- Retirada não cobra taxa de entrega
- O pedido precisa ter pelo menos um item para ser finalizado

## Estrutura

src/pizzariadaswinxs/
├── Main.java
├── model/        → Cliente, Produto, Pizza, Bebida, Sobremesa, Combo, ItemPedido, Pedido, Preferencias, ResultadoPagamento
├── controller/    → MenuController, PagamentoController
├── service/       → PagamentoService
└── exception/     → PedidoInvalidoException, AreaRestritaException

## Como rodar

Abra o projeto no IntelliJ e execute `Main.java` (▶️).

## Equipe

| Responsável | Parte |
|---|---|
| Tarcilla e Luiza | Model |
| Douglas | Menu / cardápio |
| Nicolas | Pedido, cálculo, resumo e finalização |
| Jonas e José Carlos | Delivery e pagamento |
