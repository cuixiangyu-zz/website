(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d79c6b88"],{"30cf":function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return t.tableData?i("div",{staticClass:"main"},[i("el-table",{staticStyle:{width:"100%"},attrs:{border:"",data:t.tableData.list}},[i("el-table-column",{attrs:{align:"center",prop:"clinic.clinicName",label:"诊室",width:"180"}}),t._v(" "),i("el-table-column",{attrs:{prop:"clinic.clinicNo",label:"诊室号",width:"180"}}),t._v(" "),i("el-table-column",{attrs:{prop:"doctor.name",label:"签到医生",width:"180"}}),t._v(" "),i("el-table-column",{attrs:{prop:"edit","min-width":"120",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(i){return t.doctorSignn(e.$index,e.row)}}},[t._v("签到")]),t._v(" "),i("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(i){return t.doctorSignn(e.$index,e.row)}}},[t._v("替换医生")]),t._v(" "),i("el-button",{attrs:{size:"mini"},on:{click:function(i){return t.backSign(e.$index,e.row)}}},[t._v("签退")])]}}],null,!1,2781752806)})],1),t._v(" "),i("el-dialog",{attrs:{title:"医生签到",visible:t.doctorSignFormVisible,width:"30%"},on:{"update:visible":function(e){t.doctorSignFormVisible=e}}},[i("el-form",{attrs:{model:t.signDoctor}},[i("el-form-item",{attrs:{label:"医生姓名"}},[i("el-select",{attrs:{placeholder:"请选择","value-key":"id"},model:{value:t.signDoctor.doctorId,callback:function(e){t.$set(t.signDoctor,"doctorId",e)},expression:"signDoctor.doctorId"}},t._l(t.doctorList,(function(t){return i("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1)],1),t._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:t.confirmSign}},[t._v("确定")]),t._v(" "),i("el-button",{on:{click:function(e){t.doctorSignFormVisible=!1}}},[t._v("取 消")])],1)],1),t._v(" "),i("el-pagination",{staticStyle:{"margin-top":"20px"},attrs:{total:t.tableData.total,"current-page":t.defaultQuery.pageNum,"page-sizes":[1,5,10,20,30],"page-size":t.defaultQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1):t._e()},o=[],c=i("b775");function r(t){return Object(c["a"])({url:"/doctorSign/getClinicBySectionId",method:"get",params:t})}function a(t){return Object(c["a"])({url:"/doctorSign/doctorSignin",method:"get",params:t})}function l(t){return Object(c["a"])({url:"/doctorSign/doctorSignOut",method:"post",params:t})}function s(t){return Object(c["a"])({url:"/doctorSign/getNotSignDoctorBySectionId",method:"get",params:t})}var u={data:function(){return{tableData:null,doctorSignFormVisible:!1,defaultQuery:{pageNum:1,pageSize:10,sectionId:""},selectDoctor:{doctor:""},doctorList:{},signDoctor:{doctorId:"",clinicId:""}}},created:function(){this.getClinic()},methods:{getClinic:function(){var t=this;r(this.defaultQuery).then((function(e){t.tableData=e}))},handleSizeChange:function(t){this.defaultQuery.pageSize=t,this.getClinic()},handleCurrentChange:function(t){this.defaultQuery.pageNum=t,this.getClinic()},doctorSignn:function(t,e){console.log(e),this.signDoctor.doctorId="",this.signDoctor.clinicId=e.clinic.id,this.defaultQuery.sectionId=e.clinic.sectionsId,this.getNotSignDoctorBySectionId(),this.doctorSignFormVisible=!0},confirmSign:function(){var t=this;this.doctorSignFormVisible=!1,a(this.signDoctor).then((function(e){t.$message({message:"上班成功!",type:"success"}),t.getClinic()}))},backSign:function(t,e){var i=this;this.$confirm("医生确定下班？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){i.signDoctor.doctorId=e.doctor.id,l(i.signDoctor).then((function(t){i.$message({message:"下班成功!",type:"success"}),i.getClinic()}))}))},getNotSignDoctorBySectionId:function(){var t=this;s(this.defaultQuery).then((function(e){t.doctorList=e}))}}},d=u,g=(i("8a27"),i("2877")),f=Object(g["a"])(d,n,o,!1,null,"2bda98ee",null);e["default"]=f.exports},"8a27":function(t,e,i){"use strict";var n=i("a54a"),o=i.n(n);o.a},a54a:function(t,e,i){}}]);