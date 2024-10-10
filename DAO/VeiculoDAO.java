/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.Conexao;
import Principal.Pessoa;
import Principal.Veiculo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VeiculoDAO {
    private Conexao conexao;
    private Connection conn;

    public VeiculoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Veiculo veiculo) {
    String sql = "INSERT INTO veiculo (modelo, placa, id_pessoa) VALUES (?, ?, ?);";

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, veiculo.getModelo());
        stmt.setString(2, veiculo.getPlaca());
        stmt.setInt(3, veiculo.getPessoaid().getId());

        stmt.execute();
    } catch (SQLException ex) {
        System.out.println("Erro ao inserir Veiculo: " + ex.getMessage());
    }
}
    
    public void editar(Veiculo veiculo) {
    try {
        String sql = "UPDATE veiculo set modelo=?, placa=?, id_pessoa=? WHERE id=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, veiculo.getModelo());
        stmt.setString(2, veiculo.getPlaca());
        stmt.setInt(3, veiculo.getPessoaid().getId());
        stmt.setInt(4, veiculo.getId());

        stmt.execute();
    } catch (SQLException ex) {
        System.out.println("Erro ao atualizar Veiculo: " + ex.getMessage());
    }
}

    public void excluir(int id) {
    try {
        String sql = "delete from veiculo WHERE id=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.execute();
    } catch (SQLException ex) {
        System.out.println("Erro ao excluir veiculo: " + ex.getMessage());
    }
}
    
    public Veiculo getVeiculo(int id) {
    String sql = "SELECT * FROM veiculo WHERE id = ?";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        Veiculo veiculo = new Veiculo();
        
        if (rs.first()) {

            veiculo.setId(rs.getInt("id"));
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setPlaca(rs.getString("placa"));

            Pessoa pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id_pessoa"));
            veiculo.setPessoaid(pessoa); 
        } else {
            return null; 
        }

        return veiculo;

    } catch (SQLException ex) {
        System.out.println("Erro ao consultar veículo: " + ex.getMessage());
        return null;
    }
}

    

    

    
    
}
