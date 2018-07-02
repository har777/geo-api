package api;

public class StatusBody {
    boolean success;
    String error;

    public StatusBody(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "StatusBody{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
