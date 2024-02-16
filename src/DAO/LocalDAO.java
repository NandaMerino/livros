package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LocalModel;

public class LocalDAO {
	private Connection conexao;
	private PreparedStatement statement;
	
	
	public List<LocalModel>listar(){
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sql = "SELECT id_local, local FROM tb_local ORDER BY local";
			statement = conexao.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<LocalModel> listaLocais = new ArrayList<LocalModel>();
			while(result.next()) {
				LocalModel localModel = new LocalModel();
				localModel.setId_local(result.getInt(1));
				localModel.setLocal(result.getString(2));
				
				listaLocais.add(localModel);
				
			}
			return listaLocais;
			
		}catch(Exception ex) {
			try {
				conexao.rollback();
			}catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		}finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
