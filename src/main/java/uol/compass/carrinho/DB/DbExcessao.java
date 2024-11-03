package uol.compass.carrinho.DB;

public class DbExcessao extends RuntimeException {
    public DbExcessao(String msg) {
        super(msg);
    }
}