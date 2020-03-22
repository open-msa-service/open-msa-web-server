package msa.gateway.msagateway.domain;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/*
 * ClientDetailsService에서 load하면 반환하는 객체이다.
 */
public class ClientDetailsImpl extends BaseClientDetails {

    private static final long serialVersionUID = -8263549600098155096L;

    private ClientType clientType;

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

}