package org.java.spring.db.pojo;

import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Il nome non può essere vuoto")
	@Length(min = 5, max = 60, message = "Il nome deve essere almeno 5 caratteri e massimo 60")
	private String nome;
	
	@NotEmpty(message = "La descrizione non può essere vuota")
	@Column(columnDefinition = "TEXT")
	@Length(min = 5, max = 300, message = "La descrizione deve essere almeno 5 caratteri e massimo 300")
	private String descrizione;
	
	@NotEmpty(message = "Il link dell'immagine non può essere vuoto")
	@Column(columnDefinition = "TEXT")
	@Length(max = 300, message = "Il link immagine non può superare i 300 caratteri")
	private String foto;
	
	@NotNull(message = "Il prezzo non può essere vuoto")
	@Min(value = 1, message = "Il prezzo deve essere maggiore o uguale a 1")
	private Double prezzo;
	
	public Pizza() {};
	public Pizza(String nome, String descrizione, String foto, Double prezzo) {
		
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		
		return getId() + getNome() + getDescrizione();
	}
}
