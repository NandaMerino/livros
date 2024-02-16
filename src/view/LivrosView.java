package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.AutorModel;
import model.LivrosModel;
import model.EditoraModel;
import model.LocalModel;

public class LivrosView extends JFrame{
	
	private JTable tabela_livros;
	private JScrollPane panel_livros;
	private JPanel panelSalvar = new JPanel();
	private JPanel panelAlterarDeletar = new JPanel();
	
	//PANEL SALVAR
	private JLabel labelTitulo = new JLabel("Titulo:");
	private JTextField textFieldTitulo = new JTextField(15);
	
	private JLabel labelIdAutor = new JLabel("Autor:");
	private JComboBox comboBoxAutor = new JComboBox();
	
	private JLabel labelAno = new JLabel("Ano:");
	private JTextField textFieldAno = new JTextField(2);
	
	private JLabel labelIdLocal = new JLabel("Localização:");
	private JComboBox comboBoxLocal = new JComboBox();
	
	private JLabel labelIdEditora = new JLabel("Editora:");
	private JComboBox comboBoxEditora = new JComboBox();
	
	private JButton buttonSalvar = new JButton("Salvar");

	
	//PANEL ALTERAR E DELETAR
	private JLabel labelIdAlterar = new JLabel("ID do titulo:");
	public JTable getTabela_livros() {
		return tabela_livros;
	}


	public void setTabela_livros(JTable tabela_livros) {
		this.tabela_livros = tabela_livros;
	}
	
	private JTextField textFieldIdAlterar = new JTextField(13);
	
	private JLabel labelTituloAlterar = new JLabel("Titulo:");
	private JTextField textFieldTituloAlterar = new JTextField(15);
	
	private JLabel labelIdAutorAlterar = new JLabel("Autor:");
	private JComboBox comboBoxAutorAlterar = new JComboBox();
	
	private JLabel labelAnoAlterar = new JLabel("Ano:");
	private JTextField textFieldAnoAlterar = new JTextField(3);
	
	private JLabel labelIdLocalAlterar = new JLabel("Localização:");
	private JComboBox comboBoxLocalAlterar = new JComboBox();
	
	private JLabel labelIdEditoraAlterar = new JLabel("Editora:");
	private JComboBox comboBoxEditoraAlterar = new JComboBox();
	
	private JButton buttonAlterar = new JButton("Alterar");
	private JButton buttonDeletar = new JButton("Deletar");
	
	public LivrosView() {
		this.setSize(800,500);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void confirgurarComponentes(ActionListener ouvinte, List<AutorModel> listaAutor, List<EditoraModel> listaEditora, List<LocalModel> listaLocal) {
		
		//PANEL SALVAR
		panelSalvar.add(labelTitulo);
		panelSalvar.add(textFieldTitulo);
		
		
		//AUTOR
		panelSalvar.add(labelIdAutor);
		for(int i=0; i<listaAutor.size();i++) {
			
			//COMBO BOX AUTOR
			comboBoxAutor.addItem(listaAutor.get(i).getNome_autor()); 
			comboBoxAutorAlterar.addItem(listaAutor.get(i).getNome_autor()); 	
		}
		panelSalvar.add(comboBoxAutor);
		
		
		//ANO
		panelSalvar.add(labelAno);
		panelSalvar.add(textFieldAno);
		
		
		//LOCAL
		panelSalvar.add(labelIdLocal);		
		for(int i=0; i<listaLocal.size();i++) {
			
			//COMBO BOX LOCAL
			comboBoxLocal.addItem(listaLocal.get(i).getLocal());
			comboBoxLocalAlterar.addItem(listaLocal.get(i).getLocal());	
		}
		panelSalvar.add(comboBoxLocal);
		
		//EDITORA
		panelSalvar.add(labelIdEditora);
		for (int i=0;i<listaEditora.size();i++) {
		
			//COMBO BOX EDITORA
			comboBoxEditora.addItem(listaEditora.get(i).getNome_editora());
			comboBoxEditoraAlterar.addItem(listaEditora.get(i).getNome_editora());
		}
		panelSalvar.add(comboBoxEditora);
		
		buttonSalvar.addActionListener(ouvinte);
		panelSalvar.add(buttonSalvar);
		
		panelSalvar.setPreferredSize(new Dimension(275,300));
		this.add(panelSalvar);
		
		
		//PANEL ALTERAR E DELETAR
		panelAlterarDeletar.add(labelIdAlterar);
		panelAlterarDeletar.add(textFieldIdAlterar);
		
		panelAlterarDeletar.add(labelTituloAlterar);
		panelAlterarDeletar.add(textFieldTituloAlterar);
		
		panelAlterarDeletar.add(labelIdAutorAlterar);
		panelAlterarDeletar.add(comboBoxAutorAlterar);
		
		panelAlterarDeletar.add(labelAnoAlterar);
		panelAlterarDeletar.add(textFieldAnoAlterar);
		
		panelAlterarDeletar.add(labelIdLocalAlterar);
		panelAlterarDeletar.add(comboBoxLocalAlterar);
		
		panelAlterarDeletar.add(labelIdEditoraAlterar);
		panelAlterarDeletar.add(comboBoxEditoraAlterar);
		
		buttonAlterar.addActionListener(ouvinte);
		buttonDeletar.addActionListener(ouvinte);
		panelAlterarDeletar.add(buttonAlterar);
		panelAlterarDeletar.add(buttonDeletar);
		
		panelAlterarDeletar.setPreferredSize(new Dimension(275,300));
		this.add(panelAlterarDeletar);
		
		this.limparCampos();
		
	}
	
	public void carregarTabela(List<LivrosModel> listaLivros, MouseListener ouvinte2) {
		if(panel_livros != null)
			this.remove(panel_livros);
		String[] nomesColunas = new String[] {
				"ID", "Titulo", "Autor", "Ano", "Localização", "Editora"
		};
		
		Object[][] dados = new Object[listaLivros.size()][6];
		for(int i=0; i<listaLivros.size(); i++) {
			dados[i][0] = listaLivros.get(i).getId_livros();
			dados[i][1] = listaLivros.get(i).getTitulo();
			dados[i][2] = listaLivros.get(i).getAutor().getNome_autor();
			dados[i][3] = listaLivros.get(i).getAno();
			dados[i][4] = listaLivros.get(i).getLocal().getLocal();
			dados[i][5] = listaLivros.get(i).getEditora().getNome_editora();
		}
		
		tabela_livros = new JTable(dados,nomesColunas);
		panel_livros = new JScrollPane(tabela_livros);
		panel_livros.setPreferredSize(new Dimension(520,115));
		this.tabela_livros.addMouseListener(ouvinte2);
		this.add(panel_livros);
		this.revalidate();
	}
	
	public void limparCampos() {
		
		//LIMPAR TEXT FIELD ALTERAR E DELETAR
		this.textFieldIdAlterar.setText("");
		this.textFieldTituloAlterar.setText("");
		this.textFieldAnoAlterar.setText("");

		
		//LIMPAR TEXT FIELD SALVAR
		this.textFieldTitulo.setText("");
		this.textFieldAno.setText("");

		//LIMPAR COMBOBOX SALVAR
		this.comboBoxAutor.setSelectedIndex(-1); 
	    this.comboBoxLocal.setSelectedIndex(-1);
	    this.comboBoxEditora.setSelectedIndex(-1);

	    //LIMPAR COMBOBOX ALTERAR E DELETAR
	    this.comboBoxAutorAlterar.setSelectedIndex(-1); 
	    this.comboBoxLocalAlterar.setSelectedIndex(-1);
	    this.comboBoxEditoraAlterar.setSelectedIndex(-1);
	    
		//PARA OS BOTÕES
		this.buttonAlterar.setEnabled(false);
		this.buttonDeletar.setEnabled(false);
	}

	public JComboBox getComboBoxAutorAlterar() {
		return comboBoxAutorAlterar;
	}


	public void setComboBoxAutorAlterar(JComboBox comboBoxAutorAlterar) {
		this.comboBoxAutorAlterar = comboBoxAutorAlterar;
	}


	public JComboBox getComboBoxEditora() {
		return comboBoxEditora;
	}


	public void setComboBoxEditora(JComboBox comboBoxEditora) {
		this.comboBoxEditora = comboBoxEditora;
	}


	public JComboBox getComboBoxEditoraAlterar() {
		return comboBoxEditoraAlterar;
	}


	public void setComboBoxEditoraAlterar(JComboBox comboBoxEditoraAlterar) {
		this.comboBoxEditoraAlterar = comboBoxEditoraAlterar;
	}


	public JComboBox getComboBoxLocal() {
		return comboBoxLocal;
	}


	public void setComboBoxLocal(JComboBox comboBoxLocal) {
		this.comboBoxLocal = comboBoxLocal;
	}


	public JComboBox getComboBoxLocalAlterar() {
		return comboBoxLocalAlterar;
	}


	public void setComboBoxLocalAlterar(JComboBox comboBoxLocalAlterar) {
		this.comboBoxLocalAlterar = comboBoxLocalAlterar;
	}


	public JComboBox getComboBoxAutor() {
		return comboBoxAutor;
	}


	public void setComboBoxAutor(JComboBox comboBoxAutor) {
		this.comboBoxAutor = comboBoxAutor;
	}


	public JTextField getTextFieldTitulo() {
		return textFieldTitulo;
	}


	public void setTextFieldTitulo(JTextField textFieldTitulo) {
		this.textFieldTitulo = textFieldTitulo;
	}

	public JTextField getTextFieldAno() {
		return textFieldAno;
	}


	public void setTextFieldAno(JTextField textFieldAno) {
		this.textFieldAno = textFieldAno;
	}

	public JButton getButtonSalvar() {
		return buttonSalvar;
	}


	public void setButtonSalvar(JButton buttonSalvar) {
		this.buttonSalvar = buttonSalvar;
	}


	public JTextField getTextFieldIdAlterar() {
		return textFieldIdAlterar;
	}


	public void setTextFieldIdAlterar(JTextField textFieldIdAlterar) {
		this.textFieldIdAlterar = textFieldIdAlterar;
	}


	public JTextField getTextFieldTituloAlterar() {
		return textFieldTituloAlterar;
	}


	public void setTextFieldTituloAlterar(JTextField textFieldTituloAlterar) {
		this.textFieldTituloAlterar = textFieldTituloAlterar;
	}

	public JTextField getTextFieldAnoAlterar() {
		return textFieldAnoAlterar;
	}


	public void setTextFieldAnoAlterar(JTextField textFieldAnoAlterar) {
		this.textFieldAnoAlterar = textFieldAnoAlterar;
	}
	

	public JButton getButtonAlterar() {
		return buttonAlterar;
	}


	public void setButtonAlterar(JButton buttonAlterar) {
		this.buttonAlterar = buttonAlterar;
	}


	public JButton getButtonDeletar() {
		return buttonDeletar;
	}


	public void setButtonDeletar(JButton buttonDeletar) {
		this.buttonDeletar = buttonDeletar;
	}
	
	
	
	

}
