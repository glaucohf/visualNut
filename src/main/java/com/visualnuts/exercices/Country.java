package com.visualnuts.exercices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {

	private String nome;
	private List<String> languages = new ArrayList<String>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(languages, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(languages, other.languages) && Objects.equals(nome, other.nome);
	}

}
