
Vue.prototype.mergeJSON = function (target, source) {
    for (var obj in source) {
        target[obj] = source[obj];
    }
    return target;
}
Vue.prototype.tableRender = function (datas, data) {
    var result = data.menuLevel;
    for (var item of datas) {
        if (item.keyName === result) {
            result = item.titleName;
            return result;
        }
    }
}

Vue.component('default-table', {
    props: ['columns', "checkbox"],
    created() {
        Vue.prototype.selection = "";
        Vue.prototype.selections = "";
        api.get("/button/array", { menuKey: this.currentMenu }).then(res => { this.buttons = res.data; });
        this.loadList();
    },
    data() {
        return {
            loading: false,
            page: { cur: 1, size: 10, total: 0 },
            datas: [],
            buttons: [],
        }
    },
    methods: {
        handle(e, name) {
            Vue.prototype.subTitle = e.target.innerText;
            var obj = this[name];
            if (obj) {
                obj.apply();
            } else {
                this.$emit(name);
            }
        },
        loadList() {
            this.loading = true;
            api.get(this.currentMenu, this.mergeJSON(this.page, this.queryInfo)).then(res => {
                this.datas = res.data.data;
                this.page.total = res.data.total;
                this.loading = false;
            }).catch(err => {
                console.error(err);
                this.loading = false;
                this.$Message.error('请求' + this.currentMenu + "失败");
            });
        },
        paging(page) {
            this.page = page;
            this.loadList(this.page);
        },
        query() {
            Vue.prototype.btnType = 1;
            Vue.prototype.readonly = false;
            Vue.prototype.selections = this.$refs.table.getSelection();
            this.app.$emit('switchView', this.currentMenu + '/query.html');
        },
        add() {
            Vue.prototype.btnType = 2;
            Vue.prototype.readonly = false;
            Vue.prototype.selections = this.$refs.table.getSelection();
            this.app.$emit('switchView', this.currentMenu + '/form.html');
        },
        edit() {
            Vue.prototype.btnType = 3;
            Vue.prototype.readonly = false;
            Vue.prototype.selections = this.$refs.table.getSelection();
            if (this.checkbox && this.selections.length > 0) {
                this.$Message.warn('请勾选中数据');
                return;
            } else if (!this.selection) {
                this.$Message.warn('请点击选中行');
                return;
            }
            this.app.$emit('switchView', this.currentMenu + '/form.html');
        },
        detail() {
            Vue.prototype.btnType = 4;
            Vue.prototype.readonly = true;
            Vue.prototype.selections = this.$refs.table.getSelection();
            if (this.checkbox && this.selections.length > 0) {
                this.$Message.warn('请勾选中数据');
                return;
            } else if (!this.selection) {
                this.$Message.warn('请点击选中行');
                return;
            }
            this.app.$emit('switchView', this.currentMenu + '/form.html');
        },
        del() {
            Vue.prototype.btnType = 5;

            Vue.prototype.selections = this.$refs.table.getSelection();
            if (this.checkbox && this.selections.length > 0) {
                this.$Message.warn('请勾选中数据');
                return;
            } else if (!this.selection) {
                this.$Message.warn('请点击选中行');
                return;
            }
            var id;
            if (this.checkbox) {
                id = this.selections[0].id;
            } else {
                id = this.selection.id;
            }
            this.$Confirm("确定删除？", "提示").then(() => {
                api.delete(this.currentMenu + "/" + id).then(res => {
                    this.loadList();
                }).catch((err) => {
                    this.$Message.error(err);
                });
            });
        },
        rowSelect(data) {
            Vue.prototype.selection = data;
        }

    },
    template: `
    <div>
        <div class="h-panel-bar">
            <h-button-group>
                <h-button v-for="item in buttons" @click="handle($event,item.click)" :icon="item.icon">{{item.title}}
                </h-button>
            </h-button-group>
        </div>
        <div class="h-panel-body bottom-line">
            <h-table :checkbox="checkbox" :selectRow="!checkbox"  ref="table" :datas="datas" @rowSelect="rowSelect" :loading="loading" :columns="columns">
            </h-table>
        </div>
        <div class="h-panel-bar">
            <h-pagination :cur="page.cur" :small="true" :total="page.total" align="right" @change="paging"></h-pagination>
        </div>
    </div>`
});

Vue.component('s-select', {
    props: ['url', 'value'],
    created() {
        if (this.value) {
            this.loadData(this.value, null);
        }
    },
    data() {
        return {
            show: '',
            datas: [],
            page: { cur: 1, size: 30, total: 30 },
            param: {
                keyName: 'keyName',
                titleName: 'titleName',
                minWord: 0,
                loadData: this.loadData
            },
            keyword: ''
        }
    },
    methods: {
        loadData(filter, callback) {
            if (this.keyword !== filter) {
                this.keyword = filter;
                this.page.cur = 1;
            }
            api.get(this.url, this.mergeJSON(this.page, { value: filter })).then(res => {
                var data = res.data.data;
                if (data.length < this.page.size) {
                    this.page.total = data.length;
                }
                if (callback) {
                    callback(data);
                } else {
                    this.show = data[0].titleName;
                }
            });
        },
        pageChange(page) {
            this.page.cur = page.cur;
            this.$refs.sselect.search();
        },
        onChange(data, trigger) {
            this.$emit('input', data.key)
        }
    },
    template: `<h-autocomplete :option="param" ref="sselect" v-model="show" :show="show"  @change="onChange" :disabled="readonly">
        <div slot="bottom" @mousedown.stop.prevent>
            <h-pagination :cur="page.cur" :total="page.total" @change="pageChange" layout="pager" small></h-pagination>
        </div>
    </h-autocomplete>`
});
