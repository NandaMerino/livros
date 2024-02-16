package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
	
	private String login = "---";
	private String senha = "---";
	private String nomeBase = "---";
	private String servidor = "---";
	
	public Connection getCon() throws Exception {
		Connection conexao = null;
		try {
			if(conexao == null || conexao.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String dados = servidor+"/"+nomeBase;
				conexao = DriverManager.getConnection("jdbc:mysql://"+dados+"?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC",login,senha);
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return conexao;
	}
}
