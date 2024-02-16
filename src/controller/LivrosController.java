package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.AutorDAO;
import DAO.LivrosDAO;
import DAO.EditoraDAO;
import DAO.LocalDAO;
import model.AutorModel;
import model.LivrosModel;
import model.EditoraModel;
import model.LocalModel;
import view.LivrosView;

public class LivrosController {
	private LivrosView view = new LivrosView();
	private List<AutorModel> listaAutor;
	private List<LivrosModel> listaLivros;
	private List<LocalModel> listaLocal;
	private List<EditoraModel> listaEditora;
	
	
	public LivrosController() {
		MouseListener ouvinte2 = new MouseListener(){
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == view.getTabela_livros()) {
					int linha = view.getTabela_livros().getSelectedRow();
					
					int id_livros = (int) view.getTabela_livros().getValueAt(linha,0);
					String titulo = (String) view.getTabela_livros().getValueAt(linha, 1);
			        String nome_autor = (String) view.getTabela_livros().getValueAt(linha, 2);
					String ano = (String) view.getTabela_livros().getValueAt(linha, 3);
			        String local = (String) view.getTabela_livros().getValueAt(linha, 4);
			        String nome_editora = (String) view.getTabela_livros().getValueAt(linha, 5); 
										
					view.getTextFieldIdAlterar().setText(String.format("%d",id_livros));
					view.getTextFieldTituloAlterar().setText(titulo);
					view.getComboBoxAutorAlterar().setSelectedItem(nome_autor);
					view.getTextFieldAnoAlterar().setText(ano);
					view.getComboBoxLocalAlterar().setSelectedItem(local);
					view.getComboBoxEditoraAlterar().setSelectedItem(nome_editora);
					
					view.getButtonAlterar().setEnabled(true);
					view.getButtonDeletar().setEnabled(true);
				}
			}
		};
		
		ActionListener ouvinte = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == view.getButtonSalvar()) {
					
					//SALVAR
					LivrosModel livrosModel = new LivrosModel();
					AutorModel autorModel = new AutorModel();
					EditoraModel editoraModel = new EditoraModel();
					LocalModel localModel = new LocalModel();
					
					livrosModel.setTitulo(view.getTextFieldTitulo().getText());
					
					//PARA LISTA COMBOBOX AUTOR
					int posicaoListaAutor = view.getComboBoxAutor().getSelectedIndex();
					int id_autor = listaAutor.get(posicaoListaAutor).getId_autor();
					autorModel.setId_autor(id_autor);
					
					livrosModel.setAno(view.getTextFieldAno().getText());
					
					//PARA LISTA COMBOBOX LOCAL
					int posicaoListaLocal = view.getComboBoxLocal().getSelectedIndex();
					int id_local = listaLocal.get(posicaoListaLocal).getId_local();
					localModel.setId_local(id_local);
					
					//PARA LISTA COMBOBOX EDITORA
					int posicaoListaEditora = view.getComboBoxEditora().getSelectedIndex();
					int id_editora = listaEditora.get(posicaoListaEditora).getId_editora();
					editoraModel.setId_editora(id_editora);
					
					
					LivrosDAO livrosDAO = new LivrosDAO();
					
					livrosModel.setAutor(autorModel);
					livrosModel.setEditora(editoraModel);
					livrosModel.setLocal(localModel);
					
					livrosDAO.salvar(livrosModel);
					
					JOptionPane.showMessageDialog(null, "Cadastro de livro realizado com sucesso!");
					List<LivrosModel>listaLivros = livrosDAO.listarLivros();
					view.carregarTabela(listaLivros, ouvinte2);
					view.limparCampos();
				}else if (e.getSource() == view.getButtonAlterar()) {
					
					//ALTERAR
					LivrosModel livrosModel = new LivrosModel();
					AutorModel autorModel = new AutorModel();
					EditoraModel editoraModel = new EditoraModel();
					LocalModel localModel = new LocalModel();
					
					String id_livros = view.getTextFieldIdAlterar().getText();
					if(!id_livros.equals("")) {
						livrosModel.setId_livros(Integer.parseInt(id_livros));
						livrosModel.setTitulo(view.getTextFieldTituloAlterar().getText());
						
						//PARA LISTA COMBOBOX AUTOR
						int posicaoListaAutor = view.getComboBoxAutorAlterar().getSelectedIndex();
						int id_autor = listaAutor.get(posicaoListaAutor).getId_autor();
						autorModel.setId_autor(id_autor);
					   
						livrosModel.setAno(view.getTextFieldAnoAlterar().getText());
					    
						//PARA LISTA COMBOBOX LOCAL
						int posicaoListaLocal = view.getComboBoxLocalAlterar().getSelectedIndex();
						int id_local = listaLocal.get(posicaoListaLocal).getId_local();
						localModel.setId_local(id_local);
						
						//PARA LISTA COMBOBOX EDITORA
						int posicaoListaEditora = view.getComboBoxEditoraAlterar().getSelectedIndex();
						int id_editora = listaEditora.get(posicaoListaEditora).getId_editora();
						editoraModel.setId_editora(id_editora);
						
					    LivrosDAO livrosDAO = new LivrosDAO();
					    livrosModel.setAutor(autorModel);
					    livrosModel.setLocal(localModel);
					    livrosModel.setEditora(editoraModel);
					    
					    livrosDAO.alterar(livrosModel);
					    
					    JOptionPane.showMessageDialog(null,"Cadastro de livro alterado com sucesso!");
					    List<LivrosModel> listaLivros = livrosDAO.listarLivros();
					    view.carregarTabela(listaLivros, ouvinte2);
					    view.limparCampos();
					}
					
				}else if (e.getSource() == view.getButtonDeletar()) {
					
					//DELETAR
					String id_livros = view.getTextFieldIdAlterar().getText();
					if(!id_livros.equals("")) {
						LivrosDAO livrosDAO = new LivrosDAO();
						livrosDAO.deletar(Integer.parseInt(id_livros));
						JOptionPane.showMessageDialog(null, "Cadastro de livro deletado com sucesso!");
						List<LivrosModel> listaLivros = livrosDAO.listarLivros();
						view.carregarTabela(listaLivros, ouvinte2);
						view.limparCampos();
					}
				}
			}
		};
		
		AutorDAO autorDAO = new AutorDAO();
		LocalDAO localDAO = new LocalDAO();
		EditoraDAO editoraDAO = new EditoraDAO(); 
		
		this.listaAutor = autorDAO.listar();
		this.listaLocal = localDAO.listar();
		this.listaEditora = editoraDAO.listar();
		
		view.confirgurarComponentes(ouvinte,listaAutor,listaEditora,listaLocal);
		
		LivrosDAO livrosDAO = new LivrosDAO();
		this.listaLivros = livrosDAO.listarLivros();
		view.carregarTabela(listaLivros, ouvinte2);
		
		
	}



}
