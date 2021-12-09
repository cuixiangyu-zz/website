<template>
  <div class="main">
    <el-container>
      <el-header></el-header>
    </el-container>
    <el-row type="flex">
      <el-col :span="6" :offset="6">
        <el-card>
          <el-image :key="tableData.id" :src="tableData.coverUrl" :lazy="true" fit="fill"></el-image>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-row style="bottom: 0px; top: 0px; right: 0px; left: 20px;">
          <el-col :span="24">
            <p style="font-size: 25px; ">{{tableData.name}}</p>
            <el-button @click="getWatchList()" type="primary" >播放列表</el-button>
          </el-col>
          <el-col :span="24">
            <div class="block">
              <span class="demonstration">评分</span>
              <el-rate v-model="level.level" :colors="colors" @change="changelevel(tableData)"></el-rate>
            </div>
            <div class="block">
              <span class="demonstration">收藏</span>
              <el-tooltip class="item" effect="dark" content="添加收藏" placement="right">
                <el-button v-if="tableData.userFavorite==null" size="small" type="primary" icon="el-icon-star-off"
                           @click="addFavorite(tableData)"></el-button>
              </el-tooltip>
              <el-tooltip class="item" effect="dark" content="取消收藏" placement="right">
                <el-button v-if="tableData.userFavorite!==null" size="small" type="primary" icon="el-icon-star-on"
                           @click="deleteFavorite(tableData)"></el-button>
              </el-tooltip>
            </div>
          </el-col>
          <el-col :span="24">
            <p style="font-size: 17px; ">作者</p>
            <el-col
              v-for="actor in tableData.actors"
              :span="4"
              :key="actor.id"
              class="row-li"
              @click="getActor(actor.id)"
            >
              <el-tag>{{actor.chineseName}}</el-tag>
            </el-col>
          </el-col>
          <el-col :span="24">
            <p style="font-size: 17px; width: 20%;">分类</p>
            <el-col
              v-for="type in tableData.types"
              :span="4"
              :key="type.id"
              class="row-li"
              @click="getType(type)"
            >
              <el-tag>{{type.chineseName}}</el-tag>
            </el-col>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row style="width: 60%; left: 20%;" v-for="video in videos" :key="video.poster">
      <div class="player-container">
        <video-player
          class="vjs-custom-skin"
          :options="video"
          @keyup.left="down"
          @keyup.right="up"
        ></video-player>
      </div>
    </el-row>
    <el-row style="width: 60%; left: 20%;"></el-row>
    <el-drawer
      title="播放列表!"
      :visible.sync="tableVisible"
      direction="rtl"
      size="50%">
      <el-table :data="videoAndIdList.videoList" height="800" :row-class-name="tableRowClassName">
        <el-table-column property="name" label="影片名" width="250"></el-table-column>
        <el-table-column prop="type" label="类型"/>
        <el-table-column prop="actors" label="作者">
          　　
          <template scope="scope">
            　　　　
            <div v-for="item in scope.row.actors">{{item.chineseName}}</div>
            　　
          </template>
        </el-table-column>
        <el-table-column label="封面" >
          　　
          <template slot-scope="scope">
            　　　　<img :src="scope.row.coverUrl" width="90%" height="90%" alt="封面"/>
            　　
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button size="mini" @click="selectVideo(scope.$index, scope.row)">查看影片</el-button>
            <el-button size="mini" type="primary" @click="deleteVideo(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    <el-container>
      <el-header></el-header>
    </el-container>

  </div>
</template>
<script>
    import {getDetil, changelevel, saveViewHistory, getWatchList} from "@/api/videoDetail";
    import {addHistory} from "@/api/viewHistory";
    import {addFavorite, deleteFavorite} from "@/api/userFavorite";
    // 引入video样式
    import "video.js/dist/video-js.css";
    import "vue-video-player/src/custom-theme.css";

    export default {
        data() {
            return {
                tableData: [],
                querylist: [],
                querydata: null,
                tableVisible: false,
                editableTabsValue: "",
                video: undefined,
                videos: [],
                listQuery: {
                    pageNum: 1,
                    pageSize: 10,
                    actorName: null,
                    pictureName: null,
                    language: null,
                    types: null
                },
                typeMap: "",
                actors: "",
                deviceDetail: null,
                imgSrc: "http://127.0.0.1:8081/website/resources/_MG_0138.jpg",
                url: "http://127.0.0.1:8081/website/resources/_MG_0138.jpg",
                level: {
                    id: null,
                    type: null,
                    level: null
                },
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
                viewHistory: {
                    type: null,
                    videoId: null,
                    startData: '',
                    watchTime: ''
                },
                addFavoriteParms: {
                    type: null,
                    videoId: null
                },
                videoAndIdList: {
                    idList: [],
                    videoList: []
                }
            };
        },
        created() {
            this.viewHistory.startData = Date.now();
            var list = sessionStorage.getItem("videoAndIdList");
            if (list !== null &&
                list !== undefined &&
                list !== "") {
                this.videoAndIdList = JSON.parse(list);
            }
            this.getDetil();
        },
        mounted() {
            this.video = this.$refs.video;
        },
        updated() {
            this.video = this.$refs.video;
        },
        beforeRouteLeave(to, form, next) {
            sessionStorage.setItem(
                "listQuery_video_detail",
                JSON.stringify(this.querydata)
            );
            sessionStorage.setItem("refresh_video_detail", true);
            sessionStorage.setItem("videoAndIdList", JSON.stringify(this.videoAndIdList));
            const currentTime = Date.now();

            this.viewHistory.watchTime = currentTime - this.viewHistory.startData;
            this.viewHistory.startData = new Date(this.viewHistory.startData);
            this.saveViewHistory();
            next();
        },
        methods: {
            getDetil() {
                var listQuery = sessionStorage.getItem("listQuery_video_detail");
                var refresh = sessionStorage.getItem("refresh_video_detail");
                if (listQuery !== null && refresh !== null && refresh === "true") {
                    this.querydata = JSON.parse(listQuery);
                }
                if (
                    this.$route.query.id !== null &&
                    this.$route.query.id !== undefined &&
                    this.$route.query.id !== ""
                ) {
                    this.querydata = this.$route.query.id;
                }
                this.viewHistory.videoId = this.querydata;
                this.videoAndIdList.idList.unshift(this.querydata);
                getDetil({id: this.querydata}).then(res => {
                    this.viewHistory.type = res.type;
                    this.level.level = res.level;
                    //strurl = strurl .replace("\\", "\/");
                    res.coverUrl = res.coverUrl.replace(/\\/g,"/");
                    this.tableData = res;
                    const srcList = [];
                    for (const i of res.address) {
                        const videoInfo = {
                            playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
                            autoplay: false, // 如果true,浏览器准备好时开始回放。
                            controls: true, // 控制条
                            preload: "auto", // 视频预加载
                            muted: false, // 默认情况下将会消除任何音频。
                            loop: false, // 导致视频一结束就重新开始。
                            language: "zh-CN",
                            aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                            fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                            sources: [
                                {
                                    src: i
                                }
                            ],
                            poster: res.coverUrl,
                            width: document.documentElement.clientWidth,
                            notSupportedMessage: "此视频暂无法播放，请稍后再试" // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                        };
                        srcList.push(videoInfo);
                    }
                    this.videos = srcList
                });
            },
            saveViewHistory() {
                addHistory(this.viewHistory).then(res => {

                })
            },
            getType(type) {
                event.stopPropagation();
                const arr = [];
                const arr1 = [];
                arr.push("allTypes");
                arr.push(type.id);
                arr1.push(arr);
                this.listQuery.types = arr1;
                this.listQuery.actorName = null;
                console.log(this.listQuery);
                this.jump();
            },
            getActor(actor) {
                event.stopPropagation();
                this.listQuery.actorName = actor;
                this.listQuery.types = null;
                this.jump();
            },
            jump() {
                this.$router.push({
                    path: "/video/index",
                    name: "影片" // 要跳转的路径的 name,可
                });
            },
            showimg() {
                const arr = [];
                arr.push("http://127.0.0.1:8081/website/resources/_MG_0170.jpg");
                arr.push("http://127.0.0.1:8081/website/resources/_MG_0177.jpg");
                this.srcList = arr;
            },
            up() {
                var time = this.video.currentTime;
                console.log(time);
                this.video.currentTime = time + 120;
            },
            down() {
                var time = this.video.currentTime;
                console.log(time);
                this.video.currentTime = time - 120;
            },
            changelevel(item) {
                this.level.id = item.id;
                this.level.type = item.type;
                changelevel(this.level);
            },
            addFavorite(data) {
                this.addFavoriteParms.videoId = data.id;
                this.addFavoriteParms.type = data.type;
                addFavorite(this.addFavoriteParms).then(res => {
                    this.tableData.userFavorite = res;
                }).catch(res => {
                    this.getDetil();
                })
            },
            deleteFavorite(data) {
                deleteFavorite({id: this.tableData.userFavorite.id}).then(res => {
                    this.tableData.userFavorite = null;
                }).catch(res => {
                    this.getDetil();
                })
            },
            getWatchList() {
                getWatchList({idList: JSON.stringify(this.videoAndIdList.idList)}).then(res => {
                    res.forEach(item => {
                        if (item.type === 1) {
                            item.type = '日本影片'
                        }else if (item.type === 2) {
                            item.type = '美国影片'
                        }else if (item.type === 3) {
                            item.type = '动漫'
                        }else if (item.type === 4) {
                            item.type = '小视频'
                        }else if (item.type === 5) {
                            item.type = '图片'
                        }
                    })
                    this.videoAndIdList.videoList = res;
                    this.tableVisible = true;
                    console.log(this.videoAndIdList);
                })
            },
            deleteVideo(index, row) {
                for (var i = 0; i < this.videoAndIdList.idList.length; i++) {
                    if (row.id === this.videoAndIdList.idList[i]) {
                        this.videoAndIdList.idList.splice(i, 1); // 将使后面的元素依次前移，数组长度减1
                        i--; // 如果不减，将漏掉一个元素
                    }
                }
                this.getWatchList();
            },
            selectVideo(index, row) {
                const currentTime = Date.now();

                this.viewHistory.watchTime = currentTime - this.viewHistory.startData;
                this.viewHistory.startData = new Date(this.viewHistory.startData);
                this.saveViewHistory();
                this.viewHistory.startData = Date.now();
                this.tableVisible = false;
                this.querydata = row.id;
                getDetil({id: this.querydata}).then(res => {
                    this.viewHistory.type = res.type;
                    this.level.level = res.level;
                    this.tableData = res;
                    const srcList = [];
                    for (const i of res.address) {
                        const videoInfo = {
                            playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
                            autoplay: false, // 如果true,浏览器准备好时开始回放。
                            controls: true, // 控制条
                            preload: "auto", // 视频预加载
                            muted: false, // 默认情况下将会消除任何音频。
                            loop: false, // 导致视频一结束就重新开始。
                            language: "zh-CN",
                            aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                            fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                            sources: [
                                {
                                    src: i
                                }
                            ],
                            poster: res.coverUrl,
                            width: document.documentElement.clientWidth,
                            notSupportedMessage: "此视频暂无法播放，请稍后再试" // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                        };
                        srcList.push(videoInfo);
                    }
                    this.videos = srcList
                });

            },
            tableRowClassName({row, rowIndex}) {
                if (row.id === this.querydata) {
                    return 'success-row';
                }
                return '';
            }
        }
    };
</script>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
