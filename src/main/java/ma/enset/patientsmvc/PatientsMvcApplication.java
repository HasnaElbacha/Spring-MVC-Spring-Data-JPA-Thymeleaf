package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
  /*  @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Yassine",new Date(),false,182));
            patientRepository.save(new Patient(null,"Mohammed",new Date(),true,122));
            patientRepository.save(new Patient(null,"Nada",new Date(),true,112));
            patientRepository.save(new Patient(null,"Hanan",new Date(),false,412));

        };
    }*/

    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args ->{
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3==null)
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
            );


        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
