package is.hi.hbv601g.hikers.Networking;

public interface NetworkCallback<T> {
    void onSuccess(T result);
    void onFailure(String error);
}
