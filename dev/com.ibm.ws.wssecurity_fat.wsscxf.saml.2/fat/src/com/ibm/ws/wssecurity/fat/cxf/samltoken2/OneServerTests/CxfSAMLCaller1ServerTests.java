/*******************************************************************************
 * Copyright (c) 2021, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.wssecurity.fat.cxf.samltoken2.OneServerTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import componenttest.custom.junit.runner.FATRunner;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.security.saml20.fat.commonTest.SAMLConstants;
import com.ibm.ws.security.saml20.fat.commonTest.SAMLMessageConstants;
import com.ibm.ws.wssecurity.fat.cxf.samltoken2.common.CxfSAMLCallerTests;

import componenttest.topology.impl.LibertyServerWrapper;
import componenttest.annotation.SkipForRepeat;
import static componenttest.annotation.SkipForRepeat.EE8_FEATURES;
import static componenttest.annotation.SkipForRepeat.NO_MODIFICATION;


/**
 * The testcases in this class were ported from tWAS' test SamlWebSSOTests.
 * If a tWAS test is not applicable, it will be noted in the comments below.
 * If a tWAS test fits better into another test class, it will be noted
 * which test project/class it now resides in.
 * In general, these tests perform a simple IdP initiated SAML Web SSO, using
 * httpunit to simulate browser requests. In this scenario, a Web client
 * accesses a static Web page on IdP and obtains a a SAML HTTP-POST link
 * to an application installed on a WebSphere SP. When the Web client
 * invokes the SP application, it is redirected to a TFIM IdP which issues
 * a login challenge to the Web client. The Web Client fills in the login
 * form and after a successful login, receives a SAML 2.0 token from the
 * TFIM IdP. The client invokes the SP application by sending the SAML
 * 2.0 token in the HTTP POST request.
 */

@LibertyServerWrapper
@RunWith(FATRunner.class)
public class CxfSAMLCaller1ServerTests extends CxfSAMLCallerTests {

    private static final Class<?> thisClass = CxfSAMLCallerTests.class;

    @BeforeClass
    public static void setupBeforeTest() throws Exception {

        userName = "user1";
        userPass = "security";

        flowType = SAMLConstants.SOLICITED_SP_INITIATED;
        idpSupportedType = SAMLConstants.TFIM_TYPE;

        msgUtils.printClassName(thisClass.toString());
        Log.info(thisClass, "setupBeforeTest", "Prep for test");

        // add any additional messages that you want the "start" to wait for
        // we should wait for any providers that this test requires
        List<String> extraMsgs = new ArrayList<String>();
        extraMsgs.add("CWWKT0016I.*samlcallerclient.*");
        extraMsgs.add("CWWKS5000I");
        extraMsgs.add("CWWKS5002I");

        List<String> extraApps = new ArrayList<String>();
        extraApps.add(SAMLConstants.SAML_CXF_CALLER_CLIENT_APP);
        
        startSPWithIDPServer("com.ibm.ws.wssecurity_fat.saml.caller", "server_2_in_1.xml", SAMLConstants.SAML_SERVER_TYPE, extraMsgs, extraApps, true, SAMLConstants.EXAMPLE_CALLBACK, SAMLConstants.EXAMPLE_CALLBACK_FEATURE);
        
        testSAMLServer.getServer().copyFileToLibertyInstallRoot("lib/features", "internalFeatures/securitylibertyinternals-1.0.mf");    
        servicePort = Integer.toString(testSAMLServer.getServerHttpPort());
        serviceSecurePort = Integer.toString(testSAMLServer.getServerHttpsPort());

        setActionsForFlowType(flowType);
        testSettings.setIdpUserName(userName);
        testSettings.setIdpUserPwd(userPass);
        testSettings.setSpTargetApp(testSAMLServer.getHttpString() + "/samlcallerclient/CxfSamlCallerSvcClient");
        testSettings.setSamlTokenValidationData(testSettings.getIdpUserName(), testSettings.getSamlTokenValidationData().getIssuer(), testSettings.getSamlTokenValidationData().getInResponseTo(), testSettings.getSamlTokenValidationData().getMessageID(), testSettings.getSamlTokenValidationData().getEncryptionKeyUser(), testSettings.getSamlTokenValidationData().getRecipient(), SAMLConstants.AES256);

        testSAMLServer.addIgnoredServerExceptions(SAMLMessageConstants.CWWKS5207W_SAML_CONFIG_IGNORE_ATTRIBUTES, SAMLMessageConstants.CWWKG0101W_CONFIG_NOT_VISIBLE_TO_OTHER_BUNDLES, SAMLMessageConstants.CWWKF0001E_FEATURE_MISSING);
        
        //issue 23060 
        //The condition checker is also used in some tests which do not run with CBH, 
        //where it acts another checker to reference the respective old/new format ehcache xml.
        Set<String> features = testSAMLServer.getServer().getServerConfiguration().getFeatureManager().getFeatures(); 
		if (features.contains("usr:wsseccbh-1.0")) {
            setFeatureVersion("EE7cbh1");
        } else if (features.contains("usr:wsseccbh-2.0")) {
            setFeatureVersion("EE7cbh2");
        } 
        
    }
}
