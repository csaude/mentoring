# Mentoring
Mentoring Project (Back-End)

This project as only one dependency that is frameworks-core. All you need to do is to clone this project and build or install on your local machine.

#Configurations
 - You need to create 2 databases schemas (one for test and another for development) with names mentoring_test and mentoring respectively
 - You need to create a properties file named `mentoring_dev.properties` in your home directory with following contents.
 > \# Used the specify local repository  
 > _mentoring.local.repository=/home/user1/mentoring_releases/_  
 >  
 > \# Used to specify the location to copy the newly created war file for production build profile. This can be a tomcat webapps directory or a  
 > \# mounted docker volume attached which is how we use to deploy to the docker compose infrastructure currently  
 > _warfile.prod.copy.directory=/opt/tomcat/webapps/mentoring_
 >
 > \# Used to specify of the location to copy the newly created war file for testing build profile.
 > _warfile.test.copy.directory=/opt/data/webapp/test/mentoring_  
 >  
 > \# Used to specify the production database mysql host.
 > _mysql.prod.host=localhost_  
 >  
 > \# Used to specify the production database mysql port.  
 > _mysql.prod.port=3306_  