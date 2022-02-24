/*
 * Classe usada para modelar um usuário do sistema
 */
package Modelo;

/**
 *
 * @author Dimpeny
 */
public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;
    
    /**
    * Construtor usado no retorno de validaLogin(String,String),
    * e na lista de usuários.
    * 
    * @param id | É o ID do usuário.
    * @param nome | É o nome do usuário.
    */
    public Usuario(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    /**
     * Construtor usado na inserção de um novo usuário.
     * 
     * @param nome | É o nome do usuário.
     * @param login | É o login usado pelo usuário.
     * @param senha | É a senha usada pelo usuário.
     */
    public Usuario(String nome, String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    
    /*
    Getters
    */

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
}
