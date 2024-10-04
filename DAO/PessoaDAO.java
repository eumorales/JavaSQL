package DAO;
import Conexao.Conexao;
import Principal.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Conexao conexao;
    private Connection conn;

    public PessoaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, idioma) VALUES (?, ?, ?);";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, pessoa.getIdioma());

            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir pessoa: " + ex.getMessage());
        }
    }
    
    public void editar(Pessoa pessoa) {
    try {
        String sql = "UPDATE pessoa set nome=?, sexo=?, idioma=? WHERE id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getSexo());
        stmt.setString(3, pessoa.getIdioma());
        stmt.setInt(4, pessoa.getId());
        
        stmt.execute();
        
    } catch (SQLException ex) {
        System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
    }
}

    

    public boolean checarPessoa(String nome) {
        String sql = "SELECT * FROM pessoa WHERE nome = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                System.out.println("Pessoa não encontrada.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar pessoa: " + ex.getMessage());
            return false;
        }
    }
    
        public void excluir(int id) {
    try {
        String sql = "delete from pessoa WHERE id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        
    } catch (SQLException ex) {
        System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
    }
}
    
public List<Pessoa> getPessoasNome(String nome, String sexo) {
    String sql = "SELECT * FROM pessoa WHERE nome LIKE ? and sexo LIKE ?";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt.setString(1, "%" + nome + "%");
        stmt.setString(2, "%" + sexo + "%");

        ResultSet rs = stmt.executeQuery();
        List<Pessoa> listaPessoas = new ArrayList<>(); 

        while (rs.next()) {
            Pessoa p = new Pessoa();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setSexo(rs.getString("sexo"));
            p.setIdioma(rs.getString("idioma"));
            listaPessoas.add(p);
        }
        return listaPessoas;
    } catch (SQLException ex) {
        System.out.println("Erro ao consultar todas as pessoas: " + ex.getMessage());
        return null;
    }
}


public Pessoa getPessoa(int id) {
    String sql = "SELECT * FROM pessoa WHERE id = ?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        // 1º parâmetro é o SQL
        // 2º parâmetro é o tipo do ResultSet - ResultSet scroll, ou seja, o cursor se move para frente ou para trás.
        // Este tipo de ResultSet é sensível às alterações feitas no banco de dados, ou seja, as modificações feitas no banco de dados são refletidas no ResultSet.
        // 3º parâmetro é sobre os parâmetros de concorrência – pode ser "read only" ou atualizável

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
        Pessoa p = new Pessoa(); // Preparo um objeto que vou armazenar a consulta
        // Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição da consulta
        // Em alguns casos, a consulta terá mais de um resultado de retorno
        rs.first();
        p.setId(id);
        p.setNome(rs.getString("nome"));
        p.setSexo(rs.getString("sexo"));
        p.setIdioma(rs.getString("idioma"));
        return p;

    } catch (SQLException ex) {
        System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
        return null;
    }
}

public List<Pessoa> getPessoas() {

    String sql = "SELECT * FROM pessoa";
    try {

        PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery(); // obtenho o retorno da consulta e armazeno no ResultSet
        List<Pessoa> listaPessoas = new ArrayList<>(); // Preparo uma lista de objetos que vou armazenar a consulta
        // Percorre rs e salvar as informações dentro de um objeto Pessoa e depois adiciona na lista
        while (rs.next()) {
            Pessoa p = new Pessoa();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setSexo(rs.getString("sexo"));
            p.setIdioma(rs.getString("idioma"));
            listaPessoas.add(p);
        }
        return listaPessoas;
    } catch (SQLException ex) {
        System.out.println("Erro ao consultar todas as pessoas: " + ex.getMessage());
        return null;
    }
}



}
