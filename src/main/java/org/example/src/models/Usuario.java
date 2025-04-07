package org.example.src.models;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    protected String name;
    protected String username;
    protected String password;
    protected int id;
    private static int proxyId = 1;

    public Usuario(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = proxyId++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getUsername(String username) {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static Usuario getUser(String username) {
        return users.get(username);
    }

    //armazena os usuarios em memória
    private static final Map<String, Usuario> users = new HashMap<>();

    public static boolean addUser(String username, String password, String name) {
        //verifica se o usuario ja existe
        if (users.containsKey(username)) {
            return false;
        }

        Usuario newUser = new Usuario(username, password, name);
        users.put(username, newUser);
        return true;
    }

    public static boolean validateUser(String username, String password) {
        //verificar se o usuario existe
        Usuario user = users.get(username);
        if(user == null){
            return false;
        }
        //verifica se a senha esta correta
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    // Verificar se um usuário existe
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
}
