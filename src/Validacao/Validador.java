/*
 * Classe usada para fazer as validações de todas as outras classes
 */
package Validacao;

import DAO.Conexao;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dimpeny
 */
public class Validador {

    /**
     * Metodo usado para validar o login do usuário.
     *
     * @param login É o login informado pelo usuário.
     * @param senha É a senha informada pelo usuário.
     *
     * @return um usuário com id e nome se validar, caso contrário retorna nulo.
     */
    public static Usuario validaLogin(String login, String senha) {
        PreparedStatement ps = null;
        try {
            Connection con = Conexao.conectarBD();
            if (con != null) {
                String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, login);
                ps.setString(2, senha);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    ps.close();
                    return new Usuario(id, nome);
                }
                con.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Valida se um CPF é valido .
     *
     * @param cpf | CPF a ser validado.
     * @return true Se o CPF for valido. Senão retorna false.
     */
    public static boolean cpfValido(String cpf) {
        //remove os pontos e traço do cpf.
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("\\-", "");

        //retorna falso se o cpf for um numero só 11 vezes.
        if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666")
                || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")
                || cpf.equals("00000000000")) {
            return false;
        }

        //Separa os digitos do cpf.
        //Retorna falso se houver algum espaço.
        List<Integer> d = new ArrayList<>();
        String s;
        for (int i = 0; i < 11; i++) {
            s = "";
            s += cpf.charAt(i);
            try {
                d.add(Integer.parseInt(s));
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        //Validação do primeiro digito verificador.
        int soma = 0;
        int multiplicador = 10;
        for (int i = 0; i < 9; i++) {
            soma += d.get(i) * multiplicador;
            multiplicador--;
        }
        int sobra = 11 - (soma % 11);

        //Retorna false se o primeiro digito verificador não estiver correto.
        System.out.println("Sobra = " + sobra);
        if (d.get(9) == sobra || (d.get(9) == 0 && (sobra == 10 || sobra == 11))) {
            System.out.println("Primeiro digito verificador está correto");
        } else {
            System.out.println("Primeiro digito verificador está incorreto,");
            return false;
        }

        //Validação do segundo digito verificador.
        soma = 0;
        multiplicador = 11;
        for (int i = 0; i < 10; i++) {
            soma += d.get(i) * multiplicador;
            multiplicador--;
        }
        sobra = 11 - (soma % 11);

        //Retorna false se o segundo digito verificador não estiver correto.
        if (d.get(10) == sobra || (d.get(10) == 0 && (sobra == 10 || sobra == 11))) {
            System.out.println("Segundo digito verificador está correto");
            return true;
        }
        System.out.println("Segundo digito verificador está incorreto,");
        return false;

    }
}
