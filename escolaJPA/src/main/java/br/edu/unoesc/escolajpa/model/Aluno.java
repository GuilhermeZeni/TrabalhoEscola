package br.edu.unoesc.escolajpa.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@ManyToOne
	private Curso curso;

	
	@ManyToMany // (cascade = CascadeType.ALL, fetch =   FetchType.EAGER)
@JoinTable(name = "alunos_disciplina",
   joinColumns = @JoinColumn(name = "id_aluno"),
   inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
private List<Disciplina> disciplinasAluno;

	public Aluno(String nome, Curso curso, List<Disciplina> disciplinasAluno) {
		this.nome = nome;
		this.curso = curso;
		this.disciplinasAluno = disciplinasAluno;
	}

	public Aluno(String nome, Curso curso) {
		this.nome = nome;
		curso.adicionarAluno(this);
	}

	public Aluno(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Aluno(String nome) {
		this.nome = nome;
	}

	public Aluno() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Disciplina> getdisciplinasAluno() {
		return disciplinasAluno;
	}

	public void setDisciplinasAluno(List<Disciplina> disciplinasAluno) {
		this.disciplinasAluno = disciplinasAluno;
	}
	
	public void adicionarDisciplina(Disciplina disciplina) {
		if (disciplina != null && !this.getdisciplinasAluno().contains(disciplina)) {
			this.disciplinasAluno.add(disciplina);
			if (!disciplina.getAlunos().contains(this)) {
				disciplina.getAlunos().add(this);
			}
		}
	}


	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", curso=" + curso + ", disciplinasAluno=" + disciplinasAluno
				+ "]";
	}
}