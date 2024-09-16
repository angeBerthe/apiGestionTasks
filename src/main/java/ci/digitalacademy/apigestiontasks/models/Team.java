package ci.digitalacademy.apigestiontasks.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameTeam;

    private String description;

    @Column(unique = true)
    private String slug;

    @OneToMany(mappedBy = "team")
    private Set<Tasks> tasks;

    @OneToMany(mappedBy = "team")
    private Set<Member> members;

    @ManyToOne
    private Project project;

}
