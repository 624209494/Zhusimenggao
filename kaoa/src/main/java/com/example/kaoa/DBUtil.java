package com.example.kaoa;

import com.example.kaoa.fragments.Food;
import com.jy.zsm.db.DaoSession;
import com.jy.zsm.db.UserDao;

import java.util.List;

public class DBUtil {

    private static DaoSession session = MyApplication.getSession();

    public static void insert(User user) {
        User queryid = queryid(user.getTitle());
        if (queryid == null){
            session.insert(user);
        }
    }
    public static User queryid(String title) {
        User unique = session.queryBuilder(User.class)
                .where(UserDao.Properties.Title.eq(title))
                .build().unique();
        return unique;
    }
    public static List<User> queryAll() {
        List<User> users = session.loadAll(User.class);
        return users;
    }

    public static void delete(User user) {
        User queryid = queryid(user.getTitle());
        if (queryid!=null){
            session.delete(user);
        }


    }

}
