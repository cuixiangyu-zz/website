(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-58e02d71"],{9384:function(e,t,i){},b561:function(e,t,i){"use strict";i.d(t,"c",(function(){return c})),i.d(t,"d",(function(){return l})),i.d(t,"b",(function(){return o})),i.d(t,"a",(function(){return r}));var n=i("b775");function c(e){return Object(n["a"])({url:"/sections/findList",method:"post",params:e})}function l(e){return Object(n["a"])({url:"/sections/findPage",method:"post",params:e})}function o(e){return Object(n["a"])({url:"/sections/findListByDoctorId",method:"post",params:e})}function r(e){return Object(n["a"])({url:"/sections/exportExcel",method:"post",params:e,responseType:"blob"})}},e5ce:function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return e.tableData?i("div",{staticClass:"main"},[i("el-form",{attrs:{inline:!0}},[i("el-form-item",{attrs:{label:"科室"}},[i("el-select",{attrs:{clearable:"",filterable:"",placeholder:"请选择"},model:{value:e.defaultQuery.sectionsId,callback:function(t){e.$set(e.defaultQuery,"sectionsId",t)},expression:"defaultQuery.sectionsId"}},e._l(e.options,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),e._v(" "),i("el-form-item",{attrs:{label:"诊室号"}},[i("el-input",{attrs:{clearable:"",placeholder:""},model:{value:e.defaultQuery.clinicNo,callback:function(t){e.$set(e.defaultQuery,"clinicNo",t)},expression:"defaultQuery.clinicNo"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"诊室名称"}},[i("el-input",{attrs:{clearable:"",placeholder:""},model:{value:e.defaultQuery.clinicName,callback:function(t){e.$set(e.defaultQuery,"clinicName",t)},expression:"defaultQuery.clinicName"}})],1),e._v(" "),i("el-form-item",{staticStyle:{"margin-right":"50px"}},[i("el-button",{attrs:{type:"primary"},on:{click:e.getClinic}},[e._v("查询")])],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.addClinic}},[e._v("新增诊室")])],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.exportExcel}},[e._v("导出")])],1)],1),e._v(" "),i("el-table",{staticStyle:{width:"100%"},attrs:{border:"",data:e.tableData.list}},[i("el-table-column",{attrs:{prop:"clinicName",label:"诊室",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"clinicNo",label:"诊室号",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"hisCode",label:"HIS编码"}}),e._v(" "),i("el-table-column",{attrs:{prop:"sectionname",label:"科室"}}),e._v(" "),i("el-table-column",{attrs:{prop:"address","min-width":"120",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(i){return e.editClinic(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),i("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(i){return e.deleteClinic(t.$index,t.row)}}},[e._v("删除")]),e._v(" "),i("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(i){return e.assignDevice(t.$index,t.row)}}},[e._v("分配设备")])]}}],null,!1,363249743)})],1),e._v(" "),i("el-pagination",{staticStyle:{"margin-top":"20px"},attrs:{total:e.tableData.total,"current-page":e.defaultQuery.pageNum,"page-sizes":[1,5,10,20,30],"page-size":e.defaultQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),i("el-dialog",{attrs:{title:e.formTitle,visible:e.clinicAddFormVisible,width:"30%"},on:{"update:visible":function(t){e.clinicAddFormVisible=t}}},[i("el-form",{ref:"clinicForm",staticClass:"demo-ruleForm",attrs:{model:e.formQuery,rules:e.formRules}},["编辑诊室"===e.formTitle?i("el-form-item",{attrs:{hidden:"true",label:"ID","label-width":"100px"}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:e.formQuery.id,callback:function(t){e.$set(e.formQuery,"id",t)},expression:"formQuery.id"}})],1):e._e(),e._v(" "),i("el-form-item",{attrs:{label:"诊室号","label-width":"100px",prop:"clinicNo"}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:e.formQuery.clinicNo,callback:function(t){e.$set(e.formQuery,"clinicNo",t)},expression:"formQuery.clinicNo"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"诊室名","label-width":"100px",prop:"clinicName"}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:e.formQuery.clinicName,callback:function(t){e.$set(e.formQuery,"clinicName",t)},expression:"formQuery.clinicName"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"科室","label-width":"100px",prop:"sectionsId"}},[i("el-select",{attrs:{filterable:"",placeholder:"请选择"},model:{value:e.formQuery.sectionsId,callback:function(t){e.$set(e.formQuery,"sectionsId",t)},expression:"formQuery.sectionsId"}},e._l(e.options,(function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1)],1),e._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary",disabled:e.btnLoading},on:{click:e.confirmForm}},[e._v("保 存")]),e._v(" "),i("el-button",{on:{click:e.cancle}},[e._v("取 消")])],1)],1),e._v(" "),i("el-dialog",{attrs:{title:"分配设备",visible:e.changeDeviceVisible,width:"30%"},on:{"update:visible":function(t){e.changeDeviceVisible=t}}},[i("el-cascader-multi",{attrs:{data:e.deviceList,filterable:"","reserve-keyword":""},model:{value:e.clinicDevicepojo.checkList,callback:function(t){e.$set(e.clinicDevicepojo,"checkList",t)},expression:"clinicDevicepojo.checkList"}}),e._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:e.addDevices}},[e._v("保 存")]),e._v(" "),i("el-button",{on:{click:function(t){e.changeDeviceVisible=!1}}},[e._v("取 消")])],1)],1)],1):e._e()},c=[],l=(i("7f7f"),i("ac6a"),i("b775"));function o(e){return Object(l["a"])({url:"/clinic/findList",method:"post",params:e})}function r(e){return Object(l["a"])({url:"/clinic/save",method:"post",data:e})}function a(e){return Object(l["a"])({url:"/clinic/update",method:"post",data:e})}function s(e){return Object(l["a"])({url:"/clinic/delete",method:"post",params:e})}function u(e){return Object(l["a"])({url:"/clinic/getClinicDevice",method:"get",params:e})}function d(e){return Object(l["a"])({url:"/clinic/setClinicDevice",method:"post",data:e})}function f(e){return Object(l["a"])({url:"/clinic/exportExcel",method:"post",params:e,responseType:"blob"})}var m=i("b561"),p={data:function(){return{tableData:null,defaultQuery:{pageNum:1,pageSize:10,sectionsId:"",clinicNo:"",clinicName:""},formRules:{clinicNo:{required:!0,message:"请输入诊室号！",trigger:"blur"},clinicName:{required:!0,message:"请输入诊室名称！",trigger:"blur"},sectionsId:{required:!0,message:"请选择科室！",trigger:"blur"}},clinicAddFormVisible:!1,formQuery:{id:"",clinicName:"",clinicNo:"",sectionsId:""},secitonsOption:"",btnLoading:!1,formTitle:"新增诊室",deviceList:"",changeDeviceVisible:!1,selectedOptions:null,props:{multiple:!0},clinicDevicepojo:{checkList:null,clinicId:null},imgSrc:"E:/picture/_MG_0138.jpg"}},created:function(){},methods:{getClinic:function(){var e=this;o(this.defaultQuery).then((function(t){t.list.forEach((function(t){e.options.forEach((function(e){t.sectionsId===e.id&&(t.sectionname=e.name)}))})),e.tableData=t}))},getSections:function(){var e=this;Object(m["c"])().then((function(t){e.options=t,e.getClinic()}))},handleSizeChange:function(e){this.defaultQuery.pageSize=e,this.getClinic()},handleCurrentChange:function(e){this.defaultQuery.pageNum=e,this.getClinic()},deleteClinic:function(e,t){var i=this;this.$confirm("此操作将删除该诊室, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s({id:t.id}).then((function(e){i.getClinic()}))}))},addClinic:function(){this.formTitle="新增诊室",this.clinicAddFormVisible=!0,this.formQuery={id:"",clinicName:"",clinicNo:"",sectionsId:""}},editClinic:function(e,t){var i=t.id,n=t.clinicName,c=t.clinicNo,l=t.sectionsId;this.formQuery={id:i,clinicName:n,clinicNo:c,sectionsId:l},this.formTitle="编辑诊室",this.clinicAddFormVisible=!0},exportExcel:function(){f(this.defaultQuery).then((function(e){if(e){var t=window.URL.createObjectURL(new Blob([e]));console.log(e.fileNames);var i=document.createElement("a");i.style.display="none",i.href=t,i.setAttribute("download","诊室信息.xlsx"),document.body.appendChild(i),i.click()}}))},confirmForm:function(){var e=this;this.$refs.clinicForm.validate((function(t){if(!t)return!1;e.btnLoading=!0,"新增诊室"===e.formTitle?r(e.formQuery).then((function(t){e.clinicAddFormVisible=!1,e.btnLoading=!1,e.getClinic()})).catch((function(){e.btnLoading=!1})):a(e.formQuery).then((function(t){e.clinicAddFormVisible=!1,e.btnLoading=!1,e.getClinic()})).catch((function(){e.btnLoading=!1}))}))},resetForm:function(){this.$refs.clinicForm.resetFields(),this.$refs.clinicForm.clearValidate()},cancle:function(){this.resetForm(),this.clinicAddFormVisible=!1},assignDevice:function(e,t){var i=this;this.clinicDevicepojo.clinicId=t.id,u({id:t.id}).then((function(e){console.log(e),i.changeDeviceVisible=!0,i.deviceList=e.childList,console.log(i.clinicDevicepojo),i.clinicDevicepojo.checkList=e.checkList}))},confirmChangeDevice:function(){},addDevices:function(){var e=this;d(this.clinicDevicepojo).then((function(t){e.changeDeviceVisible=!1,e.getClinic()}))},changeDevice:function(e){console.log(e),console.log(this.selectedOptions)}}},b=p,h=(i("f64c"),i("2877")),v=Object(h["a"])(b,n,c,!1,null,"47ff16e2",null);t["default"]=v.exports},f64c:function(e,t,i){"use strict";var n=i("9384"),c=i.n(n);c.a}}]);