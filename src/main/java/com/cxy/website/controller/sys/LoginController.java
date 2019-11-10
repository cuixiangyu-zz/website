package com.cxy.website.controller.sys;

import com.cxy.website.common.aop.LogDetail;
import com.cxy.website.common.util.RandomUtils;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.sys.LoginResult;
import com.cxy.website.service.sys.LoginService;
import com.cxy.website.service.sys.SysUserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *@Description 用户登录控制器
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Controller
@Api(value = "用户登录")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    SysUserService userService;

    private long verifyTTL = 60;//验证码过期时间60秒

    private String create16String()
    {
        return RandomUtils.generateString(16);
    }


    /**
     *@Description 获取用户名
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping("/getUserName")
    @ApiOperation(value = "getUserName", notes = "获取用户名", httpMethod = "GET")
    @ResponseBody
    public Object getUserName(HttpSession session){
        String userName=loginService.getCurrentUserName();
        session.setAttribute("userName",userName);
        Map map=new HashMap();
        map.put("userName",userName);
        return JsonData.buildSuccess(map);
        }
    /**
     *@Description 生成验证码(项目中暂时不需要先留着)
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping("/getVerifyCode")
    @ApiOperation(value = "getVerifyCode", notes = "生成验证码", httpMethod = "GET")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        byte[] bytesCaptchaImg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("verifyCode", createText);
            request.getSession().setAttribute("verifyCodeTTL", System.currentTimeMillis());
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage bufferedImage = defaultKaptcha.createImage(createText);
            ImageIO.write(bufferedImage, "jpg", jpegOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        bytesCaptchaImg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(bytesCaptchaImg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     *@Description 返回没有权限界面
     *@Param
     *@Return 无权限界面
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping("/403")
    @ApiOperation(value = "403", notes = "未授权", httpMethod = "GET")
    @ResponseBody
    public Object unauthorizedRole() {
        System.out.println("------没有权限-------");
        return JsonData.buildError("没有权限",1);
    }

    /**
     *@Description 用户登录
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/loginMain",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "loginMain", notes = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号",paramType = "query",required = true,  dataType = "String",defaultValue="admin"),
            @ApiImplicitParam(name = "password", value = "密码",paramType = "query",required = true,  dataType = "String",defaultValue="123456")
    })
    @LogDetail(logType="登录",type = 5)
    public Object login(String userName,String password) throws Exception {
        System.out.println("login()");
        Map<String, Object> map = new HashMap<>();

       // String password = AesUtils.decrypt(encryptedPwd,key);
        LoginResult loginResult = loginService.login(userName, password);
        if (loginResult.isLogin()) {
            map.put("userName", userName);
            map.put("token",loginResult.getToken());
           return  JsonData.buildSuccess(map,"登录成功",1);
           //return map;
        } else {
            return JsonData.buildError(loginResult.getResult(),1);
        }
    }

    /**
     *@Description 用户退出系统
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "loginOut", notes = "用户退出系统", httpMethod = "GET")
    public Object logOut() {
        loginService.logout();
        return JsonData.buildSuccess(null,"退出系统",0);
    }


}
