package mapper;

import domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findByCondition(User user);

    public List<User> findByIds(List<Integer> ids);
}
