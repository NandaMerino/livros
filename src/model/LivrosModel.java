package model;

public class LivrosModel {
	
	private int id_livros;
	private String titulo;
	private String ano;
	private AutorModel autor;
	private EditoraModel editora;
	private LocalModel local;

	public AutorModel getAutor() {
		return autor;
	}

	public void setAutor(AutorModel autor) {
		this.autor = autor;
	}

	public int getId_livros() {
		return id_livros;
	}
	
	public void setId_livros(int id_livros) {
		this.id_livros = id_livros;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}

	public EditoraModel getEditora() {
		return editora;
	}

	public void setEditora(EditoraModel editora) {
		this.editora = editora;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}
	
	

}
