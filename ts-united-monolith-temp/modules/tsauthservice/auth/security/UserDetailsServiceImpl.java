package modules.tsauthservice.auth.security;
import java.text.MessageFormat;
import modules.tsauthservice.auth.constant.InfoConstant;
import modules.tsauthservice.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 *
 * @author fdse
 */
@Component("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("[loadUserByUsername][load UsernamePasswordAuthenticationToken][username: {}]", s);
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format(InfoConstant.USER_NAME_NOT_FOUND_1, s)));
    }
}