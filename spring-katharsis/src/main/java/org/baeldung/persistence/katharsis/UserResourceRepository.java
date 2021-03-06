package org.nklkarthi.persistence.katharsis;

import io.katharsis.queryParams.RequestParams;
import io.katharsis.repository.ResourceRepository;

import org.nklkarthi.persistence.dao.UserRepository;
import org.nklkarthi.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResourceRepository implements ResourceRepository<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id, RequestParams params) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> findAll(RequestParams params) {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<Long> ids, RequestParams params) {
        return userRepository.findAll(ids);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
