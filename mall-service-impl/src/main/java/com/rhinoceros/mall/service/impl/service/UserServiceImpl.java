package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.constant.ConstantValue;
import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.dto.ResetPasswordDto;
import com.rhinoceros.mall.core.dto.RetrievePasswordDto;
import com.rhinoceros.mall.core.enumeration.Gender;
import com.rhinoceros.mall.core.enumeration.UserStatus;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.utils.SecurityUtils;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.manager.manager.FileUploadManager;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.user.*;
import com.rhinoceros.mall.service.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("#{mail['mail.username']}")
    private String from;

    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FileUploadManager fileUploadManager;

    /**
     * 用户注册，注册失败抛出{@link UserException}
     *
     * @param userDto
     */
    @Transactional
    @Override
    public void register(RegisterUserDto userDto) {
        //设置变量获取注册时输入的用户名
        String username = userDto.getUsername();
        //设置变量获取注册时输入的邮箱
        String email = userDto.getEmail();

        if (username == null) {
            log.info("用户名不能为null");
            throw new ParameterIsNullException("用户名不能为null");
        }
        //通过输入的用户名查询数据库
        User user = userDao.findByUsername(username);
        //如果用户已存在，程序抛出异常
        if (user != null) {
            log.info("用户已经存在");
            throw new UserHasFoundException("用户已经存在");
        }

        if (email == null) {
            log.info("邮箱不能为null");
            throw new ParameterIsNullException("邮箱不能为null");
        }
        //通过输入的邮箱查询数据库
        user = userDao.findByEmail(email);
        //如果用户已存在，程序抛出异常
        if (user != null) {
            log.info("邮件已经注册过");
            throw new EmailHasFoundException("邮箱已经注册过");
        }

        //创建用户
        User u = new User();

        //录入用户名
        u.setUsername(username);
        //录入登陆密码(加密)
        u.setPassword(SecurityUtils.encrypt(userDto.getPassword()));
        //初始邮件设置为空
        u.setEmail(userDto.getEmail());
        //注册时昵称默认使用用户名
        u.setNickname(userDto.getUsername());
        //初始性别初始为空
        u.setGender(Gender.UNKNOWN);
        //初始电话为空
        u.setTelephone(null);
        //初始生日为空
        u.setBirthday(null);
        //初始头像为空
        u.setAvatar(null);
        //记录注册时间
        u.setCreateAt(new Date());
        //上次登陆时间为空
        u.setLastLoginAt(null);
        //上次登陆ip地址为空
        u.setLastLoginIp(null);
        //设置用户状态为激活
        u.setStatus(UserStatus.ACTIVATED);
        //将用户信息录入数据库
        userDao.add(u);
    }

    /**
     * 用户登录，登录失败抛出{@link UserException}
     *
     * @param userDto
     * @return user 登录成功返回的用户信息
     */
    @Transactional
    @Override
    public User login(LoginUserDto userDto) {
        String username = userDto.getUsername();
        if (username == null) {
            log.info("用户名不能为null");
            throw new ParameterIsNullException("用户名不能为null");
        }
        //根据用户名从dao中查询用户信息
        User user = userDao.findByUsername(username);
        //判断用户是否存在,不存在则返回null
        if (user == null) {
            log.info("用户不存在");
            throw new UserNotFoundException("用户不存在");
        }
        // 用户存在，比较密码是否匹配，如果不匹配，则抛出异常
        if (!user.getPassword().equals(SecurityUtils.encrypt(userDto.getPassword()))) {
            log.info("密码输入错误");
            throw new PsaawordNotMatchException("密码输入错误");
        }

        //更新用户登陆信息
        Date date = new Date();
        User u = new User();
        u.setId(user.getId());
        u.setLastLoginIp(userDto.getIp());
        u.setLastLoginAt(date);
        //保存到数据库
        userDao.updateSelectionById(u);

        user.setLastLoginAt(date);
        user.setLastLoginIp(userDto.getIp());
        //返回用户信息
        return user;
    }


    @Transactional
    @Override
    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        String secret = resetPasswordDto.getSecret();
        String email = SecurityUtils.decode(secret);
        User user = userDao.findByEmail(email);
        user.setPassword(resetPasswordDto.getPassword());
        userDao.updateSelectionById(user);
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public void sendResetPasswordEmail(RetrievePasswordDto retrievePasswordDto, String redirectUrl) {

        String email = retrievePasswordDto.getEmail();

        User user = userDao.findByEmail(email);
        if (user == null) {
            log.info("对应邮箱的用户不存在");
            throw new UserNotFoundException("对应邮箱的用户不存在");
        }

        //加密验证参数
        String param = SecurityUtils.encode(email);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("密码重置邮件");

        //获取当前网站url
        message.setText("重置密码:" + redirectUrl + "?" + ConstantValue.RESET_PASSWORD_PARAM + "=" + param);
        mailSender.send(message);
    }

    /**
     * 根据id更新用户信息中不为null的字段
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User updateSelectionById(User user) {
        Long userId = user.getId();
        if (userId == null){
            log.info("用户id不能为空");
            throw new ParameterIsNullException("用户id不能为空");
        }
        User old = userDao.findById(userId);
        if (old==null){
            log.info("用户不存在");
            throw  new EntityNotExistException("用户不存在");
        }
        userDao.updateSelectionById(user);
        return userDao.findById(userId);
    }

    /**
     * 上传文件
     * @param is
     * @param user
     * @param fileName
     * @return
     */
    @Transactional
    @Override
    public User upload(InputStream is,User user ,String fileName){
        String str = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件后缀
        String extName = convertString(str);//后缀转化为小写
        //判断文件格式是否为图片
        if(!extName.equals("jpg")&&!extName.equals("png")&&!extName.equals("bmp")&&!extName.equals("gif")){
            throw new AvatarFormatNotCorrectException("图片格式不正确");
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/");
        String dateString = formatter.format(date);
        String savePath = "user/avatar/"+dateString;//保存路径
        String saveName = UUID.randomUUID().toString()+ "_" + fileName;//重组文件名称
        //获取图片url
        String a_url = fileUploadManager.upload(is,savePath,saveName);
        //更新数据库中用户头像
        user.setAvatar(a_url);
        //更新用户数据
        userDao.updateSelectionById(user);
        //获取用户
        user = userDao.findById(user.getId());
        return user;
    }

    /**格式化**/
    private String convertString(String str){
        char[] ch = str.toCharArray();
        StringBuffer sbf = new StringBuffer();
        for(int i=0; i< ch.length; i++){
            sbf.append(charToLowerCase(ch[i]));
        }
        return sbf.toString();
    }
    /***转小写**/
    private char charToLowerCase(char ch){
        if(ch <= 90 && ch >= 65){
            ch += 32;
        }
        return ch;
    }

}
