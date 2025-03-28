<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="癌症科普"
				source_table="article"
			  ></list_result_search>

				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="抗癌论坛"
				source_table="forum"
			  ></list_result_search>

						  <list_result_search
				v-if="$check_action('/registered_users/list', 'get')"
				:list="result_registered_users_user_name"
				title="注册用户用户姓名"
				source_table="registered_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/registered_users/list', 'get')"
				:list="result_registered_users_user_gender"
				title="注册用户用户性别"
				source_table="registered_users"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/doctor_user/list', 'get')"
				:list="result_doctor_user_doctors_name"
				title="医生用户医生姓名"
				source_table="doctor_user"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/doctor_user/list', 'get')"
				:list="result_doctor_user_professional_title_of_doctor"
				title="医生用户医生职称"
				source_table="doctor_user"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/anticancer_classification/list', 'get')"
				:list="result_anticancer_classification_anticancer_type"
				title="抗癌分类抗癌类型"
				source_table="anticancer_classification"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/anticancer_methods/list', 'get')"
				:list="result_anticancer_methods_anti_cancer_title"
				title="抗癌方法抗癌标题"
				source_table="anticancer_methods"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/anticancer_methods/list', 'get')"
				:list="result_anticancer_methods_anticancer_type"
				title="抗癌方法抗癌类型"
				source_table="anticancer_methods"
			  ></list_result_search>
																					  <list_result_search
				v-if="$check_action('/doctors_comments/list', 'get')"
				:list="result_doctors_comments_patient_name"
				title="医生点评患者姓名"
				source_table="doctors_comments"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/doctors_comments/list', 'get')"
				:list="result_doctors_comments_release_time"
				title="医生点评发布时间"
				source_table="doctors_comments"
			  ></list_result_search>
												</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
						"result_registered_users_user_name":[],
								"result_registered_users_user_gender":[],
												"result_doctor_user_doctors_name":[],
											"result_doctor_user_professional_title_of_doctor":[],
									"result_anticancer_classification_anticancer_type":[],
									"result_anticancer_methods_anti_cancer_title":[],
								"result_anticancer_methods_anticancer_type":[],
																					"result_doctors_comments_patient_name":[],
								"result_doctors_comments_release_time":[],
										};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取抗癌论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},

				/**
	 * 获取user_name
	 */
	get_registered_users_user_name(){
		let url = "~/api/registered_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_users_user_name = json.result.list;
			result_registered_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_users_user_name = result_registered_users_user_name
		 	}
		});
	},
						/**
	 * 获取user_gender
	 */
	get_registered_users_user_gender(){
		let url = "~/api/registered_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_users_user_gender = json.result.list;
			result_registered_users_user_gender.map(o => o.title = o['user_gender'])
	  			this.result_registered_users_user_gender = result_registered_users_user_gender
		 	}
		});
	},
										/**
	 * 获取doctors_name
	 */
	get_doctor_user_doctors_name(){
		let url = "~/api/doctor_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "doctors_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_user_doctors_name = json.result.list;
			result_doctor_user_doctors_name.map(o => o.title = o['doctors_name'])
	  			this.result_doctor_user_doctors_name = result_doctor_user_doctors_name
		 	}
		});
	},
									/**
	 * 获取professional_title_of_doctor
	 */
	get_doctor_user_professional_title_of_doctor(){
		let url = "~/api/doctor_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "professional_title_of_doctor": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctor_user_professional_title_of_doctor = json.result.list;
			result_doctor_user_professional_title_of_doctor.map(o => o.title = o['professional_title_of_doctor'])
	  			this.result_doctor_user_professional_title_of_doctor = result_doctor_user_professional_title_of_doctor
		 	}
		});
	},
							/**
	 * 获取anticancer_type
	 */
	get_anticancer_classification_anticancer_type(){
		let url = "~/api/anticancer_classification/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "anticancer_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_anticancer_classification_anticancer_type = json.result.list;
			result_anticancer_classification_anticancer_type.map(o => o.title = o['anticancer_type'])
	  			this.result_anticancer_classification_anticancer_type = result_anticancer_classification_anticancer_type
		 	}
		});
	},
							/**
	 * 获取anti_cancer_title
	 */
	get_anticancer_methods_anti_cancer_title(){
		let url = "~/api/anticancer_methods/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "anti_cancer_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_anticancer_methods_anti_cancer_title = json.result.list;
			result_anticancer_methods_anti_cancer_title.map(o => o.title = o['anti_cancer_title'])
	  			this.result_anticancer_methods_anti_cancer_title = result_anticancer_methods_anti_cancer_title
		 	}
		});
	},
						/**
	 * 获取anticancer_type
	 */
	get_anticancer_methods_anticancer_type(){
		let url = "~/api/anticancer_methods/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "anticancer_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_anticancer_methods_anticancer_type = json.result.list;
			result_anticancer_methods_anticancer_type.map(o => o.title = o['anticancer_type'])
	  			this.result_anticancer_methods_anticancer_type = result_anticancer_methods_anticancer_type
		 	}
		});
	},
																			/**
	 * 获取patient_name
	 */
	get_doctors_comments_patient_name(){
		let url = "~/api/doctors_comments/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "patient_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctors_comments_patient_name = json.result.list;
			result_doctors_comments_patient_name.map(o => o.title = o['patient_name'])
	  			this.result_doctors_comments_patient_name = result_doctors_comments_patient_name
		 	}
		});
	},
						/**
	 * 获取release_time
	 */
	get_doctors_comments_release_time(){
		let url = "~/api/doctors_comments/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "release_time": this.query.word }, (json) => {
		  if (json.result) {
			var result_doctors_comments_release_time = json.result.list;
			result_doctors_comments_release_time.map(o => o.title = o['release_time'])
	  			this.result_doctors_comments_release_time = result_doctors_comments_release_time
		 	}
		});
	},
									
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
					this.get_registered_users_user_name();
							this.get_registered_users_user_gender();
											this.get_doctor_user_doctors_name();
										this.get_doctor_user_professional_title_of_doctor();
								this.get_anticancer_classification_anticancer_type();
								this.get_anticancer_methods_anti_cancer_title();
							this.get_anticancer_methods_anticancer_type();
																				this.get_doctors_comments_patient_name();
							this.get_doctors_comments_release_time();
									  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
				  this.get_registered_users_user_name();
						  this.get_registered_users_user_gender();
										  this.get_doctor_user_doctors_name();
									  this.get_doctor_user_professional_title_of_doctor();
							  this.get_anticancer_classification_anticancer_type();
							  this.get_anticancer_methods_anti_cancer_title();
						  this.get_anticancer_methods_anticancer_type();
																			  this.get_doctors_comments_patient_name();
						  this.get_doctors_comments_release_time();
										},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
