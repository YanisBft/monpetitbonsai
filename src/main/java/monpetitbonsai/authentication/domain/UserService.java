package monpetitbonsai.authentication.domain;

import monpetitbonsai.authentication.infrastructure.*;
import monpetitbonsai.commons.AuthorityType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity create(UserCreationRequest userCreationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreationRequest.getUsername());
        if (userCreationRequest.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        }
        UserEntity savedUser = userDao.save(userEntity);

        List<AuthorityEntity> authorities = new ArrayList<>();
        authorities.add(new AuthorityEntity(AuthorityId.getDefaultAuthority(savedUser.getId())));
        savedUser.setAuthorities(authorities);

        return userDao.save(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(s);
        List<String> authoritiesList = userDao.findAuthorityByUserId(user.getId());
        String authorities = String.join(",", authoritiesList);
        return new AppUser(user.getId(), user.getUsername(), user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    public List<UserEntity> getAll() {
        return userDao.findAll();
    }

    public Optional<UserEntity> getById(UUID id) {
        return userDao.findById(id);
    }

    public Optional<UserEntity> updatePassword(UUID id, String newPassword) {
        Optional<UserEntity> user = userDao.findById(id);
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            return Optional.of(userDao.save(user.get()));
        }
        return user;
    }

    public Optional<UserEntity> updateAuthority(UUID id, AuthorityType newAuthority) {
        Optional<UserEntity> user = userDao.findById(id);
        if (user.isPresent()) {
            user.get().getAuthorities().clear();
            user.get().getAuthorities().add(new AuthorityEntity(new AuthorityId(id, newAuthority.name())));
            return Optional.of(userDao.save(user.get()));
        }
        return user;
    }
}
