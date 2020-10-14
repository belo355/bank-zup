package br.com.hackaton.zup.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.instrument.ClassDefinition;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private LocalDateTime dateBirth;
    private String cpf;

    public Prospect(String name, String lastName, String email, LocalDateTime dateBirth, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return Objects.equals(id, prospect.id) &&
                Objects.equals(name, prospect.name) &&
                Objects.equals(lastName, prospect.lastName) &&
                Objects.equals(email, prospect.email) &&
                Objects.equals(dateBirth, prospect.dateBirth) &&
                Objects.equals(cpf, prospect.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, dateBirth, cpf);
    }
}
