/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.dlz.framework.ssme.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.dlz.framework.ssme.util.pay.alipay.sign.MD5;


public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }
    
    
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        boolean result= equals(tokenHashedCredentials, accountCredentials);
        if(!result && token instanceof UsernamePasswordToken){
        	UsernamePasswordToken token2=(UsernamePasswordToken)token;
        	token2.setPassword(MD5.sign(new String(token2.getPassword())).toCharArray());
        	result= doCredentialsMatch2(token2, info);
        }
        return result;
    }
    
    public boolean doCredentialsMatch2(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        return equals(tokenHashedCredentials, accountCredentials);
    }

}
