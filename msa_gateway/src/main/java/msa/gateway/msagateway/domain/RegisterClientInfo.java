package msa.gateway.msagateway.domain;


/*
 * 동적클라이언트 등록시 컨트롤러에서 매개변수로 사용되는 Dto클래스.
 */
public class RegisterClientInfo {

    private String name;
    private String redirectUri;
    private String clientType;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRedirectUri() {
        return redirectUri;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    public String getClientType() {
        return clientType;
    }
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


}