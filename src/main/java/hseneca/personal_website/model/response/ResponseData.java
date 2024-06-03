package hseneca.personal_website.model.response;

import lombok.Data;

@Data
public class ResponseData {
    private int status = 200;
    private String desc;
    private Object data;
    private boolean isSucess;

    public ResponseData() {}

    public ResponseData(int status, String desc, Object data, boolean isSucess) {
        this.status = status;
        this.desc = desc;
        this.data = data;
        this.isSucess = false;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                ", isSucess=" + isSucess +
                '}';
    }
}
