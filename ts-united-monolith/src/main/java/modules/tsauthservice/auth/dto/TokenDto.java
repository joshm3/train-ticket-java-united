package modules.tsauthservice.auth.dto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * create token and back to user
 *
 * @author fdse
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto implements Serializable {
    private static final long serialVersionUID = 8460179745119402516L;

    private String userId;

    private String username;

    private String token;
}