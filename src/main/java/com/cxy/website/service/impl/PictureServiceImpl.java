package com.cxy.website.service.impl;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.dao.PictureMapper;
import com.cxy.website.dao.PictureTypeMapper;
import com.cxy.website.model.*;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:38
 **/
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    PictureTypeMapper pictureTypeMapper;

    @Autowired
    TypeService typeService;

    @Autowired
    ActorService actorService;

    @Autowired
    LevelService levelService;

    @Autowired
    UserFavoriteService userFavoriteService;

    @Value("${file.url.prefix}")
    private String FILE_URL_PREFIX ;

    private static final Pattern COMIC_NAME = Pattern.compile("\\][^\\[\\]]+(\\[)?");

    /**
     * 添加
     *
     * @param picture
     * @return
     */
    @Override
    public int add(Picture picture) {
        int count = pictureMapper.insert(picture);
        return count;
    }

    /**
     * 修改
     *
     * @param picture
     * @return
     */
    @Override
    public int update(Picture picture) {
        int count = pictureMapper.updateByPrimaryKey(picture);
        return count;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        int count = pictureMapper.deleteByPrimaryKey(id);
        int piccount = pictureTypeMapper.deleteByPictureId(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Picture findById(int id) {
        Picture picture = pictureMapper.selectByPrimaryKey(id);
        return picture;
    }

    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    @Override
    public Picture findByName(String name) {
        Picture picture = pictureMapper.selectByName(name);
        return picture;
    }

    /**
     * 根据作者查找
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageInfo<Picture> findByArtist(int pageNum, int pageSize, int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByArtist(id);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
    }

    /**
     * 根据类型查找
     *
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @Override
    public PageInfo<Picture> findByType(int pageNum, int pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByType(type);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
    }

    /**
     * 根据语言查找
     *
     * @param pageNum
     * @param pageSize
     * @param language
     * @return
     */
    @Override
    public PageInfo<Picture> findByLanguage(int pageNum, int pageSize, String language) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictures = pictureMapper.selectByLanguage(language);
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        return page;
    }

    /**
     * 条件查询图片
     *
     * @param pageNum     页码
     * @param pageSize    但也数量
     * @param actorName   作者名
     * @param pictureName 图片名
     * @param types       类型
     * @return jsondata
     */
    @Override
    public JsonData findPageList(Integer pageNum, Integer pageSize, String actorName, String pictureName, String language, List<List<Object>> types) {
        PageHelper.startPage(pageNum, pageSize);
        List<String> type = null;
        if (types != null && types.size() > 0) {
            type = new ArrayList<>();
            for (List<Object> typelist : types) {

                if (typelist.get(1) != null) {
                    type.add(typelist.get(1).toString());
                }
            }
        }

        List<Picture> pictures = pictureMapper.selectPageList(actorName, pictureName, language, type);
        for (Picture picture : pictures) {
            picture = getPicture(picture, picture.getId());
        }
        PageInfo<Picture> page = new PageInfo<Picture>(pictures);
        List<Type> alltypes = typeService.findByType(CommonStatus.TYPE_TYPE_PICTURE);
        List<Actor> actors = actorService.findByType(CommonStatus.ACTOR_TYPE_PICTURE);
        Map<String, Object> jsondata = getTypeList(alltypes, type);
        jsondata.put("PageInfo", page);
        jsondata.put("actors", actors);
        return JsonData.buildSuccess(jsondata);
    }

    public Map<String, Object> getTypeList(List<Type> allTypes, List<String> selectTypes) {
        if (allTypes == null) {
            return null;
        }
        List<Map> childList = new ArrayList<>();
        Map<String, Object> typeMap = new HashMap<>();
        typeMap.put("value", "allTypes");
        typeMap.put("label", "类型");
        typeMap.put("multiple", "true");
        List<Map<String, Object>> typeList = new ArrayList<>();

        for (Type type : allTypes) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", type.getId());
            info.put("label", type.getChineseName());
            info.put("multiple", "true");
            if (selectTypes != null && selectTypes.contains(type.getChineseName())) {
                info.put("checked", true);
            } else {
                info.put("checked", false);
            }
            typeList.add(info);
        }
        typeMap.put("children", typeList);
        List<Map<String, Object>> typelist = new ArrayList<>();
        typelist.add(typeMap);
        Map<String, Object> jsondata = new HashMap<>();
        jsondata.put("typeMap", typelist);
        return jsondata;
    }

    @Override
    public Picture getPicture(Picture picture, Integer id) {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<Actor> actors = actorService.findByPictureid(id);
        List<Type> types = typeService.findByPictureid(id);
        picture.setActors(actors);
        picture.setTypes(types);
        picture.setCoverUrl(FILE_URL_PREFIX + picture.getCoverUrl());

        Level level = levelService.findByProductionIdAndUserId(picture.getId(), user.getId(), CommonStatus.FAVORITE_HISTORY_TYPE_PICTURE);
        if (level != null) {
            picture.setLevel(level.getLevel());
        }

        UserFavorite userFavorite = userFavoriteService.findByUserIdAndVideoId(user.getId(),CommonStatus.FAVORITE_HISTORY_TYPE_PICTURE, id);
        if (userFavorite != null) {
            picture.setUserFavorite(userFavorite);
        }

        /*List<String> addrlist = new ArrayList<String>();
        addrlist.add("K:\\resources/");
        addrlist.add("I:\\resource/");
        addrlist.add("H:\\resources/");*/

        String address = "";
        for (String addr : CommonStatus.addrs) {
            address = addr + picture.getPictureUrl();
            if (new File(address).exists()) {
                break;
            }
        }
        File root = new File(address);
        File[] files = root.listFiles();
        List<String> piclist = new ArrayList<>();
        for (File picfile : files) {
            piclist.add(FILE_URL_PREFIX + picture.getPictureUrl() + File.separator + picfile.getName());
        }
        picture.setAddress(piclist);
        return picture;
    }

    /**
     * 根据地址获取文件夹下所有图片并重命名
     *
     * @param address 文件夹地址
     * @return 文件名和推荐名
     */
    @Override
    public JsonData getComicName(String address) {
        File file = new File(address);
        List<UpdateFileName> picList = new ArrayList<>();

        for (File listFile : file.listFiles()) {
            UpdateFileName updateFileName = new UpdateFileName();
            String picName = listFile.getName();
            if (!picName.contains("[")) {
                updateFileName.setFilename(picName);
                updateFileName.setSuggestname(picName);
                picList.add(updateFileName);
                continue;
            }
            Matcher matcher = null;
            matcher = COMIC_NAME.matcher(picName);
            String newName = "";
            while (matcher.find()) {
                String currentName = matcher.group(0).trim(); // 链接
                currentName = currentName.replace("[", "");
                currentName = currentName.replace("]", "");
                if (currentName.length() >= 2 && currentName.length() > newName.length()) {
                    newName = currentName;
                }
            }
            updateFileName.setFilename(picName);
            updateFileName.setSuggestname(newName);
            picList.add(updateFileName);
        }
        return JsonData.buildSuccess(picList);
    }

    /**
     * 将本地文件保存到数据库
     *
     * @param source   来源文件夹
     * @param target   目标文件夹
     * @param fileMaps 文件名
     * @param type     类型
     */
    @Override
    public void updatePicsFromLocal(String source, String target, List<UpdateFileName> fileMaps, String type) {
        for (UpdateFileName fileMap : fileMaps) {
            String suggestname = fileMap.getSuggestname();
            if (suggestname.startsWith(" ")) {
                suggestname = suggestname.substring(1, suggestname.length());
            }
            suggestname = suggestname.replaceAll(" ", "_");
            suggestname = suggestname.replaceAll(";", "_");
            String oldAddress = source + File.separator + fileMap.getFilename();
            String newAddress = target + File.separator + suggestname;
            File file = new File(oldAddress);
            if (!file.exists() || file.listFiles().length <= 0) {
                continue;
            }
            String coverName = this.moveFile(oldAddress, newAddress);

            Picture picture = new Picture();
            picture.setName(fileMap.getFilename());
            picture.setExist(CommonStatus.PICTURE_EXIST_EXIST);
            picture.setPictureUrl("/comic/" + suggestname);
            picture.setCoverUrl("/comic/" + coverName);
            if (fileMap.getFilename().indexOf("Chinese") > 0) {
                picture.setLanguage("Chinese");
            }
            if (this.findByName(fileMap.getFilename()) != null) {
                picture.setId(this.findByName(fileMap.getFilename()).getId());
                this.update(picture);
                continue;
            }
            this.add(picture);
        }
    }

    /**
     * 移动文件夹
     *
     * @param oldAddress 源文件夹
     * @param newAddress 目标文件夹
     */
    private String moveFile(String oldAddress, String newAddress) {
        File oldFile = new File(oldAddress);
        File newFile = new File(newAddress);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        for (File file : oldFile.listFiles()) {
            copyFile(file.getPath(), newAddress + File.separator + file.getName());
            file.delete();
        }
        return newFile.getName() + File.separator + newFile.listFiles()[0].getName();
    }

    public void copyFile(String oldPath, String newPath) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File oldFile = new File(oldPath);
            File file = new File(newPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            in = new FileInputStream(oldFile);
            out = new FileOutputStream(file);
            byte[] buffer = new byte[2097152];
            while ((in.read(buffer)) != -1) {
                out.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
