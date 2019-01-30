package cn.zft.antserv.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataTableVM<T> extends BaseVM.Response {
    private List<T> data;
    private int total;

    public DataTableVM(List<T> data, int total) {
        super("00", "");
        this.data = data;
        this.total = total;
    }

}
