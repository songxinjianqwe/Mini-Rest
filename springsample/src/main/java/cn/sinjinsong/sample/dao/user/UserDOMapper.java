package cn.sinjinsong.sample.dao.user;

import cn.sinjinsong.sample.domain.entity.user.UserDO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserDO record);

    
    
    UserDO findByUsername(@Param("username") String username);
    List<UserDO> findIdAndNameByUsernameContaining(@Param("username") String username);
    UserDO findByPhone(@Param("phone") String phone);
    Page<UserDO> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    String findAvatarById(@Param("id") Long id);
    UserDO findBriefInfoById(@Param("id") Long id);
    List<Long> findAllUserIds();
    UserDO findByEmail(@Param("email") String email);
}