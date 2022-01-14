package monpetitbonsai.authentication.exposition;

import monpetitbonsai.authentication.UserMapper;
import monpetitbonsai.authentication.domain.AppUser;
import monpetitbonsai.authentication.domain.UserService;
import monpetitbonsai.commons.AuthorityType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(UserMapper.toUserDto(userService.create(UserMapper.toUserCreationRequest(userDto))), HttpStatus.CREATED);
    }

    @PutMapping("/me/password")
    public ResponseEntity<UserDto> updatePassword(@RequestBody String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof AppUser) {
            AppUser credentials = (AppUser) authentication.getPrincipal();
            if (credentials != null) {
                return userService.updatePassword(credentials.getId(), newPassword)
                        .map(u -> ResponseEntity.ok(UserMapper.toUserDto(u)))
                        .orElse(ResponseEntity.notFound().build());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}/authority")
    public ResponseEntity<UserDto> updateAuthority(@PathVariable UUID id, @RequestBody String newAuthority) {
        try {
            AuthorityType at = AuthorityType.valueOf(newAuthority);
            return userService.updateAuthority(id, at)
                    .map(u -> ResponseEntity.ok(UserMapper.toUserDto(u)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof AppUser) {
            AppUser credentials = (AppUser) authentication.getPrincipal();
            if (credentials != null) {
                Optional<UserDto> user = userService.getById(credentials.getId()).map(UserMapper::toUserDto);
                if (user.isPresent()) {
                    return ResponseEntity.ok(user.get());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
