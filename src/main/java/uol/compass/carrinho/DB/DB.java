package uol.compass.carrinho.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = carregarPropriedades();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DbExcessao(e.getMessage());
            }
        }
        return conn;
    }

    public static void fecharConexao() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbExcessao(e.getMessage());
            }
        }
    }

    private static Properties carregarPropriedades() {
        try (FileInputStream fs = new FileInputStream("db.propriedades")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbExcessao(e.getMessage());
        }
    }

    public static void fecharStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbExcessao(e.getMessage());
            }
        }
    }

    public static void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbExcessao(e.getMessage());
            }
        }
    }
}
