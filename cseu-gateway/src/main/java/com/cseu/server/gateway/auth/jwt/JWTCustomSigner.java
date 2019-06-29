
package com.cseu.server.gateway.auth.jwt;


import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;

/**
 *  Creates a JWTSigner using a simple secret string
 */
public class JWTCustomSigner {
    private JWSSigner signer;

    public JWTCustomSigner() {
        try {
            this.signer = new MACSigner(JWTSecrets.DEFAULT_SECRET);
        } catch (KeyLengthException e) {
            this.signer = null;
        }
    }

    public JWSSigner getSigner() {
        return this.signer;
    }
}
