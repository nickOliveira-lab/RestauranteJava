package pizzariadaswinxs.exception;

public class AreaRestritaException extends RuntimeException {

    public AreaRestritaException(String bairro) {
        super("Área de entrega restrita. Não atendemos o bairro: " + bairro
                + ". Atendemos apenas Santo Amaro, Boa Vista e Recife Antigo.");
    }
}