(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4ddaa942"],{"1c47":function(e,t,a){"use strict";a.d(t,"a",(function(){return r})),a.d(t,"b",(function(){return n}));var s=a("b775");function r(e){return Object(s["a"])({url:"/viewHistory/addHistory",method:"post",data:e})}function n(e){return Object(s["a"])({url:"/viewHistory/getHistory",method:"get",params:e})}},"28b8":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.tableData?a("div",{staticClass:"main"},[a("el-form",{attrs:{model:e.defaltQuery,inline:!0}},[a("el-form-item",{attrs:{label:"类型"}},[a("el-select",{attrs:{clearable:""},model:{value:e.defaltQuery.type,callback:function(t){e.$set(e.defaltQuery,"type",t)},expression:"defaltQuery.type"}},[a("el-option",{attrs:{label:"日本影片",value:1}}),e._v(" "),a("el-option",{attrs:{label:"美国影片",value:2}}),e._v(" "),a("el-option",{attrs:{label:"动漫",value:3}}),e._v(" "),a("el-option",{attrs:{label:"小视频",value:4}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"开始时间"}},[a("el-date-picker",{attrs:{type:"datetime",format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间",align:"right","picker-options":e.pickerOptions},model:{value:e.defaltQuery.startTime,callback:function(t){e.$set(e.defaltQuery,"startTime",t)},expression:"defaltQuery.startTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"结束时间"}},[a("el-date-picker",{attrs:{type:"datetime",format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间",align:"right","picker-options":e.pickerOptions},model:{value:e.defaltQuery.endTime,callback:function(t){e.$set(e.defaltQuery,"endTime",t)},expression:"defaltQuery.endTime"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData.list,border:""}},[5!==e.type?a("el-table-column",{attrs:{prop:"video.name",label:"名字","min-width":"300"}}):e._e(),e._v(" "),5!==e.type?a("el-table-column",{attrs:{prop:"video.type",label:"类型"}}):e._e(),e._v(" "),5!==e.type?a("el-table-column",{attrs:{prop:"video.actors",label:"作者"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.video.actors,(function(t){return a("div",[e._v(e._s(t.chineseName))])}))}}],null,!1,3677261014)}):e._e(),e._v(" "),5!==e.type?a("el-table-column",{attrs:{prop:"video.level",label:"个人评分"}}):e._e(),e._v(" "),5!==e.type?a("el-table-column",{attrs:{prop:"creatTime",label:"观看时间"}}):e._e(),e._v(" "),5!==e.type?a("el-table-column",{attrs:{label:"封面","min-width":"100"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("img",{attrs:{src:e.row.video.coverUrl,width:"90%",height:"90%",alt:"封面"}})]}}],null,!1,2219722148)}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{prop:"picture.name",label:"名字","min-width":"300"}}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{prop:"picture.type",label:"类型"}}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{prop:"picture.actors",label:"作者"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.picture.actors,(function(t){return a("div",[e._v(e._s(t.chineseName))])}))}}],null,!1,2653420619)}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{prop:"picture.level",label:"个人评分"}}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{prop:"creatTime",label:"观看时间"}}):e._e(),e._v(" "),5===e.type?a("el-table-column",{attrs:{label:"封面","min-width":"100"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("img",{attrs:{src:e.row.picture.coverUrl,width:"90%",height:"90%",alt:"封面"}})]}}],null,!1,1327471737)}):e._e(),e._v(" "),a("el-table-column",{attrs:{prop:"address",label:"操作","min-width":"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.jump(t.$index,t.row)}}},[e._v("查看影片")])]}}],null,!1,2067581690)})],1),e._v(" "),a("el-pagination",{staticStyle:{"margin-top":"20px"},attrs:{total:e.tableData.total,"current-page":e.defaltQuery.pageNum,"page-sizes":[1,5,10,20,30],"page-size":e.defaltQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1):e._e()},r=[],n=(a("ac6a"),a("1c47")),l=a("bc50"),i=a("c1df"),c={data:function(){return{defaltQuery:{type:"",startTime:null,endTime:null,pageSize:10,pageNum:1},tableData:null,pickerOptions:{shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}},{text:"一周前",onClick:function(e){var t=new Date;t.setTime(t.getTime()-6048e5),e.$emit("pick",t)}}]}}},created:function(){this.getList()},methods:{getList:function(){var e=this;Object(n["b"])(this.defaltQuery).then((function(t){t.list.forEach((function(e){1===e.video.type?e.video.type="日本影片":2===e.video.type?e.video.type="美国影片":3===e.video.type?e.video.type="动漫":4===e.video.type?e.video.type="小视频":5===e.video.type&&(e.video.type="图片"),e.creatTime=i(e.creatTime).format("YYYY-MM-DD HH:mm:ss")})),e.tableData=t}))},handleSizeChange:function(e){this.defaltQuery.pageSize=e,this.getList()},handleCurrentChange:function(e){this.defaltQuery.pageNum=e,this.getList()},deleteFavorite:function(e,t){var a=this;console.log(t),Object(l["b"])({id:t.id}).then((function(e){a.getList()})).catch((function(e){a.getList()}))},jump:function(e,t){sessionStorage.setItem("listQuery_user_favorite",JSON.stringify(this.listQuery)),sessionStorage.setItem("refresh_user_favorite",!0),"图片"!==t.type&&(sessionStorage.setItem("refresh_video_detail",!0),this.$router.push({path:"/video_detail/index",name:"影片详情",params:{id:t.videoId}}))}}},o=c,d=(a("ff9b"),a("2877")),u=Object(d["a"])(o,s,r,!1,null,"443adbf3",null);t["default"]=u.exports},4678:function(e,t,a){var s={"./af":"2bfb","./af.js":"2bfb","./ar":"8e73","./ar-dz":"a356","./ar-dz.js":"a356","./ar-kw":"423e","./ar-kw.js":"423e","./ar-ly":"1cfd","./ar-ly.js":"1cfd","./ar-ma":"0a84","./ar-ma.js":"0a84","./ar-sa":"8230","./ar-sa.js":"8230","./ar-tn":"6d83","./ar-tn.js":"6d83","./ar.js":"8e73","./az":"485c","./az.js":"485c","./be":"1fc1","./be.js":"1fc1","./bg":"84aa","./bg.js":"84aa","./bm":"a7fa","./bm.js":"a7fa","./bn":"9043","./bn.js":"9043","./bo":"d26a","./bo.js":"d26a","./br":"6887","./br.js":"6887","./bs":"2554","./bs.js":"2554","./ca":"d716","./ca.js":"d716","./cs":"3c0d","./cs.js":"3c0d","./cv":"03ec","./cv.js":"03ec","./cy":"9797","./cy.js":"9797","./da":"0f14","./da.js":"0f14","./de":"b469","./de-at":"b3eb","./de-at.js":"b3eb","./de-ch":"bb71","./de-ch.js":"bb71","./de.js":"b469","./dv":"598a","./dv.js":"598a","./el":"8d47","./el.js":"8d47","./en-SG":"cdab","./en-SG.js":"cdab","./en-au":"0e6b","./en-au.js":"0e6b","./en-ca":"3886","./en-ca.js":"3886","./en-gb":"39a6","./en-gb.js":"39a6","./en-ie":"e1d3","./en-ie.js":"e1d3","./en-il":"73332","./en-il.js":"73332","./en-nz":"6f50","./en-nz.js":"6f50","./eo":"65db","./eo.js":"65db","./es":"898b","./es-do":"0a3c","./es-do.js":"0a3c","./es-us":"55c9","./es-us.js":"55c9","./es.js":"898b","./et":"ec18","./et.js":"ec18","./eu":"0ff2","./eu.js":"0ff2","./fa":"8df48","./fa.js":"8df48","./fi":"81e9","./fi.js":"81e9","./fo":"0721","./fo.js":"0721","./fr":"9f26","./fr-ca":"d9f8","./fr-ca.js":"d9f8","./fr-ch":"0e49","./fr-ch.js":"0e49","./fr.js":"9f26","./fy":"7118","./fy.js":"7118","./ga":"5120","./ga.js":"5120","./gd":"f6b46","./gd.js":"f6b46","./gl":"8840","./gl.js":"8840","./gom-latn":"0caa","./gom-latn.js":"0caa","./gu":"e0c5","./gu.js":"e0c5","./he":"c7aa","./he.js":"c7aa","./hi":"dc4d","./hi.js":"dc4d","./hr":"4ba9","./hr.js":"4ba9","./hu":"5b14","./hu.js":"5b14","./hy-am":"d6b6","./hy-am.js":"d6b6","./id":"5038","./id.js":"5038","./is":"0558","./is.js":"0558","./it":"6e98","./it-ch":"6f12","./it-ch.js":"6f12","./it.js":"6e98","./ja":"079e","./ja.js":"079e","./jv":"b540","./jv.js":"b540","./ka":"201b","./ka.js":"201b","./kk":"6d79","./kk.js":"6d79","./km":"e81d","./km.js":"e81d","./kn":"3e92","./kn.js":"3e92","./ko":"22f8","./ko.js":"22f8","./ku":"2421","./ku.js":"2421","./ky":"9609","./ky.js":"9609","./lb":"440c","./lb.js":"440c","./lo":"b29d","./lo.js":"b29d","./lt":"26f9","./lt.js":"26f9","./lv":"b97c","./lv.js":"b97c","./me":"293c","./me.js":"293c","./mi":"688b","./mi.js":"688b","./mk":"6909","./mk.js":"6909","./ml":"02fb","./ml.js":"02fb","./mn":"958b","./mn.js":"958b","./mr":"39bd","./mr.js":"39bd","./ms":"ebe4","./ms-my":"6403","./ms-my.js":"6403","./ms.js":"ebe4","./mt":"1b45","./mt.js":"1b45","./my":"8689","./my.js":"8689","./nb":"6ce3","./nb.js":"6ce3","./ne":"3a39","./ne.js":"3a39","./nl":"facd","./nl-be":"db29","./nl-be.js":"db29","./nl.js":"facd","./nn":"b84c","./nn.js":"b84c","./pa-in":"f3ff","./pa-in.js":"f3ff","./pl":"8d57","./pl.js":"8d57","./pt":"f260","./pt-br":"d2d4","./pt-br.js":"d2d4","./pt.js":"f260","./ro":"972c","./ro.js":"972c","./ru":"957c","./ru.js":"957c","./sd":"6784","./sd.js":"6784","./se":"ffff","./se.js":"ffff","./si":"eda5","./si.js":"eda5","./sk":"7be6","./sk.js":"7be6","./sl":"8155","./sl.js":"8155","./sq":"c8f3","./sq.js":"c8f3","./sr":"cf1e9","./sr-cyrl":"13e9","./sr-cyrl.js":"13e9","./sr.js":"cf1e9","./ss":"52bd","./ss.js":"52bd","./sv":"5fbd","./sv.js":"5fbd","./sw":"74dc","./sw.js":"74dc","./ta":"3de5","./ta.js":"3de5","./te":"5cbb","./te.js":"5cbb","./tet":"576c","./tet.js":"576c","./tg":"3b1b","./tg.js":"3b1b","./th":"10e8","./th.js":"10e8","./tl-ph":"0f38","./tl-ph.js":"0f38","./tlh":"cf75","./tlh.js":"cf75","./tr":"0e81","./tr.js":"0e81","./tzl":"cf51","./tzl.js":"cf51","./tzm":"c109","./tzm-latn":"b53d","./tzm-latn.js":"b53d","./tzm.js":"c109","./ug-cn":"6117","./ug-cn.js":"6117","./uk":"ada2","./uk.js":"ada2","./ur":"5294","./ur.js":"5294","./uz":"2e8c","./uz-latn":"010e","./uz-latn.js":"010e","./uz.js":"2e8c","./vi":"2921","./vi.js":"2921","./x-pseudo":"fd7e","./x-pseudo.js":"fd7e","./yo":"7f33","./yo.js":"7f33","./zh-cn":"5c3a","./zh-cn.js":"5c3a","./zh-hk":"49ab","./zh-hk.js":"49ab","./zh-tw":"90ea","./zh-tw.js":"90ea"};function r(e){var t=n(e);return a(t)}function n(e){var t=s[e];if(!(t+1)){var a=new Error("Cannot find module '"+e+"'");throw a.code="MODULE_NOT_FOUND",a}return t}r.keys=function(){return Object.keys(s)},r.resolve=n,e.exports=r,r.id="4678"},a5d6:function(e,t,a){},bc50:function(e,t,a){"use strict";a.d(t,"a",(function(){return r})),a.d(t,"b",(function(){return n})),a.d(t,"c",(function(){return l}));var s=a("b775");function r(e){return Object(s["a"])({url:"/userFavorite/addFavorite",method:"post",data:e})}function n(e){return Object(s["a"])({url:"/userFavorite/deleteFavorite",method:"get",params:e})}function l(e){return Object(s["a"])({url:"/userFavorite/getList",method:"get",params:e})}},ff9b:function(e,t,a){"use strict";var s=a("a5d6"),r=a.n(s);r.a}}]);