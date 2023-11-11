<template>
    <div class="home">
        <el-row :gutter="70">
            <el-col :span="12">
                <el-card class="grid-content">
                    <div class="avatar">
                        <el-avatar
                            :size="100"
                            shape="square"
                            :src="icon"
                            v-if="icon !== undefined"
                        >
                            <img src="http://localhost:9142/02.jpg" />
                        </el-avatar>
                        <el-avatar :size="70" v-else @error="errorHandler">{{
                            name
                        }}</el-avatar>
                    </div>
                    <div class="welcome-content" style="font-size: 22px">
                        <div>欢迎您， {{ name }}</div>
                        <div class="little">
                            我管理的学校/宿舍楼：{{ statistics.building }}
                        </div>
                        <div class="little">
                            宿舍总数： {{ statistics.roomNum }}
                        </div>
                    </div>

                    <img
                        :src="require('@/assets/home.png')"
                        style="width: 100%"
                    />
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="grid-content" style="padding-top: 25px">
                    <div style="text-align: center; margin-bottom: 15px">
                        <h1>欢迎访问学生宿舍管理系统</h1>
                    </div>
                    <hr />
                    <div style="margin-top: 15px;font-size: 1vw;">
                        第一条、按时归寝，按时就寝，严格执行宿舍的作息时间。<br /><br />
                        第二条、个人因事、因病要离开宿舍时，必须向寝室长汇报。<br /><br />
                        第三条、寝室成员都要讲究卫生，值日生按时清扫室内卫生，大家养成良好的卫生习惯。<br /><br />
                        第四条、不准私自调换房间、床位。个人贵重物品、现金财物及证件等要妥善保管，任何人未经主人同意不得擅自动用他人物品，宿舍成员离开宿舍必须关好门窗、锁好房门、切断电源。<br /><br />
                        第五条、被褥整洁，物品摆放整齐有序，屋内棚上、墙角无蜘蛛网灰尘，地面床下每天清扫一次，拖一次。床上、窗台、门、角提线无灰尘。<br /><br />
                        第六条、遵守纪律，服从领导，不会客、不留住、不吸烟、不饮酒、不打架、不骂人，团结互助礼貌待人。<br /><br />
                        第七条、宿舍成员间要文明交往、和睦相处、团结一致，互谅互让，互勉互助。<br /><br />
                        第八条、讲文明礼貌，不准随地吐谈、乱丢垃圾、大小便，室内设施不准随便移动拆卸。要保持室内整洁美观，一切日常用具要干净整齐有序，不准往窗外乱丢杂物，垃圾、剩饭(菜)要倒在指定地点。<br /><br />
                        第九条、宿舍内的走道、公共场所，禁止堆放杂物、禁止饲养宠。<br /><br />
                        第十条、注意安全严禁在寝室内用用火，寝室人员时刻要提高防火意识，平时经常检查寝室内，走廊，洗漱间的照明电路是否有破损。
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import store from "../store";
import { get } from "@/api/system/home";

export default {
    name: "Home",
    data() {
        return {
            statistics: {
                building: undefined,
                roomNum: undefined,
                totalStudent: undefined,
                livingStudent: undefined,
                leaveStudent: undefined,
                unprocessedRepair: undefined,
            },
        };
    },
    computed: {
        activeMenu() {
            const route = this.$route;
            const { path } = route;
            return path;
        },
        name() {
            return store.state.userName;
        },
        icon() {
            return store.state.userIcon;
        },
    },
    methods: {
        async get() {
            const { data } = await get();
            this.statistics = data;
        },
    },
    created() {
        this.get();
    },
};
</script>
<style scoped>
.avatar {
    margin: 20px 20px;
    float: left;
}
.home {
    padding: 20px;
}
.welcome-content {
    /*float: left;*/
    margin: 30px 0;
}
.grid-content {
    margin: 10px 0;
    position: relative;
}
.fixed:after {
    content: "";
    clear: both;
    display: block;
    visibility: hidden;
    height: 0;
    width: 0;
}
.little {
    font-size: 16px;
    color: #909399;
    margin-top: 8px;
}
.mid {
    text-align: center;
}
</style>
