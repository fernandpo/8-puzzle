package grad.ufsc.br.puzzle.Entidades;

public enum VariacoesAlgoritimo {
    CUSTO_UNIFORME("Custo Uniforme"),
    A_ESTRELA_SIMPLES("A* Simples"),
    A_ESTRELA_MANHATTAN("A* Manhattan");

    private final String descricao;
    VariacoesAlgoritimo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoVariacaoAlgoritimo(){
        return this.descricao;
    }

    public static VariacoesAlgoritimo fromString(String text) {
        for (VariacoesAlgoritimo variacao : VariacoesAlgoritimo.values()) {
            if (variacao.descricao.equalsIgnoreCase(text)) {
                return variacao;
            }
        }
        return VariacoesAlgoritimo.CUSTO_UNIFORME;
    }
}
