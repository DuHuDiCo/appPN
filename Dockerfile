FROM openjdk:11



ARG XMX=500m


ENV XMX=$XMX



VOLUME /tmp


EXPOSE 9090

ADD ./target/apppn-0.0.1-SNAPSHOT.jar apppn.jar

ENTRYPOINT java -Xmx$XMX -jar /apppn.jar 
