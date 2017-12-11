package cn.sinjinsong.sample.service.user.impl;

import cn.sinjinsong.sample.dao.user.RoleDOMapper;
import cn.sinjinsong.sample.dao.user.UserDOMapper;
import cn.sinjinsong.sample.domain.entity.user.UserDO;
import cn.sinjinsong.sample.security.token.TokenManager;
import cn.sinjinsong.sample.service.user.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by SinjinSong on 2017/4/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private RoleDOMapper roleDOMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private TokenManager tokenManager;

    @Override
    @Cacheable("UserDO")
    @Transactional(readOnly = true)
    public UserDO findByUsername(String username) {
        return userDOMapper.findByUsername(username);
    }

    @Override
    @Cacheable("UserDO")
    @Transactional(readOnly = true)
    public UserDO findByPhone(String phone) {
        return userDOMapper.findByPhone(phone);
    }

    @Override
    @Cacheable("UserDO")
    @Transactional(readOnly = true)
    public UserDO findById(Long id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(value = "UserDO", allEntries = true)
    public void save(UserDO userDO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //对密码进行加密
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        userDO.setRegTime(LocalDateTime.now());
        userDOMapper.insert(userDO);
        //添加用户的角色，每个用户至少有一个user角色
        long roleId = roleDOMapper.findRoleIdByRoleName("ROLE_USER");
        roleDOMapper.insertUserRole(userDO.getId(), roleId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(value = "UserDO", allEntries = true)
    public void update(UserDO userDO) {
        userDOMapper.updateByPrimaryKeySelective(userDO);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(value = "UserDO", allEntries = true)
    public void resetPassword(Long id, String username, String newPassword) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setPassword(passwordEncoder.encode(newPassword));
        tokenManager.deleteToken(username);
        userDOMapper.updateByPrimaryKeySelective(userDO);
    }

    @Transactional(readOnly = true)
    @CacheEvict(value = "UserDO", allEntries = true)
    @Override
    public List<UserDO> findIdAndNameByUsernameContaining(String username) {
        return userDOMapper.findIdAndNameByUsernameContaining(username);
    }

    @Transactional(readOnly = true)
    @CacheEvict(value = "UserDO", allEntries = true)
    @Override
    public PageInfo<UserDO> findAll(int pageNum, int pageSize) {
        return userDOMapper.findAll(pageNum, pageSize).toPageInfo();
    }

    @Transactional(readOnly = true)
    @CacheEvict(value = "UserDO", allEntries = true)
    @Override
    public String findAvatarById(Long id) {
        return userDOMapper.findAvatarById(id);
    }

    @Transactional(readOnly = true)
    @CacheEvict(value = "UserDO", allEntries = true)
    @Override
    public UserDO findByEmail(String email) {
        return userDOMapper.findByEmail(email);
    }
}
