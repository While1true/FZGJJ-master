package RxWeb;

/**
 * Created by vange on 2017/8/31.
 */

public class BaseBean<T> {
    int status;
    T data;

    @Override
    public String toString() {
        return "BaseBean{" +
                "status=" + status +
                ", t=" + data +
                '}';
    }

    public BaseBean(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setT(T data) {
        this.data = data;
    }
}
