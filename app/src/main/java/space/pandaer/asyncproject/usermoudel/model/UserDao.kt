package space.pandaer.asyncproject.usermoudel.model

import androidx.room.*

@Dao
interface  UserDao {
    //添加用户
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user:User)
    //修改用户
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    //删除用户
    @Delete
    fun deleteUser(user: User)

    //查询全部用户
    @Query("SELECT * FROM user")
    fun getAllUserList() : List<User>

    //根据账号查询用户
    @Query("SELECT * FROM user WHERE account = :account")
    fun getSingleUser(account:String) : User

}