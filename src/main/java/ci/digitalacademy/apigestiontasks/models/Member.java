package ci.digitalacademy.apigestiontasks.models;


import ci.digitalacademy.apigestiontasks.enumeration.Gender;
import ci.digitalacademy.apigestiontasks.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Team team;


}
