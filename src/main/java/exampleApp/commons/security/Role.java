package exampleApp.commons.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//strategia dodawania Id
    @Column(name = "role_id")
    private Integer roleId;

    private String role;

    @JsonIgnore // ignoruje seta gdzie miałem tagi, nie zapętlam się

    @ManyToMany
    private Set<UserApp> users = new HashSet<>();//hash set żeby po drodze nie zebrać nulla


}
