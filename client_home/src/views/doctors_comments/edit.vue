<template>
	<div class="diy_edit page_doctors_comments" id="doctors_comments_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','post_patient') || $check_field('add','post_patient') || $check_field('get','post_patient')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发布患者:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_post_patient" :disabled="disabledObj['post_patient_isDisabled']" v-model="form['post_patient']" v-if="(form['post_patient'] && $check_field('set','post_patient')) || (!form['post_patient'] && $check_field('add','post_patient'))" >
								<option v-for="o in list_user_post_patient" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','post_patient')">{{ form['post_patient'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','patient_name') || $check_field('add','patient_name') || $check_field('get','patient_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								患者姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_patient_name" v-model="form['patient_name']" placeholder="请输入患者姓名" v-if="(form['patient_name'] && $check_field('set','patient_name')) || (!form['patient_name'] && $check_field('add','patient_name'))"  :disabled="disabledObj['patient_name_isDisabled']"/>
							<span v-else-if="$check_field('get','patient_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','release_time') || $check_field('add','release_time') || $check_field('get','release_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发布时间:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['release_time_isDisabled']" id="form_release_time" v-model="form['release_time']" placeholder="请输入发布时间" v-if="(form['release_time'] && $check_field('set','release_time')) || (!form['release_time'] && $check_field('add','release_time'))" />
							<span v-else-if="$check_field('get','release_time')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','publish_content') || $check_field('add','publish_content') || $check_field('get','publish_content')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								发布内容:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_publish_content" v-model="form['publish_content']" v-if="(form['publish_content'] && $check_field('set','publish_content')) || (!form['publish_content'] && $check_field('add','publish_content'))" :disabled="disabledObj['publish_content_isDisabled']" />
							<span v-else-if="$check_field('get','publish_content')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','comment_response') || $check_field('add','comment_response') || $check_field('get','comment_response')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								点评回复:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_comment_response" v-model="form['comment_response']" v-if="(form['comment_response'] && $check_field('set','comment_response')) || (!form['comment_response'] && $check_field('add','comment_response'))" :disabled="disabledObj['comment_response_isDisabled']" />
							<span v-else-if="$check_field('get','comment_response')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/doctors_comments/get_obj?",
				url_add: "~/api/doctors_comments/add?",
				url_set: "~/api/doctors_comments/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"post_patient": 0,
							"patient_name": "",
							"release_time": "",
							"publish_content": "",
							"comment_response": "",
						"doctors_comments_id": 0,
				},

				obj: {
						"post_patient": 0, // 发布患者
							"patient_name":  '', // 患者姓名
							"release_time": new Date().getTime(),
							"publish_content":  '', // 发布内容
							"comment_response":  '', // 点评回复
						"doctors_comments_id": 0,
				},

				// 表单字段
				form: {
						"post_patient": 0, // 发布患者
							"patient_name":  '', // 患者姓名
							"release_time": new Date().getTime(),
							"publish_content":  '', // 发布内容
							"comment_response":  '', // 点评回复
						"doctors_comments_id": 0,
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
									
				// ID字段
				field: "doctors_comments_id",

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
					async get_user_session_post_patient(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["post_patient"] = user_id
								_this.disabledObj['post_patient' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "post_patient") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/doctors_comments/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
						        if (JSON.stringify(this.form["release_time"]).indexOf("-")===-1) {
          this.form["release_time"] = this.$toTime(parseInt(this.form["release_time"]), "yyyy-MM-dd")
        }
					
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
					this.get_user_session_post_patient();
					this.get_list_user_post_patient();
															},
	}
</script>

<style>




</style>
