FROM 10.3.98.241/library/tomcat:8
MAINTAINER xuyf
RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD dist /usr/local/tomcat/webapps/ROOT/
ENV TOMCAT_PASS admin
EXPOSE 8080