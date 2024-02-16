package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AutorModel;
import model.LivrosModel;
import model.EditoraModel;
import model.LocalModel;

public class LivrosDAO {
	private Connection conexao;
	private PreparedStatement statement;
	
	public void salvar(LivrosModel livrosModel) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sqlInsert = "INSERT INTO tb_livros (titulo, id_autor, ano, id_local, id_editora) VALUES(?,?,?,?,?)";
			statement = conexao.prepareStatement(sqlInsert);
			
			statement.setString(1,livrosModel.getTitulo());
			statement.setInt(2,livrosModel.getAutor().getId_autor());
			statement.setString(3,livrosModel.getAno());
			statement.setInt(4,livrosModel.getLocal().getId_local());
			statement.setInt(5,livrosModel.getEditora().getId_editora());
			
			statement.executeUpdate();
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			}
			catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace(); 
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void alterar(LivrosModel livrosModel) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
				conexao = conexaoBD.getCon();
				conexao.setAutoCommit(false);
				
				String sqlUpdate = "UPDATE tb_livros SET titulo = ?, id_autor = ?, ano = ?, id_local = ?, id_editora = ? WHERE id_livros = ?";
				statement = conexao.prepareStatement(sqlUpdate);
				
				statement.setString(1, livrosModel.getTitulo());
				statement.setInt(2, livrosModel.getAutor().getId_autor());
				statement.setString(3,livrosModel.getAno());
				statement.setInt(4, livrosModel.getLocal().getId_local());
				statement.setInt(5, livrosModel.getEditora().getId_editora());
				statement.setInt(6, livrosModel.getId_livros());
				
				statement.executeUpdate();			
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			}
			catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void deletar(int id_livros) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sqlDelete = "DELETE FROM tb_livros WHERE id_livros = ?";
			statement = conexao.prepareStatement(sqlDelete);
			
			statement.setInt(1, id_livros);
			
			statement.executeUpdate();
			
		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			}
			catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public List<LivrosModel> listarLivros(){
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);
			
			String sqlSelect = "SELECT l.id_livros, l.titulo, a.nome_autor, a.id_autor, l.ano, lo.local, lo.id_local, e.nome_editora, e.id_editora "
					+ " FROM tb_livros AS l LEFT JOIN tb_autor AS a ON (a.id_autor = l.id_autor) "
					+ "LEFT JOIN tb_local AS lo ON (lo.id_local = l.id_local) "
					+ "LEFT JOIN tb_editora AS e ON (e.id_editora = l.id_editora) "
					+ "ORDER BY l.titulo";
			statement = conexao.prepareStatement(sqlSelect);
			
			ResultSet result = statement.executeQuery();
			
			List<LivrosModel> listaLivros = new ArrayList<LivrosModel>();
			while(result.next()) {
				
				LivrosModel livrosModel = new LivrosModel();
				AutorModel autor = new AutorModel();
				EditoraModel editora = new EditoraModel();
				LocalModel local = new LocalModel();
				
				livrosModel.setId_livros(result.getInt(1)); //COLUNA ID_LIVROS
				livrosModel.setTitulo(result.getString(2)); //COLUNA TITULO
				
				autor.setNome_autor(result.getString(3)); //COLUNA AUTOR
				autor.setId_autor(result.getInt(4)); //COLUNA AUTOR
				
				livrosModel.setAno(result.getString(5)); //COLUNA ANO
				
				local.setLocal(result.getString(6)); //COLUNA LOCAL
				local.setId_local(result.getInt(7)); //COLUNA LOCAL
				
				editora.setNome_editora(result.getString(8)); //COLUNA EDITORA
				editora.setId_editora(result.getInt(9)); //COLUNA EDITORA
				
				livrosModel.setAutor(autor);
				livrosModel.setLocal(local);
				livrosModel.setEditora(editora);

				listaLivros.add(livrosModel);
				
			}
			return listaLivros;

		}
		catch(Exception ex) {
			try {
				conexao.rollback();
			}
			catch(SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		}
		finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
