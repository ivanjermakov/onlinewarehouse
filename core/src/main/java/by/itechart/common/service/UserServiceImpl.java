package by.itechart.common.service;

import by.itechart.common.entity.User;
import by.itechart.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getUsers(Long companyId, Pageable pageable) {
        return userRepository.findAllByCompany_Id(companyId, pageable);
    }

    @Override
    public Long saveOrUpdateUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.setDeleted(userId);
    }
}
