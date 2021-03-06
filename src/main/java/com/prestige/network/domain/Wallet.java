package com.prestige.network.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prestige.network.service.CryptUtils;
import com.prestige.network.service.MiddlewareRequest;
import com.prestige.network.service.WalletService;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "private_key")
    @JsonIgnore
    private String private_key;

    @Column(name = "public_key")
    @JsonIgnore
    private String public_key;

    @Column(name = "public_key_hash")
    @JsonIgnore
    private String public_key_hash;

    @Column(name = "wif")
    @JsonIgnore
    private String wif;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    public Wallet() {}

    public Wallet(String address, String name, String private_key, String public_key, String public_key_hash, String wif, User user) {
        this.address = address;
        this.name = name;
        this.private_key = private_key;
        this.public_key = public_key;
        this.public_key_hash = public_key_hash;
        this.wif = wif;
        this.user = user;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public Wallet address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Wallet name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
     }

    public String getPrivate_key() {
        return private_key;
    }

    public Wallet private_key(String private_key) {
        this.private_key = private_key;
        return this;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public Wallet public_key(String public_key) {
        this.public_key = public_key;
        return this;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPublic_key_hash() {
        return public_key_hash;
    }

    public Wallet public_key_hash(String public_key_hash) {
        this.public_key_hash = public_key_hash;
        return this;
    }

    public void setPublic_key_hash(String public_key_hash) {
        this.public_key_hash = public_key_hash;
    }

    public String getWif() {
        return wif;
    }

    public Wallet wif(String wif) {
        this.wif = wif;
        return this;
    }

    public void setWif(String wif) {
        this.wif = wif;
    }

    public User getUser() {
        return user;
    }

    public Wallet user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        if (wallet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wallet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", name='" + getName() + "'" +
            ", private_key='" + getPrivate_key() + "'" +
            ", public_key='" + getPublic_key() + "'" +
            ", public_key_hash='" + getPublic_key_hash() + "'" +
            ", wif='" + getWif() + "'" +
            "}";
    }

    //Conection api NEO / Fill Wallet
    public Wallet createWalletfromApi(User user,String nameWallet) {
        //Crear wallet
        String key = System.getenv("PASSPHRASE_VALUE");
        JSONObject middlewareRequest = new MiddlewareRequest().post("/wallet/new", new ArrayList<>());
        String name = (nameWallet == null || nameWallet == "") ? ("Wallet-" + middlewareRequest.getString("address")) : nameWallet;
        Wallet wallet = new Wallet(
           middlewareRequest.getString("address"),
           name,
           CryptUtils.encrypt(middlewareRequest.getString("private_key"), key),
           CryptUtils.encrypt(middlewareRequest.getString("public_key"), key),
           CryptUtils.encrypt(middlewareRequest.getString("public_key_hash"), key),
           CryptUtils.encrypt(middlewareRequest.getString("wif"), key),
           user
         );
        return wallet;
    }

    //Conection api NEO / Fill Wallet from Wif
    public Wallet importWalletfromApi(User user,String wif,String nameWallet) {
        //Import wallet
        String key = System.getenv("PASSPHRASE_VALUE");
        JSONObject middlewareRequest = new MiddlewareRequest().get("/get_data_from_wif/" + wif);
        String name = (nameWallet == null || nameWallet == "") ? ("Wallet-" + middlewareRequest.getString("address")) : nameWallet;
        Wallet wallet = new Wallet(
            middlewareRequest.getString("address"),
            name,
            CryptUtils.encrypt(middlewareRequest.getString("private_key"), key),
            CryptUtils.encrypt(middlewareRequest.getString("public_key"), key),
            CryptUtils.encrypt(middlewareRequest.getString("public_key_hash"), key),
            CryptUtils.encrypt(middlewareRequest.getString("wif"), key),
            user
         );
        return wallet;
     }

    //Conection api NEO / accountstate
    /*public List accountstatefromApi(Wallet wallet) {
        //Import wallet
        String key = System.getenv("PASSPHRASE_VALUE");
        JSONObject middlewareRequest = new MiddlewareRequest().get("/get_data_from_wif/" + wif);
        Wallet wallet = new Wallet(
            middlewareRequest.getString("address"),
            "a",
            CryptUtils.encrypt(middlewareRequest.getString("private_key"), key),
            CryptUtils.encrypt(middlewareRequest.getString("public_key"), key),
            CryptUtils.encrypt(middlewareRequest.getString("public_key_hash"), key),
            CryptUtils.encrypt(middlewareRequest.getString("wif"), key),
            user
        );
        return wallet;
    }*/


}
