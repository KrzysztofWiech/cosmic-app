package exampleApp.models;


import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor//gdy mamy buildera to tej adnotacji nie potrzebujemy
@AllArgsConstructor//gdy mamy buildera to tej adnotacji nie potrzebujemy
//@Data robi nam gett sett toString hashCode Equals
@Data
//@Builder wzorzec projektowy, tworzy nam nową klasę która posiada metody nazwane tak samo jak zmienne
@Builder
//Entity baza danych mapuje na encje bazy danych
@Entity
//@Table tworzy nam tabelę o nazwie planets
@Table(name = "planets")
public class Planet {

    //klucz główny, sztywna tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //kolumny, unique - unikalny, nulltable - nie może być pusta
    @Column(name = "planet_name", unique = true, nullable = false)
    private String planetName;

    @Column(name = "distance_from_sun")
    private long distanceFromSun;

    @Column(name = "one_way_light_time_to_the_sun")
    private double oneWayLightTimeToTheSun;

    @Column(name = "length_of_year")
    private long lengthOfYear;

    @Column(name = "planet_type")
    private String planetType;

    @Column(name = "planet_info")
    private String planetInfo;

    @Column(name = "planet_image")
    private String planetImage;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, //usuwa tag, zostawia planetę
            CascadeType.MERGE
    })


    @JoinTable(
            name = "planet_tag",
            joinColumns = @JoinColumn(name = "planet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

}
