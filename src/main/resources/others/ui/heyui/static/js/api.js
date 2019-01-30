axios.defaults.timeout = 5000;
// axios.defaults.headers.post['Context-Type'] = "application:json;charset=utf8";
// axios.defaults.headers['Access-Control-Allow-Origin'] = '*';
// axios.defaults.headers['Access-Control-Allow-Headers'] = 'X-Requested-With,Content-Type';
// axios.defaults.headers['Access-Control-Allow-Methods'] = 'PUT,POST,GET,DELETE,OPTIONS';

var api = {
    baseURL: '/api',
    get(path, params) {
        return axios.get(this.baseURL + path, { params: params });
    },
    post(path, params) {
        return axios.post(this.baseURL + path, params);
    },
    put(path, params) {
        return axios.put(this.baseURL + path, params);
    },
    delete(path, params) {
        return axios.delete(this.baseURL + path, params);
    },
    setCookie(name, value) {
        document.cookie = name + "=" + escape(value) + ";path=/";
    },
    getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    },
    delCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = this.getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
}