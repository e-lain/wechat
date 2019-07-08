package top.zerotop.util;

public class Result<Content> {
    public static final Result<Boolean> SUCCESS = new Result<>(true);
    private Content content = null;
    private boolean success = false;
    private String errorCode = "NO_ERROR";
    private String errorMsg = "";

    public Result() {

    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(Content content) {
        this.content = content;
    }

    public static <Content> Result<Content> make(Content content) {
        Result<Content> result = new Result<>();
        result.success = true;
        result.content = content;
        return result;
    }

    public static <Content> Result<Content> error(Content content) {
        Result<Content> result = new Result<>();
        result.success = false;
        result.content = content;
        return result;
    }

    public static Result<Boolean> getSUCCESS() {
        return SUCCESS;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
