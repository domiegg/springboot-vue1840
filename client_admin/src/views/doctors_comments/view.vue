<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','post_patient') || $check_field('add','post_patient') || $check_field('set','post_patient')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布患者" prop="post_patient">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_post_patient(form['post_patient']) }}
							<!--<el-input id="business_name" v-model="form['post_patient']" placeholder="请输入发布患者"-->
							<!--v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','post_patient')) || (!form['doctors_comments_id'] && $check_field('add','post_patient'))" :disabled="disabledObj['post_patient_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','post_patient')">{{form['post_patient']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','post_patient')) || (!form['doctors_comments_id'] && $check_field('add','post_patient'))" id="post_patient" v-model="form['post_patient']" :disabled="disabledObj['post_patient_isDisabled']">
								<el-option v-for="o in list_user_post_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','post_patient')" id="post_patient" v-model="form['post_patient']" :disabled="true">
								<el-option v-for="o in list_user_post_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="post_patient" v-model="form['post_patient']" :disabled="disabledObj['post_patient_isDisabled']">
							<el-option v-for="o in list_user_post_patient" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','patient_name') || $check_field('add','patient_name') || $check_field('set','patient_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="患者姓名" prop="patient_name">
												<el-input id="patient_name" v-model="form['patient_name']" placeholder="请输入患者姓名"
							  v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','patient_name')) || (!form['doctors_comments_id'] && $check_field('add','patient_name'))" :disabled="disabledObj['patient_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','patient_name')">{{form['patient_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布时间" prop="release_time">
								<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','release_time')) || (!form['doctors_comments_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','publish_content') || $check_field('add','publish_content') || $check_field('set','publish_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布内容" prop="publish_content">
								<el-input type="textarea" id="publish_content" v-model="form['publish_content']" placeholder="请输入发布内容"
						v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','publish_content')) || (!form['doctors_comments_id'] && $check_field('add','publish_content'))" :disabled="disabledObj['publish_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','publish_content')">{{form['publish_content']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','comment_response') || $check_field('add','comment_response') || $check_field('set','comment_response')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="点评回复" prop="comment_response">
								<el-input type="textarea" id="comment_response" v-model="form['comment_response']" placeholder="请输入点评回复"
						v-if="user_group === '管理员' || (form['doctors_comments_id'] && $check_field('set','comment_response')) || (!form['doctors_comments_id'] && $check_field('add','comment_response'))" :disabled="disabledObj['comment_response_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','comment_response')">{{form['comment_response']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "doctors_comments_id",
				url_add: "~/api/doctors_comments/add?",
				url_set: "~/api/doctors_comments/set?",
				url_get_obj: "~/api/doctors_comments/get_obj?",
				url_upload: "~/api/doctors_comments/upload?",

				query: {
					"doctors_comments_id": 0,
				},

				form: {
								"post_patient": 0, // 发布患者
										"patient_name":  '', // 患者姓名
										"release_time":  '', // 发布时间
										"publish_content":  '', // 发布内容
										"comment_response":  '', // 点评回复
											"doctors_comments_id": 0, // ID
						
				},
				disabledObj:{
								"post_patient_isDisabled": false,
										"patient_name_isDisabled": false,
										"release_time_isDisabled": false,
										"publish_content_isDisabled": false,
										"comment_response_isDisabled": false,
										},

	
					// 用户列表
				list_user_post_patient: [],
						// 用户组
				group_user_post_patient: "",
				
		
		
		
	
			}
		},
		methods: {


	
	
				/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_post_patient() {
                // if(this.user_group !== "管理员" && this.form["post_patient"] === 0) {
                //     this.form["post_patient"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=注册用户");
                if(json.result && json.result.list){
                    this.list_user_post_patient = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取注册用户用户组
			 */
			async get_group_user_post_patient() {
							this.form["post_patient"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					this.group_user_post_patient = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_post_patient(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_post_patient.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["post_patient"] = id
									_this.disabledObj['post_patient' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "post_patient") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_post_patient(id){
				var obj = this.list_user_post_patient.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
					
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
						        if (this.form["release_time"].indexOf("-")===-1){
          this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
        }
									$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


								        if(this.form["release_time"]=="0000-00-00"){
          this.form["release_time"] = null;
        }
				if(parseInt(this.form["release_time"]) > 9999){
					this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]),"yyyy-MM-dd")
				}
								


			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																					return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/doctors_comments/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctors_comments/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctors_comments/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/doctors_comments/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/doctors_comments/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
					this.get_list_user_post_patient();
					this.get_group_user_post_patient();
													},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
