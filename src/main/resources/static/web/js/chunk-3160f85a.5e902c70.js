(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3160f85a"],{"127a":function(e,t,s){},7336:function(e,t,s){},c3f6:function(e,t,s){"use strict";var a=s("127a"),i=s.n(a);i.a},ce1f:function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return e.tableData?s("div",{staticClass:"main"},[s("el-form",{attrs:{inline:!0}},[s("el-form-item",{attrs:{label:"图片名"}},[s("el-input",{attrs:{clearable:"",placeholder:"请输入图片名"},model:{value:e.listQuery.pictureName,callback:function(t){e.$set(e.listQuery,"pictureName",t)},expression:"listQuery.pictureName"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"作者"}},[s("el-select",{attrs:{placeholder:"请选择","value-key":"id"},model:{value:e.listQuery.actorName,callback:function(t){e.$set(e.listQuery,"actorName",t)},expression:"listQuery.actorName"}},e._l(e.actors,(function(e){return s("el-option",{key:e.id,attrs:{label:e.chineseName,value:e.id}})})),1)],1),e._v(" "),s("el-form-item",{attrs:{label:"语言"}},[s("el-select",{attrs:{placeholder:"请选择",clearable:""},model:{value:e.listQuery.language,callback:function(t){e.$set(e.listQuery,"language",t)},expression:"listQuery.language"}},[s("el-option",{attrs:{label:"汉语",value:"chinese"}}),e._v(" "),s("el-option",{attrs:{label:"英语",value:"english"}}),e._v(" "),s("el-option",{attrs:{label:"日语",value:"japan"}})],1)],1),e._v(" "),s("el-form-item",{attrs:{label:"分类"}},[s("el-cascader-multi",{attrs:{data:e.typeMap,filterable:"","reserve-keyword":""},model:{value:e.listQuery.types,callback:function(t){e.$set(e.listQuery,"types",t)},expression:"listQuery.types"}})],1),e._v(" "),s("el-form-item",{staticStyle:{"margin-right":"50px"}},[s("el-button",{attrs:{type:"primary"},on:{click:e.getPageList}},[e._v("查询")])],1)],1),e._v(" "),s("el-row",{staticClass:"row-ul",attrs:{gutter:"20"}},e._l(e.tableData.list,(function(t){return s("el-col",{key:t.id,staticClass:"row-li",attrs:{span:4},nativeOn:{click:function(t){return e.handleClick(t)}}},[s("el-card",{attrs:{"body-style":{padding:"0px",height:"500px"}}},[s("el-image",{attrs:{src:t.coverUrl,"preview-src-list":t.address,lazy:""},on:{click:e.showimg}}),e._v(" "),s("div",{staticStyle:{padding:"14px"},on:{click:function(s){return e.jump(t.id)}}},[s("span",{staticClass:"spantest"},[e._v(e._s(t.name))]),e._v(" "),s("div",{staticClass:"bottom clearfix"},[s("time",{staticClass:"time"},[e._v(e._s(t.actorname))]),e._v(" "),s("span",{staticClass:"tag-group__title"},[e._v("类型:")]),e._v(" "),e._l(t.types,(function(t){return s("el-tag",{key:t.id,attrs:{size:"mini",effect:"plain"},on:{click:function(s){return e.getType(t)}}},[e._v(e._s(t.chineseName))])}))],2),e._v(" "),s("div",{staticClass:"bottom clearfix"},[s("time",{staticClass:"time"},[e._v(e._s(t.actorname))]),e._v(" "),s("span",{staticClass:"tag-group__title"},[e._v("作者:")]),e._v(" "),e._l(t.actors,(function(t){return s("el-tag",{key:t.id,attrs:{size:"mini",effect:"plain"},on:{click:function(s){return e.getActor(t.id)}}},[e._v(e._s(t.chineseName))])}))],2)])],1)],1)})),1),e._v(" "),s("el-pagination",{staticStyle:{"margin-top":"20px"},attrs:{total:e.tableData.total,"current-page":e.listQuery.pageNum,"page-sizes":[1,6,12,18,24],"page-size":e.listQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1):e._e()},i=[],r=s("b775");function l(e){return Object(r["a"])({url:"/picture/getPageList",method:"post",data:e})}var n={data:function(){return{tableData:[],listQuery:{pageNum:1,pageSize:12,actorName:null,pictureName:null,language:null,types:null},typeMap:"",actors:"",deviceDetail:null,imgSrc:"http://127.0.0.1:8081/website/resources/_MG_0170.jpg",url:"http://127.0.0.1:8081/website/resources/_MG_0170.jpg",srcList:[]}},created:function(){this.getPageList()},beforeRouteLeave:function(e,t,s){sessionStorage.setItem("listQuery_comic_picture",JSON.stringify(this.listQuery)),sessionStorage.setItem("refresh_comic_picture",!0),s()},methods:{getPageList:function(){var e=this,t=sessionStorage.getItem("listQuery_comic_picture"),s=sessionStorage.getItem("refresh_comic_picture");null!==t&&null!==s&&"true"===s&&(this.listQuery=JSON.parse(t),console.log("aaaaaaaaaaaaaaaaaa")),console.log(this.listQuery),l(this.listQuery).then((function(t){e.tableData=t.PageInfo,e.actors=t.actors,e.typeMap=t.typeMap}))},handleCurrentChange:function(e){sessionStorage.setItem("refresh_comic_picture",!1),this.listQuery.pageNum=e,this.getPageList()},handleSizeChange:function(e){sessionStorage.setItem("refresh_comic_picture",!1),this.listQuery.pageSize=e,this.getPageList()},handleClick:function(){},getType:function(e){event.stopPropagation();var t=[],s=[];t.push("allTypes"),t.push(e.id),s.push(t),this.listQuery.types=s,this.listQuery.actorName=null,console.log(this.listQuery),sessionStorage.setItem("refresh_comic_picture",!1),this.getPageList()},getActor:function(e){event.stopPropagation(),this.listQuery.actorName=e,this.listQuery.types=null,sessionStorage.setItem("refresh_comic_picture",!1),this.getPageList()},imgview:function(){alert("2222")},jump:function(e){sessionStorage.setItem("listQuery_comic_picture",JSON.stringify(this.listQuery)),sessionStorage.setItem("refresh_comic_picture",!0),sessionStorage.setItem("refresh_comic_picture_detail",!0),this.$router.push({path:"/picture_detail/index",name:"图片详情",params:{id:e}})},showimg:function(){var e=[];e.push("http://127.0.0.1:8081/website/resources/_MG_0177.jpg"),e.push("http://127.0.0.1:8081/website/resources/_MG_0170.jpg"),this.srcList=e}}},c=n,o=(s("c3f6"),s("fdcd"),s("2877")),u=Object(o["a"])(c,a,i,!1,null,"4b423810",null);t["default"]=u.exports},fdcd:function(e,t,s){"use strict";var a=s("7336"),i=s.n(a);i.a}}]);