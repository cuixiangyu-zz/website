webpackJsonp([13],{100:function(e,t,n){"use strict";var i=n(1),r=n(237),a=n(234),o=n.n(a),s=function(e){return n.e(10).then(function(){var t=[n(254)];e.apply(null,t)}.bind(this)).catch(n.oe)},u=function(e){return n.e(2).then(function(){var t=[n(258)];e.apply(null,t)}.bind(this)).catch(n.oe)},c=function(e){return n.e(4).then(function(){var t=[n(257)];e.apply(null,t)}.bind(this)).catch(n.oe)},l=function(e){return n.e(0).then(function(){var t=[n(251)];e.apply(null,t)}.bind(this)).catch(n.oe)},p=function(e){return n.e(1).then(function(){var t=[n(261)];e.apply(null,t)}.bind(this)).catch(n.oe)},d=function(e){return n.e(3).then(function(){var t=[n(253)];e.apply(null,t)}.bind(this)).catch(n.oe)},m=function(e){return n.e(5).then(function(){var t=[n(252)];e.apply(null,t)}.bind(this)).catch(n.oe)},f=function(e){return n.e(6).then(function(){var t=[n(256)];e.apply(null,t)}.bind(this)).catch(n.oe)},h=function(e){return n.e(7).then(function(){var t=[n(255)];e.apply(null,t)}.bind(this)).catch(n.oe)},v=function(e){return n.e(8).then(function(){var t=[n(260)];e.apply(null,t)}.bind(this)).catch(n.oe)},g=function(e){return n.e(9).then(function(){var t=[n(259)];e.apply(null,t)}.bind(this)).catch(n.oe)},w=function(e){return n.e(11).then(function(){var t=[n(250)];e.apply(null,t)}.bind(this)).catch(n.oe)};i.default.use(r.a);var b=function(e,t,n){if(n)return n;var i={};return e.hash&&(i.selector=e.hash),e.matched.some(function(e){return e.meta.scrollToTop})&&(i.x=0,i.y=0),i};t.a=new r.a({mode:"history",scrollBehavior:b,routes:[{path:"/",name:"login",component:o.a},{path:"/home",component:s,mate:{keepAlive:!0},children:[{path:"/",name:"joblist",component:u},{path:"/company",name:"company",component:f},{path:"/americanVideoList",name:"americanVideoList",component:l},{path:"/pornHubVideoList",name:"pornHubVideoList",component:p},{path:"/comicList",name:"comicList",component:d},{path:"/comicDetail",name:"comicDetail",component:m},{path:"/message",component:v},{path:"/aboutme",name:"me",component:w}]},{path:"/detail/:jobId",name:"detail",component:c,meta:{scrollToTop:!0}},{path:"/comdetail/:id",name:"comDetail",component:h},{path:"/meschatDetail",name:"meschatDetail",component:g}]})},101:function(e,t){},102:function(e,t){},103:function(e,t){},104:function(e,t){},106:function(e,t,n){function i(e){n(215)}var r=n(61)(n(149),n(235),i,"data-v-0df5941a",null);e.exports=r.exports},108:function(e,t,n){"use strict";var i=n(156),r=n.n(i),a=n(58),o=n.n(a),s=n(60),u=(n.n(s),n(59)),c=o.a.create({baseURL:"http://192.168.1.17:5002/website",withCredentials:!0,timeout:3e5});c.interceptors.request.use(function(e){return u.a.getters.token,e},function(e){return console.log(e),r.a.reject(e)}),c.interceptors.response.use(function(e){var t=e.data;if(console.log(t),200!==e.status)return n.i(s.Message)({message:t.message||"Error",type:"error",duration:5e3}),r.a.reject(new Error(t.message||"Error"));if(4001===t.code)n.i(s.Message)({message:t.msg||"Error",type:"error",duration:5e3}),u.a.dispatch("user/resetToken").then(function(){location.reload()});else{if(4002!==t.code&&4003!==t.code&&4004!==t.code&&4005!==t.code)return 0===t.code?(1===t.msgType&&n.i(s.Message)({message:t.msg||"成功",type:"success",duration:5e3}),t.data):"multipart/form-data"===t.type?t:(n.i(s.Message)({message:t.msg||"Error",type:"error",duration:5e3}),r.a.reject(new Error(t.message||"Error")));n.i(s.Message)({message:t.msg||"Error",type:"error",duration:5e3})}},function(e){return console.log("err"+e.response.data),n.i(s.Message)({message:e.response.data.msg||"Error",type:"error",duration:5e3}),r.a.reject(e)}),t.a=c},149:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"app"}},150:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n(152),a=n(151);t.default={data:function(){return{isNhad:!1,userlist:[],userules:{userid:"",userpwd:""},loginForm:{userName:"",password:""},lable:"+86"}},components:{},watch:{$route:function(e,t){var n=e.path.split("/").length,i=t.path.split("/").length;this.transitionName=n<i?"slide-right":"slide-left"},userlist:{handler:function(e){r.a.save(e)},deep:!0}},methods:{infologin:function(){var e=this;console.log(this.loginForm),n.i(a.a)(this.loginForm).then(function(t){e.$router.push({path:"/home"})})},adduser:function(){this.haduser(),this.isNhad&&this.userules.userid&&this.userules.userpwd&&(this.userlist.push({userid:this.userules.userid,userpwd:this.userules.userpwd}),console.log(this.userlist))},haduser:function(){var e=this,t=r.a.fetch();t.length;t.forEach(function(t){e.userules.userid!==i.userid?e.isNhad=!0:e.isNhad=!1})}},mounted:function(){var e={session_id:Math.random().toString(32).slice(2),username:"admin",password:"123456"};this.$store.commit("logIn/getSession_id",e.session_id),this.userlist.push(e),this.userules.userid=e.userid,this.userules.userpwd=e.userpwd}}},151:function(e,t,n){"use strict";function i(e){return n.i(r.a)({url:"/loginMain",method:"post",params:e})}t.a=i;var r=n(108)},152:function(e,t,n){"use strict";var i=n(109),r=n.n(i),a={save:function(e){return window.sessionStorage.setItem("user-id",r()(e))},fetch:function(){return JSON.parse(window.sessionStorage.getItem("user-id")||"[]")}};t.a=a},153:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n(1),r=n(106),a=n.n(r),o=n(100),s=n(58),u=n.n(s),c=n(59),l=n(60),p=n.n(l),d=n(102),m=(n.n(d),n(107)),f=n.n(m),h=n(99),v=(n.n(h),n(104)),g=(n.n(v),n(101)),w=(n.n(g),n(105)),b=n.n(w),y=n(103);n.n(y);i.default.use(p.a),i.default.use(f.a),i.default.prototype.$http=u.a,i.default.use(b.a),window.alert=function(e){i.default.$toast({message:e,duration:1e3})},i.default.config.productionTip=!1,new i.default({el:"#app",router:o.a,store:c.a,data:{},template:"<App/>",components:{App:a.a}})},154:function(e,t,n){"use strict";var i={session_id:"",userid:"",userImage:""},r={},a={},o={getSession_id:function(e,t){e.session_id=t},getUser:function(e,t){e.userid=t},getUserImage:function(e,t){e.userImage=t}};t.a={namespaced:!0,state:i,getters:r,actions:a,mutations:o}},215:function(e,t){},216:function(e,t){},234:function(e,t,n){function i(e){n(216)}var r=n(61)(n(150),n(236),i,"data-v-5361e148",null);e.exports=r.exports},235:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]}},236:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"login-bg"},[n("div",{attrs:{id:"login"}},[n("form",{attrs:{action:""}},[n("h3",[e._v("BOSS直聘")]),e._v(" "),n("ul",{staticClass:"info-login"},[n("li",[n("b",[e._v("\n            "+e._s(e.lable)+"\n            "),n("i",{staticClass:"icon-down"})]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.loginForm.userName,expression:"loginForm.userName"}],attrs:{type:"text",placeholder:"用户名"},domProps:{value:e.loginForm.userName},on:{input:function(t){t.target.composing||e.$set(e.loginForm,"userName",t.target.value)}}})]),e._v(" "),n("li",[n("span"),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.loginForm.password,expression:"loginForm.password"}],attrs:{type:"password",placeholder:"密码"},domProps:{value:e.loginForm.password},on:{input:function(t){t.target.composing||e.$set(e.loginForm,"password",t.target.value)}}})]),e._v(" "),n("li",[n("input",{attrs:{type:"submit",value:"进入"},on:{click:function(t){return t.preventDefault(),e.infologin(t)}}})])])])]),e._v(" "),n("div",{staticClass:"login-footer flex_parent"},[n("router-link",{staticClass:"flex_child",attrs:{to:""}},[n("span",[e._v("用户协议")])]),e._v(" "),n("router-link",{staticClass:"flex_child",attrs:{to:""}},[n("span",[e._v("密码登录")])])],1)])},staticRenderFns:[]}},247:function(e,t){},59:function(e,t,n){"use strict";var i=n(1),r=n(244),a=n(154);i.default.use(r.a);var o={},s={},u={},c=new r.a.Store({modules:{logIn:a.a},actions:s,state:o,mutations:u});t.a=c},99:function(e,t){!function(e,t){function n(){var t=a.getBoundingClientRect().width;t/u>540&&(t=540*u);var n=t/10;a.style.fontSize=n+"px",l.rem=e.rem=n}var i,r=e.document,a=r.documentElement,o=r.querySelector('meta[name="viewport"]'),s=r.querySelector('meta[name="flexible"]'),u=0,c=0,l=t.flexible||(t.flexible={});if(o){console.warn("将根据已有的meta标签来设置缩放比例");var p=o.getAttribute("content").match(/initial\-scale=([\d\.]+)/);p&&(c=parseFloat(p[1]),u=parseInt(1/c))}else if(s){var d=s.getAttribute("content");if(d){var m=d.match(/initial\-dpr=([\d\.]+)/),f=d.match(/maximum\-dpr=([\d\.]+)/);m&&(u=parseFloat(m[1]),c=parseFloat((1/u).toFixed(2))),f&&(u=parseFloat(f[1]),c=parseFloat((1/u).toFixed(2)))}}if(!u&&!c){var h=(e.navigator.appVersion.match(/android/gi),e.navigator.appVersion.match(/iphone/gi)),v=e.devicePixelRatio;u=h?v>=3&&(!u||u>=3)?3:v>=2&&(!u||u>=2)?2:1:1,c=1/u}if(a.setAttribute("data-dpr",u),!o)if(o=r.createElement("meta"),o.setAttribute("name","viewport"),o.setAttribute("content","initial-scale="+c+", maximum-scale="+c+", minimum-scale="+c+", user-scalable=no"),a.firstElementChild)a.firstElementChild.appendChild(o);else{var g=r.createElement("div");g.appendChild(o),r.write(g.innerHTML)}e.addEventListener("resize",function(){clearTimeout(i),i=setTimeout(n,300)},!1),e.addEventListener("pageshow",function(e){e.persisted&&(clearTimeout(i),i=setTimeout(n,300))},!1),"complete"===r.readyState?r.body.style.fontSize=12*u+"px":r.addEventListener("DOMContentLoaded",function(e){r.body.style.fontSize=12*u+"px"},!1),n(),l.dpr=e.dpr=u,l.refreshRem=n,l.rem2px=function(e){var t=parseFloat(e)*this.rem;return"string"==typeof e&&e.match(/rem$/)&&(t+="px"),t},l.px2rem=function(e){var t=parseFloat(e)/this.rem;return"string"==typeof e&&e.match(/px$/)&&(t+="rem"),t}}(window,window.lib||(window.lib={}))}},[153]);