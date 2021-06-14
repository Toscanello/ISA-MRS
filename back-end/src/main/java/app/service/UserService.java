package app.service;

import app.domain.Patient;
import app.domain.User;
import app.dto.PatientDTO;
import app.dto.UserDTO;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user, UserDTO editedUser)
    {
        user.setName(editedUser.getName());
        user.setSurname(editedUser.getSurname());
        user.setAddress(editedUser.getAddress());
        user.setPhoneNumber(editedUser.getPhoneNumber());
        if(!user.getPassword().equals(editedUser.getPassword()))
            user.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User saveUser(User u) {
        return userRepository.save(u);
    }

    @Transactional
    public User getAndIncrement(String email) {
        return userRepository.getAndIncrementVersion(email);
    }
}
