package catalog.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/12/13
 * Time: 10:59 PM
 */
@Entity
@Table(name="ADMIN")
public class Admin {

    private String email;
    private String password;


    @Id
    @Pattern(regexp="[A-Za-z0-9.%_+-]+@[A-Za-z0-9.-]+.[A-Za-z0-9]{2,4}", message="Invalid email address.")
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    @Size(min=6, max=20, message="The password must be at least 6 characters long.")
    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
