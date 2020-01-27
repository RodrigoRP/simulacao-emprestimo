package br.com.rodrigoramos.desafiodbc.model.enums;

public enum TipoCliente {

    TIPO_A(1, "Tipo A"),
    TIPO_B(2, "Tipo B"),
    TIPO_C(3, "Tipo C");


    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
