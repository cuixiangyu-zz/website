webpackJsonp([10],{254:function(t,a,n){function e(t){n(332)}var s=n(61)(n(284),n(371),e,"data-v-158b2688",null);t.exports=s.exports},284:function(t,a,n){"use strict";Object.defineProperty(a,"__esModule",{value:!0}),a.default={data:function(){return{msg:"主要的内容区域",willshow:!1,isloading:!1}},watch:{$route:function(t,a){var n=t.path.split("/").length,e=a.path.split("/").length;this.transitionName=n<e?"slide-right":"slide-left"}},computed:{},methods:{showitem:function(){},beforeEnter:function(t){console.log(t)}},mounted:function(){},created:function(){}}},307:function(t,a,n){a=t.exports=n(248)(!1),a.push([t.i,'.navbar[data-v-158b2688]{width:100%;position:fixed;left:0;bottom:0;background:#fff;z-index:10}.navbar[data-v-158b2688]:before{content:"";position:absolute;left:0;background:gray;width:100%;height:1px;-webkit-transform:scaleY(.3);transform:scaleY(.3);-webkit-transform-origin:0 0;transform-origin:0 0}.navbar a[data-v-158b2688]{color:gray;padding:.18rem 0;transition:all .5s;text-align:center}.navbar a span[data-v-158b2688]{display:block}.navbar a [class*=" icon-"][data-v-158b2688],.navbar a [class^=icon-][data-v-158b2688]{font-size:.6rem}.navbar a.router-link-active[data-v-158b2688]{color:#53cac3}.fade-enter-active[data-v-158b2688],.fade-leave-active[data-v-158b2688]{transition:opacity .5s}.fade-enter[data-v-158b2688],.fade-leave-to[data-v-158b2688]{opacity:0}',""])},332:function(t,a,n){var e=n(307);"string"==typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);n(249)("0632f822",e,!0,{})},371:function(t,a){t.exports={render:function(){var t=this,a=t.$createElement,n=t._self._c||a;return n("div",{attrs:{id:"home"}},[n("transition",{attrs:{name:"fade",mode:"out-in"}},[n("router-view")],1),t._v(" "),n("div",{staticClass:"navbar flex_parent"},[n("router-link",{staticClass:"flex_child",attrs:{to:"/home"}},[n("span",{staticClass:"icon-earth"}),t._v(" "),n("span",[t._v("日本")])]),t._v(" "),n("router-link",{staticClass:"flex_child",attrs:{to:"/americanVideoList"},nativeOn:{click:function(a){return t.showitem(a)}}},[n("span",{staticClass:"icon-company"}),t._v(" "),n("span",[t._v("欧美")])]),t._v(" "),n("router-link",{staticClass:"flex_child",attrs:{to:"/message"}},[n("span",{staticClass:"icon-message"}),t._v(" "),n("span",[t._v("动漫")])]),t._v(" "),n("router-link",{staticClass:"flex_child",attrs:{to:"/comicList"}},[n("span",{staticClass:"icon-me"}),t._v(" "),n("span",[t._v("漫画")])]),t._v(" "),n("router-link",{staticClass:"flex_child",attrs:{to:"/pornHubVideoList"}},[n("span",{staticClass:"icon-me"}),t._v(" "),n("span",[t._v("pornHub")])])],1)],1)},staticRenderFns:[]}}});