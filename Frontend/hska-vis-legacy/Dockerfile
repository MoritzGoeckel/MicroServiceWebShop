From tomcat:8.0
ADD EShop-1.0.0.war /usr/local/tomcat/webapps/
ADD ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
#RUN cat conf/server.xml
RUN sed -i "s|8080|4200|g" conf/server.xml
#RUN echo "#########################################################################"
#RUN value=`cat conf/server.xml` && echo "${value//8080/80}" >| conf/server.xml
RUN cat conf/server.xml
EXPOSE 4200
