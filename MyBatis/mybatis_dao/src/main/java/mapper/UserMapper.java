package mapper;

import domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    //与mapper配置文件对应
    public List<User> findAll() throws IOException;

    //与mapper配置文件对应
    public User findById(int id);
}
