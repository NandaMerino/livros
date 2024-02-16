package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AutorModel;

public class AutorDAO {
	private Connection conexao;
	private PreparedStatement statement;

	public void salvar(AutorModel autor) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);

			String sql = "INSERT INTO tb_autor VALUES (?)";
			statement = conexao.prepareStatement(sql);

			statement.setString(1, autor.getNome_autor());

			statement.executeUpdate();

		} catch (Exception ex) {
			try {
				conexao.rollback();

			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();

		} finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		}
	}

	public void alterar(AutorModel autor) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);

			String sql = "UPDATE tb_autor SET nome_autor = ? WHERE id_autor = ?";
			statement = conexao.prepareStatement(sql);

			statement.setString(1, autor.getNome_autor());
			statement.setInt(2, autor.getId_autor());
			statement.executeUpdate();
		} catch (Exception ex) {
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public void deletar(int id_autor) {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);

			String sql = "DELETE FROM tb_autor WHERE id_autor = ?";
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id_autor);
			statement.executeUpdate();
		} catch (Exception ex) {
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	

	public List<AutorModel> listar() {
		try {
			ConexaoBD conexaoBD = new ConexaoBD();
			conexao = conexaoBD.getCon();
			conexao.setAutoCommit(false);

			String sql = "SELECT id_autor, nome_autor FROM tb_autor ORDER BY nome_autor";
			statement = conexao.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<AutorModel> listaAutores = new ArrayList<AutorModel>();
			while (result.next()) {
				AutorModel autor = new AutorModel();
				autor.setId_autor(result.getInt(1));
				autor.setNome_autor(result.getString(2));
				listaAutores.add(autor);
			}
			return listaAutores;
		} catch (Exception ex) {
			try {
				conexao.rollback();
			} catch (SQLException e) {
				ex.printStackTrace();
			}
			ex.printStackTrace();
			return null;
		} finally {
			try {
				conexao.setAutoCommit(true);
				statement.close();
				conexao.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
