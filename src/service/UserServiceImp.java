package service;

import dao.UserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import utils.MailUtils;
import utils.UUIDUtils;

import java.io.IOException;
import java.io.InputStream;

public class UserServiceImp implements UserService {
    private static SqlSession sqlSession =null;
    private static UserDao userDao=null;

    static {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过配置文件创建会话工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //从工厂中获取一个会话
        sqlSession = sessionFactory.openSession();
        userDao=sqlSession.getMapper(UserDao.class);
    }

    //确认订单时，根据用户名获取用户信息
    @Override
    public User getUserByName(String username) {

        return userDao.getUserByName(username);
    }

    //登陆
    @Override
    public int login(User user) {
        int login = userDao.login(user);
        return  login;
    }

    //激活用户
    @Override
    public Integer active(String code) {
        Integer active = userDao.active(code);
        sqlSession.commit();
        return active;
    }

    //注册
    @Override
    public int register(User user) {
        user.setState(0L);
        String uuid = UUIDUtils.getUUID();
        String uuid1 = UUIDUtils.getUUID();
        user.setCode(uuid.replaceAll("-",""));
        user.setUid(uuid1.replaceAll("-",""));
        int count = userDao.register(user);
        sqlSession.commit();
        if(count==1){
            MailUtils.sendMail(user.getEmail(),user.getCode());
        }
        return count;
    }

 //注册时查看用户名可用不
    @Override
    public Integer findUserByName(String name) {
        Integer count=userDao.findUserByName(name);
        sqlSession.commit();
        return  count;
    }
}
