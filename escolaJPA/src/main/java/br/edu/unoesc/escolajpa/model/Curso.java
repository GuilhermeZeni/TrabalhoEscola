package br.edu.unoesc.escolajpa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@OneToMany (mappedBy = "curso")
	private Set<Aluno>alunos = new 
                       HashSet<Aluno>();

	public Curso(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Curso(String nome) {
		this.nome = nome;
	}

	public Curso() {
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
	
	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void adicionarAluno(Aluno aluno) {
		aluno.setCurso(this);
		this.alunos.add(aluno);
	}


	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + "]";
	}
}

