package pizzariadaswinxs.model;

/**
 * Preferências do pedido (RF04 / seção 7.5): molho, talheres e
 * observação. Devolvido pelo MenuController (Pessoa 3) — quem aplica
 * isso ao Pedido é a Pessoa 4, mantendo o Menu sem depender de Pedido.
 */
public record Preferencias(
        boolean molho,
        boolean talheres,
        String observacao
) {
}