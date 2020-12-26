import React, { Component } from "react";

class AccessTokenProvider {

    getAccessToken(){
        const token = JSON.parse(localStorage.getItem('user'));
        return token.access_token;
    }
}

export default new AccessTokenProvider();