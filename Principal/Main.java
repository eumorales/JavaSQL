package Principal;

import Conexao.Conexao;
import DAO.PessoaDAO;
import java.sql.Connection;


public class Main {
    
    public static void main(String[] args) {
        
        Connection conn = Conexao.getConexao();

        if (conn != null) {
            System.out.println("Conexão estabelecida.");
        } else {
            System.out.println("Falha ao conectar.");
        }
        
        Pessoa p = new Pessoa();
        p.setNome("Rogerinho");
        p.setSexo("M");
        p.setIdioma("PT");
        
        PessoaDAO pDAO = new PessoaDAO();
        pDAO.inserir(p);
        
        boolean bol = pDAO.checarPessoa("Ricardo");
        if (bol) {
            System.out.println("A pessoa foi inserida com sucesso na tabela.");
        } else {
            System.out.println("A pessoa não foi inserida na tabela.");
        }
        
        
    }
}
