package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EditoraModel;

public class EditoraDAO {
	private Connection conexao;
	private PreparedStatement statement;
	
	
	public void salvar(EditoraModel editora) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sql = "INSERT INTO tb_editora VALUES (?)";
			statement = conexao.prepareStatement(sql);
			
			statement.setString(1, editora.getNome_editora());
			
			statement.executeUpdate();
		}catch(Exception ex) {
			try {
				conexao.rollback();
			}catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
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
	
	
	public List<EditoraModel>listar(){
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sql = "SELECT id_editora, nome_editora FROM tb_editora ORDER BY nome_editora";
			statement = conexao.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<EditoraModel> listaEditoras = new ArrayList<EditoraModel>();
			while(result.next()) {
				EditoraModel editoraModel = new EditoraModel();
				editoraModel.setId_editora(result.getInt(1));
				editoraModel.setNome_editora(result.getString(2));
				
				listaEditoras.add(editoraModel);
				
			}
			return listaEditoras;
			
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
