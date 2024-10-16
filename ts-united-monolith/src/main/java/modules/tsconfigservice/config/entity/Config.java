package modules.tsconfigservice.config.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
/**
 *
 * @author fdse
 */
@Data
@Entity
public class Config {
    @Valid
    @Id
    @NotNull
    private String name;

    @Valid
    @NotNull
    private String value;

    @Valid
    private String description;

    public Config() {
        this.name = "";
        this.value = "";
    }

    public Config(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }
}