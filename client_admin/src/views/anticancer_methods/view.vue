<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','anti_cancer_title') || $check_field('add','anti_cancer_title') || $check_field('set','anti_cancer_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="抗癌标题" prop="anti_cancer_title">
												<el-input id="anti_cancer_title" v-model="form['anti_cancer_title']" placeholder="请输入抗癌标题"
							  v-if="user_group === '管理员' || (form['anticancer_methods_id'] && $check_field('set','anti_cancer_title')) || (!form['anticancer_methods_id'] && $check_field('add','anti_cancer_title'))" :disabled="disabledObj['anti_cancer_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','anti_cancer_title')">{{form['anti_cancer_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','anticancer_type') || $check_field('add','anticancer_type') || $check_field('set','anticancer_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="抗癌类型" prop="anticancer_type">
								<el-select id="anticancer_type" v-model="form['anticancer_type']"
						v-if="user_group === '管理员' || (form['anticancer_methods_id'] && $check_field('set','anticancer_type')) || (!form['anticancer_methods_id'] && $check_field('add','anticancer_type'))">
						<el-option v-for="o in list_anticancer_type" :key="o['anticancer_type']" :label="o['anticancer_type']"
							:value="o['anticancer_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','anticancer_type')">{{form['anticancer_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo') || $check_field('add','cover_photo') || $check_field('set','cover_photo')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo">
								<el-upload :disabled="disabledObj['cover_photo_isDisabled']" id="cover_photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['anticancer_methods_id'] && $check_field('set','cover_photo')) || (!form['anticancer_methods_id'] && $check_field('add','cover_photo'))">
						<img v-if="form['cover_photo']" :src="$fullUrl(form['cover_photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo'])" :preview-src-list="[$fullUrl(form['cover_photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','release_time') || $check_field('add','release_time') || $check_field('set','release_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布时间" prop="release_time">
								<el-date-picker :disabled="disabledObj['release_time_isDisabled']" v-if="user_group === '管理员' || (form['anticancer_methods_id'] && $check_field('set','release_time')) || (!form['anticancer_methods_id'] && $check_field('add','release_time'))" id="release_time"
						v-model="form['release_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_time')">{{form['release_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','content_details') || $check_field('add','content_details') || $check_field('set','content_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="内容详情" prop="content_details">
					<quill-editor v-model.number="form['content_details']"
						v-if="user_group === '管理员' || (form['anticancer_methods_id'] && $check_field('set','content_details')) || (!form['anticancer_methods_id'] && $check_field('add','content_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','content_details')" v-html="form['content_details']"></div>
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
				field: "anticancer_methods_id",
				url_add: "~/api/anticancer_methods/add?",
				url_set: "~/api/anticancer_methods/set?",
				url_get_obj: "~/api/anticancer_methods/get_obj?",
				url_upload: "~/api/anticancer_methods/upload?",

				query: {
					"anticancer_methods_id": 0,
				},

				form: {
								"anti_cancer_title":  '', // 抗癌标题
										"anticancer_type":  '', // 抗癌类型
										"cover_photo":  '', // 封面图片
										"release_time":  '', // 发布时间
										"content_details":  '', // 内容详情
											"anticancer_methods_id": 0, // ID
						
				},
				disabledObj:{
								"anti_cancer_title_isDisabled": false,
										"anticancer_type_isDisabled": false,
										"cover_photo_isDisabled": false,
										"release_time_isDisabled": false,
										"content_details_isDisabled": false,
										},

	
						// 抗癌类型选项列表
				list_anticancer_type: [""],
	
		
		
		
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取抗癌类型列表
			 */
			async get_list_anticancer_type() {
				var json = await this.$get("~/api/anticancer_classification/get_list?");
				if(json.result && json.result.list){
					this.list_anticancer_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
						/**
			 * 上传封面图片
			 * @param {Object} param 图片参数
			 */
			upload_cover_photo(param){
						this.uploadFile(param.file, "cover_photo");
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
					bl = this.$check_action('/anticancer_methods/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/anticancer_methods/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/anticancer_methods/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/anticancer_methods/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/anticancer_methods/view','get');
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
						this.get_list_anticancer_type();
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
