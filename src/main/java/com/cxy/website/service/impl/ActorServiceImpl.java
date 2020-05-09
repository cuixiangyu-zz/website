package com.cxy.website.service.impl;

import com.cxy.website.common.CommonStatus;
import com.cxy.website.dao.ActorMapper;
import com.cxy.website.dao.ActorTypeMapper;
import com.cxy.website.dao.VideoActorMapper;
import com.cxy.website.model.Actor;
import com.cxy.website.model.Level;
import com.cxy.website.model.Video;
import com.cxy.website.service.ActorService;
import com.cxy.website.service.LevelService;
import com.cxy.website.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @description:
 * @author: CuiXiangYu
 * @create: 2019-10-16 13:48
 **/
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorMapper actorMapper;

    @Autowired
    VideoActorMapper videoActorMapper;

    @Autowired
    VideoService videoService;

    @Autowired
    LevelService levelService;

    @Value("${file.url.prefix}")
    private String FILE_URL_PREFIX ;

    /**
     * 添加
     *
     * @param actor
     * @return
     */
    @Override
    public int add(Actor actor) {
        int count = actorMapper.insert(actor);
        return count;
    }

    /**
     * 修改
     *
     * @param actor
     * @return
     */
    @Override
    public int update(Actor actor) {
        int count = actorMapper.updateByPrimaryKey(actor);
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
        int count = actorMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Actor findByid(int id) {
        Actor actor = actorMapper.selectByPrimaryKey(id);
        return actor;
    }

    /**
     * 根据名子查找
     *
     * @param name
     * @return
     */
    @Override
    public Actor findByName(String name) {
        Actor actor = actorMapper.selectByName(name);
        return actor;
    }

    /**
     * 根据中文名查找
     *
     * @param chineseName
     * @return
     */
    @Override
    public Actor findByChineseName(String chineseName) {
        Actor actor = actorMapper.selectByChineseName(chineseName);
        return actor;
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
    public PageInfo<Actor> findByType(int pageNum, int pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByType(type);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据国家查找
     *
     * @param pageNum
     * @param pageSize
     * @param country
     * @return
     */
    @Override
    public PageInfo<Actor> findByCountry(int pageNum, int pageSize, String country) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByCountry(country);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据级别查找
     *
     * @param pageNum
     * @param pageSize
     * @param level
     * @return
     */
    @Override
    public PageInfo<Actor> findByLevel(int pageNum, int pageSize, int level) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectByLevel(level);
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    /**
     * 根据漫画id查询作者
     *
     * @param id
     * @return
     */
    @Override
    public List<Actor> findByPictureid(Integer id) {
        List<Actor> actors = actorMapper.selectByPictureid(id);
        return actors;
    }

    @Override
    public List<Actor> findByVideoid(Integer id) {
        List<Actor> actors = actorMapper.selectByVideoid(id);
        return actors;
    }

    @Override
    public PageInfo<Actor> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Actor> actors = actorMapper.selectAll();
        for (Actor actor : actors) {
            String address = FILE_URL_PREFIX + actor.getCoverUrl();
            actor.setCoverUrl(address);
        }
        PageInfo<Actor> page = new PageInfo<Actor>(actors);
        return page;
    }

    @Override
    public List<Actor> findByType(Integer type) {
        List<Actor> actors = actorMapper.selectByType(type);
        return actors;
    }

    @Override
    public void updateVideoActor(Integer id, List<String> artists) {
        videoActorMapper.updateVideoActor(id,artists);
    }

    /**
     * 根据视频id更新演员分数
     * @param videoId 视频id
     */
    @Override
    public void updateLevel(Integer videoId){
        if(videoId!=null){
            Video video = videoService.findByid(videoId);
            List<Actor> actors = this.findByVideoid(videoId);
            for (Actor actor : actors) {
                updateLevel(actor);
            }
        }else{
            List<Actor> actors = this.findByType(CommonStatus.ACTOR_TYPE_JAPAN);
            for (Actor actor : actors) {
                updateLevel(actor);
            }
            List<Actor> americanActors = this.findByType(CommonStatus.ACTOR_TYPE_AMERICAN);
            for (Actor actor : americanActors) {
                updateLevel(actor);
            }
        }
    }

    private void updateLevel(Actor actor) {
        Map<String, Object> actorLevel = actorMapper.selectActorLevel(actor.getId());
        List<Level> levelList = levelService.findByProductionIdandType(actor.getId(), CommonStatus.TYPE_TYPE_ARTIST);
        if(levelList!=null&&levelList.size()>0){
            Level level = levelList.get(0);
            level.setLevel(actorLevel.get("level")==null?"0":actorLevel.get("level").toString());
            level.setUserId(Integer.valueOf(actorLevel.get("count")==null?"0":actorLevel.get("count").toString()));
            levelService.update(level);
        }else{
            Level level = new Level();
            level.setLevel(actorLevel.get("level")==null?"0":actorLevel.get("level").toString());
            level.setUserId(Integer.valueOf(actorLevel.get("count")==null?"0":actorLevel.get("count").toString()));
            level.setProductionId(actor.getId());
            level.setProductionType(CommonStatus.TYPE_TYPE_ARTIST);
            levelService.add(level);
        }
    }
}
