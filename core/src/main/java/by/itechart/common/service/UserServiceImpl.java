package by.itechart.common.service;

import by.itechart.common.dto.AuthorityDto;
import by.itechart.common.dto.CreateUserDto;
import by.itechart.common.dto.UserDto;
import by.itechart.common.dto.UserFilter;
import by.itechart.common.entity.Address;
import by.itechart.common.entity.Authority;
import by.itechart.common.entity.User;
import by.itechart.common.repository.AuthorityRepository;
import by.itechart.common.repository.UserRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.common.utils.PasswordGenerator;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import by.itechart.mail.service.MailService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final MailService mailService;
    private final AddressService addressService;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, MailService mailService, AddressService addressService,
                           AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.addressService = addressService;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Page<UserDto> getUsers(Long companyId, UserFilter userFilter, Pageable pageable) {
        return userRepository.findAll(UserPredicate.findFilter(companyId, userFilter), pageable)
                .map(user -> ObjectMapperUtils.map(user, UserDto.class));
    }

    @Override
    public Long saveUser(User user) {
        Long id = userRepository.save(user).getId();
        LOGGER.info("User was created/updated with id: {}", id);

        return id;
    }

    @Override
    public Long saveUser(long companyId, CreateUserDto createUserDto) {
        Long addressId = addressService.saveAddress(createUserDto.getAddress());
        User user = ObjectMapperUtils.map(createUserDto, User.class);

        List<Authority> persistentAuthoritiesList = getAllAuthorities();
        List<Authority> userPersistentAuthoritiesList = new ArrayList<>();
        user.getAuthorities().forEach(authority -> {
            persistentAuthoritiesList.forEach(persistentAuthority -> {
                if (authority.getName().equals(persistentAuthority.getName())) {
                    userPersistentAuthoritiesList.add(persistentAuthority);
                }
            });
        });
        user.setAuthorities(userPersistentAuthoritiesList);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String originalPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(originalPassword));
        user.setCompany(new Company(companyId));
        user.setAddress(new Address(addressId));
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        Long userId = saveUser(user);
        String subject = "\"Online warehouse\" account";
        String createUserEmailMessage = "\"Online warehouse\"\n" +
                "Your account has been successfully created.\n" +
                "Login: " + user.getUsername() + " \n" +
                "Password:" + originalPassword;
        mailService.send(user.getEmail(), subject, createUserEmailMessage);
        return userId;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundEntityException("User"));
    }

    @Override
    public UserDto getUser(Long userId, Long companyId) {
        User user = userRepository.findUserByCompany_IdAndIdAndDeletedIsNull(companyId, userId);
        if (user == null) {
            throw new NotFoundEntityException("User");
        }
        UserDto userDto = ObjectMapperUtils.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public Set<User> getUsersWithBirthday(Long companyId, LocalDate birthDay) {
        LOGGER.info("Find users by birthday: {}", birthDay);
        return userRepository.getUsersWithBirthday(companyId, birthDay);
    }

    @Override
    public Set<User> getAllById(List<Long> id) {
        return userRepository.findAllById(id);
    }

    @Override
    public Boolean canUseUsername(String username) {
        User userByUsername = userRepository.findUserByUsername(username);
        return userByUsername == null;
    }

    @Override
    public Long changeUserAuthorities(Long userId, Long companyId, List<AuthorityDto> authorities) {
        List<Authority> persistentAuthoritiesList = getAllAuthorities();
        List<Authority> userPersistentAuthoritiesList = new ArrayList<>();
        authorities.forEach(authority -> {
            persistentAuthoritiesList.forEach(persistentAuthority -> {
                if (authority.getName().equals(persistentAuthority.getName())) {
                    userPersistentAuthoritiesList.add(persistentAuthority);
                }
            });
        });
        User user = userRepository.findUserByCompany_IdAndIdAndDeletedIsNull(companyId, userId);
        user.setAuthorities(userPersistentAuthoritiesList);
        return userRepository.save(user).getId();
    }

    @Override
    public Long changeEnabledValue(long userId, long companyId) {
        User user = userRepository.findUserByCompany_IdAndIdAndDeletedIsNull(companyId, userId);
        user.setEnabled(!user.getEnabled());
        return userRepository.save(user).getId();
    }

    @Override
    public Long setDeleted(long userId, long companyId) {
        userRepository.setDeleted(userId, companyId);
        return userId;
    }

    @Override
    public Long resetPassword(long userId, long companyId) {
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(8);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = userRepository.findUserByCompany_IdAndIdAndDeletedIsNull(companyId, userId);
        user.setPassword(encodedPassword);
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);
        String subject = "\"Online warehouse\" account";
        String createUserEmailMessage = "\"Online warehouse\"\n" +
                "Password for your account has been successfully changed.\n" +
                "New password:" + password;
        mailService.send(user.getEmail(), subject, createUserEmailMessage);
        return userId;
    }

    private List<Authority> getAllAuthorities() {
        return Lists.newArrayList(authorityRepository.findAll());
    }
}
