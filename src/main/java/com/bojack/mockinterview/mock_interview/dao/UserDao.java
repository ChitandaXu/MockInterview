package com.bojack.mockinterview.mock_interview.dao;

import com.bojack.mockinterview.mock_interview.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    String TABLE_NAME = "users";
    String INSET_FIELDS = " userId, userName, age, gender, password, iaManager, createDate ";
    String SELECT_FIELDS = "userId, userName, age, gender, password, iaManager, createDate ";

    List<User> findUserByName(String name);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    //@Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    //User selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where userName=#{userName}"})
    User selectByName(String userName);

    @Update({"update ", TABLE_NAME, " set password=#{password} where userId=#{userId}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where userId=#{userId}"})
    void deleteById(int id);

    @Select("select * from users")
    public List<User> ListUser();
    //@Insert("")
    public int insertUser(User user);
    @Delete({"delete from ", TABLE_NAME, " where userId=#{userId}"})
    public int delete(int userId);

    public int Update(User user);
    //@Select("select * from users where userName=#{userName}")
    //User selectByName(String userName);
}