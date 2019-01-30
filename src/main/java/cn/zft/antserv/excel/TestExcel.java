package cn.zft.antserv.excel;

import cn.zft.antserv.utils.StaticFunc;
import cn.zft.antserv.utils.poi.ExcelWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestExcel extends ExcelWriter {
    private Map<String, Object> params;
    private int page = 0;

    public TestExcel(Map<String, Object> params) {
        this.params = params;
    }

    public static void main(String[] args) throws IOException {
        TestExcel t = new TestExcel(null);
        t.write(t.build(), null);
    }

    @Override
    public List<Map<String, String>> loadData() {
        params.put("page", page++ + "");
        params.put("limit", DATA_SIZE + "");
        StaticFunc.page(params);
        //TableView<MenuInf> table =  ContextUtil.getApplicationContext().getBean(MenuInfService.class).findList(params);

        return null;
    }

    @Override
    public String title() {
        return "测试报表框架";
    }

    @Override
    public String[] titleArray() {
        return new String[]{"姓名", "年龄", "性别", "爱好", "职业", "是否结婚", "毕业学校", "三维", "四维", "四维"};
    }

    @Override
    public String isTotal() {
        return "A5,A3,A8";
    }
}
