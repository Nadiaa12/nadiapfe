$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' '-Dfile.encoding=UTF-8' -cp '.;../lib/routines.jar;../lib/log4j-slf4j-impl-2.13.2.jar;../lib/log4j-api-2.13.2.jar;../lib/log4j-core-2.13.2.jar;../lib/spring-web-5.3.8.jar;../lib/wss4j-policy-2.3.0.jar;../lib/cxf-rt-rs-extension-providers-3.4.4.jar;../lib/spring-aop-5.3.8.jar;../lib/bcprov-jdk15on-1.69.jar;../lib/spring-core-5.3.8.jar;../lib/opensaml-saml-impl-3.4.5.jar;../lib/opensaml-xacml-saml-api-3.4.5.jar;../lib/spring-beans-5.3.8.jar;../lib/cxf-core-3.4.4.jar;../lib/opensaml-profile-api-3.4.5.jar;../lib/cxf-rt-rs-security-oauth2-3.4.4.jar;../lib/opensaml-xacml-impl-3.4.5.jar;../lib/jakarta.annotation-api-1.3.5.jar;../lib/jakarta.xml.bind-api-2.3.3.jar;../lib/opensaml-xmlsec-api-3.4.5.jar;../lib/javax.activation-1.2.0.jar;../lib/opensaml-security-impl-3.4.5.jar;../lib/opensaml-saml-api-3.4.5.jar;../lib/cxf-rt-security-3.4.4.jar;../lib/jakarta.xml.soap-api-1.4.1.jar;../lib/guava-30.0-jre.jar;../lib/woodstox-core-asl-4.4.1.jar;../lib/stax-ex-1.8.3.jar;../lib/javax.ws.rs-api-2.1.jar;../lib/xmlschema-core-2.2.5.jar;../lib/cxf-rt-bindings-soap-3.4.4.jar;../lib/wss4j-bindings-2.3.0.jar;../lib/cxf-rt-frontend-jaxrs-3.4.4.jar;../lib/cxf-rt-wsdl-3.4.4.jar;../lib/istack-commons-runtime-3.0.10.jar;../lib/ehcache-3.8.1.jar;../lib/opensaml-security-api-3.4.5.jar;../lib/spring-expression-5.3.8.jar;../lib/cxf-rt-transports-http-3.4.4.jar;../lib/cxf-rt-rs-client-3.4.4.jar;../lib/commons-logging-1.2.jar;../lib/cryptacular-1.2.4.jar;../lib/neethi-3.1.1.jar;../lib/jaxb-runtime-2.3.2.jar;../lib/wss4j-ws-security-common-2.3.0.jar;../lib/jasypt-1.9.3.jar;../lib/jettison-1.4.1.jar;../lib/xmlsec-2.2.0.jar;../lib/stax2-api-3.1.4.jar;../lib/opensaml-xmlsec-impl-3.4.5.jar;../lib/javax.wsdl_1.6.2.v201012040545.jar;../lib/spring-webmvc-5.3.8.jar;../lib/opensaml-core-3.4.5.jar;../lib/opensaml-soap-api-3.4.5.jar;../lib/cxf-rt-security-saml-3.4.4.jar;../lib/cxf-rt-ws-security-3.4.4.jar;../lib/security-common-8.0.1.jar;../lib/joda-time-2.9.jar;../lib/txw2-2.3.3.jar;../lib/aopalliance-1.0.jar;../lib/commons-codec-1.15.jar;../lib/jakarta.jws-api-1.1.1.jar;../lib/wss4j-ws-security-policy-stax-2.3.0.jar;../lib/cxf-rt-features-logging-3.4.4.jar;../lib/wss4j-ws-security-stax-2.3.0.jar;../lib/org.apache.servicemix.specs.jaxws-api-2.2-2.9.0.jar;../lib/wss4j-ws-security-dom-2.3.2.jar;../lib/opensaml-xacml-api-3.4.5.jar;../lib/opensaml-xacml-saml-impl-3.4.5.jar;../lib/cxf-rt-databinding-jaxb-3.4.4.jar;../lib/spring-context-5.3.8.jar;../lib/accessors-smart-2.4.7.jar;../lib/json-path-2.1.0.jar;../lib/crypto-utils-0.31.12.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/dom4j-2.1.3.jar;../lib/json-smart-2.4.7.jar;../lib/slf4j-api-1.7.29.jar;../lib/jtds-1.3.1-patch-20190523.jar;productcategoriestd_0_1.jar;' local_project.productcategoriestd_0_1.ProductCategoriesTD $args
